# Morphe ARCore experiment patches

Project Morphe, copy từ MorpheApp/morphe-patches-template ngày 11/11/2026.
Đọc `../README.md` và `../FINGERPRINT_RESEARCH.md` trước khi build —
hai file đó mới là tài liệu thật của thử nghiệm này.

- Target: `com.google.ar.core` 1.57.2626402, experimental.
- Patch duy nhất: `ForceSupportedInstalledPatch` — ép
  `ArCoreApk.checkAvailability()` trả `SUPPORTED_INSTALLED`.
  Fingerprint **CHƯA VERIFY**, default = false.
- Build (cần JDK 21 + GitHub PAT, trên laptop, không build được ở VM này):

```bat
.\gradlew buildAndroid
```

`.mpp` ra ở `patches\build\libs\patches-*.mpp`, patch bằng Morphe Desktop.
