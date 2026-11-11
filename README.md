# Morphe ARCore experiment patches

Project Morphe, copy từ MorpheApp/morphe-patches-template ngày 11/11/2026.

- Target: `com.google.ar.core` 1.57.2626402, experimental.
- Patch duy nhất: `ForceSupportedInstalledPatch` — ép `ArCoreApk.checkAvailability()` trả `SUPPORTED_INSTALLED`. Fingerprint **CHƯA VERIFY**, default = false.
- Build tren GitHub Actions (khong can PAT): xem `.github/workflows/build-mpp.yml`.

`.mpp` ra ở `patches/build/libs/patches-*.mpp`, patch bằng Morphe Desktop.
