package app.morphe.patches.arcore.availability

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.arcore.shared.Constants.COMPATIBILITY_ARCORE

private const val EXTENSION_CLASS = "Lapp/morphe/extension/arcore/ArCoreAvailability;"

/**
 * EXPERIMENTAL, UNVERIFIED.
 *
 * Idea: force ArCoreApk.checkAvailability(Context) to return
 * ArCoreApk.Availability.SUPPORTED_INSTALLED, via the extension method
 * ArCoreAvailability.supportedInstalled().
 *
 * What this can do at most:
 * - Make callers that only check availability think ARCore is supported.
 *
 * What this CANNOT do (see README.md):
 * - Supply the missing ARCore device profile for ZTE Z2571N.
 * - Patch the native libdevice_profile_loader.so.
 * - Make ArSession_create() succeed on an unsupported device.
 *   Google's C API docs say exactly this: SUPPORTED_INSTALLED only
 *   indicates presence of a suitably versioned ARCore APK; session
 *   creation may still fail if the APK was side-loaded onto an
 *   unsupported device.
 *
 * Default = false, so Morphe Manager will NOT apply it unless Lê
 * turns it on explicitly in Expert mode. That is intentional:
 * an unverified patch must never be on by default.
 */
@Suppress("unused")
val forceSupportedInstalledPatch = bytecodePatch(
    name = "Force ARCore availability: SUPPORTED_INSTALLED (experimental)",
    description = "Experimental: force ArCoreApk.checkAvailability() to return SUPPORTED_INSTALLED. Fingerprint UNVERIFIED. Does NOT supply a device profile, so AR session creation may still fail.",
    default = false
) {
    compatibleWith(COMPATIBILITY_ARCORE)

    extendWith("extensions/extension.mpe")

    execute {
        ArCoreApkCheckAvailabilityFingerprint.method.addInstructions(
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
