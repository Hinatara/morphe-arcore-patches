package app.morphe.patches.arcore.availability

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * CANDIDATE fingerprint — UNVERIFIED against the real dex of
 * Google Play Services for AR 1.57.2626402.
 *
 * Derived only from Google's public Java API docs:
 * - Class: com.google.ar.core.ArCoreApk
 * - Method: public ArCoreApk.Availability checkAvailability(Context applicationContext)
 * - Availability enum values include SUPPORTED_INSTALLED (203 in the C API),
 *   SUPPORTED_NOT_INSTALLED, SUPPORTED_APK_TOO_OLD, UNSUPPORTED_DEVICE_NOT_CAPABLE.
 *
 * DO NOT trust this fingerprint until FINGERPRINT_RESEARCH.md step 2 is done
 * in jadx on the real APK:
 * - Confirm definingClass / name / returnType / parameters / accessFlags.
 * - Replace the `filters` below with real instruction filters found in
 *   that method, in their real order. The string filter below is a
 *   placeholder guess, not a measured instruction.
 * - If checkAvailability() turns out to be a thin native wrapper, this
 *   fingerprint is weak by design — record that and stop, do not invent
 *   a different fingerprint to force a match.
 *
 * Docs used:
 * https://developers.google.com/ar/reference/java/com/google/ar/core/ArCoreApk
 * https://developers.google.com/ar/reference/c/group/ar-core-apk
 */
object ArCoreApkCheckAvailabilityFingerprint : Fingerprint(
    definingClass = "Lcom/google/ar/core/ArCoreApk;",
    name = "checkAvailability",
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "Lcom/google/ar/core/ArCoreApk\$Availability;",
    parameters = listOf("Landroid/content/Context;"),
    filters = listOf(
        // UNVERIFIED placeholder. Replace with real filters from jadx.
        // Kept minimal on purpose: a wrong specific filter fails loudly
        // at patch time instead of silently matching the wrong method.
        string("checkAvailability")
    )
)
