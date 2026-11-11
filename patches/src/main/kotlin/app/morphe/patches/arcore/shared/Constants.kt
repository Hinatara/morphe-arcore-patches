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
        appIconColor = 0x4285F4,
        targets = listOf(
            // NOTE (2026-11-11, v1.0.1): the target was experimental
            // (isExperimental = true) in v1.0.0. Morphe Manager 2.5.0
            // showed the source as 0 patches — the experimental-only
            // target is hidden when the pre-release/experimental
            // toggles are off, and those toggles were not found in
            // the 2.5.0 UI. The patch itself is STILL experimental
            // and default-off (see ForceSupportedInstalledPatch.kt):
            // it only runs if Lê turns it on explicitly. Only the
            // target visibility flag was dropped, so the patch shows
            // up in the list at all.
            AppTarget(
                version = "1.57.2626402"
            )
        )
    )
}
