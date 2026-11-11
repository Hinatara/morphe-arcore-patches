# Morphe ARCore experiment patches

Experimental Morphe patch for Google Play Services for AR
(`com.google.ar.core`) on unsupported devices — ZTE nubia Neo 5G test.
Fingerprint **UNVERIFIED**, patch default = false.

## Add to Morphe Manager

Remote source (auto-updates when a new release is published):

- In Morphe: **Sources → + → Remote** → paste
  `https://github.com/Hinatara/morphe-arcore-patches`
- Or tap: [Add to Morphe](https://morphe.software/add-source?github=Hinatara/morphe-arcore-patches)

The repo must be public for Morphe to fetch it without a token.
If it stays private, set a GitHub PAT in Morphe Settings → GitHub PAT first.

## Patches

<!-- PATCHES_START -->
<!-- PATCHES_END -->

## Notes

- Target: `com.google.ar.core` 1.57.2626402.
- Only patch: `Force ARCore availability: SUPPORTED_INSTALLED
  (experimental)` — forces `ArCoreApk.checkAvailability()` to return
  `SUPPORTED_INSTALLED`. It does NOT supply a device profile, so an AR
  session may still fail on the ZTE Z2571N (no device profile data).
- Releases: GitHub Actions workflow `Release` (semantic-release) runs
  on push to main — it builds the `.mpp`, creates a GitHub Release with
  the bundle, and commits `patches-bundle.json` back so Morphe can
  auto-update. Manual builds: workflow `Build .mpp`.
- Local build needs JDK 21 + Android SDK 36 + a GitHub token for the
  Morphe package registry (the registry returns 401 without one).
