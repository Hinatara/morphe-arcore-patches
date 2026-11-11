package app.morphe.extension.arcore;

import com.google.ar.core.ArCoreApk;

/**
 * Extension code compiled into the .mpe and called from the patch.
 *
 * Returns the public ARCore enum value for "supported and installed".
 * This class is only a return-value helper — it does not create a
 * device profile, does not touch native code, and does not make
 * ArSession_create() succeed on its own.
 *
 * NOTE: This file references com.google.ar.core.ArCoreApk, which is
 * part of the target APK / ARCore SDK, not part of the patch project.
 * If the build cannot resolve ArCoreApk on the compile classpath,
 * replace this with the enum lookup by name (valueOf) or return the
 * value via smali in the patch itself — see FINGERPRINT_RESEARCH.md.
 * Do not fake the return type to make the build pass.
 */
@SuppressWarnings("unused")
public class ArCoreAvailability {

    public static ArCoreApk.Availability supportedInstalled() {
        return ArCoreApk.Availability.SUPPORTED_INSTALLED;
    }
}
