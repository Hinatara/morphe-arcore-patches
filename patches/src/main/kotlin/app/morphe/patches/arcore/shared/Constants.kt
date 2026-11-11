package app.morphe.patches.arcore.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    /**
     * Target: Google Play Services for AR (ARCore service APK).
     *
     * Verified facts (11/11/2026):
     * - Package: com.google.ar.core
     * - Latest version seen on APKMirror: 1.57.2626402 (6/11/2026).
     *   The arm-v7a variant has version code 262640013; ZTE nubia Neo 5G
     *   (Unisoc T820) needs the arm64-v8a variant, NOT arm-v7a.
     * - This is a system service signed by Google, not a normal user app.
     *   Patching it changes its signature, so Play may overwrite it and
     *   AR apps may refuse it. See README.md.
     *
     * The target below is EXPERIMENTAL: no confirmed-working version exists,
     * because no device profile exists for the ZTE Z2571N.
     */
    val COMPATIBILITY_ARCORE = Compatibility(
        name = "Google Play Services for AR",
        packageName = "com.google.ar.core",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xFF4285F4,
        targets = listOf(
            AppTarget(
                version = "1.57.2626402",
                isExperimental = true
            )
        )
    )
}
