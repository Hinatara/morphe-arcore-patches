package app.morphe.patches.arcore.availability

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.patch
import app.morphe.patches.arcore.shared.Constants.COMPATIBILITY_CARDBOARDXR

private const val EXTENSION_CLASS = "Lapp/morphe/extension/arcore/ArCoreAvailability;"

/**
 * EXPERIMENTAL, fingerprint VERIFIED (2026-11-12).
 *
 * Target changed in v1.0.3: this patch now patches CardboardXR 1.0.0
 * (com.brenpoly.cardboardxr), NOT the ARCore service APK. The service
 * APK contains no ArCoreApk class at all (verified by dexdump), so the
 * old target could never match. See Fingerprints.kt for the dex proof.
 *
 * Idea: force the real `checkAvailability(Context)` implementation
 * (`com.google.ar.core.k`, returned by `ArCoreApk.getInstance()`) to
 * return `ArCoreApk.Availability.SUPPORTED_INSTALLED`, via the
 * extension method ArCoreAvailability.supportedInstalled(). The
 * injected instructions run at index 0, before the method's own
 * monitor-enter / cached-result logic, so the real check never runs.
 * Unity calls this path through ArCoreApkJniAdapter, so forcing it
 * here covers the Unity JNI route too.
 *
 * What this can do at most:
 * - Make CardboardXR think ARCore is supported and installed.
 *
 * What this CANNOT do (see README.md):
 * - Supply the missing ARCore device profile for ZTE Z2571N.
 * - Patch the native libdevice_profile_loader.so / libarcore_sdk_c.so.
 * - Make ArSession_create() succeed on an unsupported device.
 *   Google's C API docs say exactly this: SUPPORTED_INSTALLED only
 *   indicates presence of a suitably versioned ARCore APK; session
 *   creation may still fail if the APK was side-loaded onto an
 *   unsupported device. The 1.54 test already showed tracking fail
 *   after getting past availability, so a working 6DoF session here
 *   is unlikely — this patch is a diagnostic step, not a fix.
 *
 * Default = false, so Morphe Manager will NOT apply it unless Lê
 * turns it on explicitly in Expert mode. That is intentional:
 * an unverified-by-running patch must never be on by default.
 */
@Suppress("unused")
val forceSupportedInstalledPatch = patch(
    name = "Force ARCore availability: SUPPORTED_INSTALLED (CardboardXR)",
    description = "Experimental: force the real checkAvailability() in CardboardXR (com.google.ar.core.k) to return SUPPORTED_INSTALLED. Fingerprint VERIFIED from the CardboardXR 1.0.0 dex. Does NOT supply a device profile, so AR session creation may still fail.",
    default = false
) {
    compatibleWith(COMPATIBILITY_CARDBOARDXR)

    extendWith("extensions/extension.mpe")

    execute {
        CardboardXrCheckAvailabilityFingerprint.method.addInstructions(
            0,
            """
                invoke-static {}, $EXTENSION_CLASS;->supportedInstalled()Ljava/lang/Object;
                move-result-object v0
                check-cast v0, Lcom/google/ar/core/ArCoreApk${'$'}Availability;
                return-object v0
            """
        )
    }
}
