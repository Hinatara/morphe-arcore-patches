# Morphe ARCore Experiment Patches

<p align="center">
  <strong>Experimental ARCore compatibility patch for Morphe</strong><br>
  A research project targeting Google Play Services for AR on selected unsupported Android devices.
</p>

<p align="center">
  <a href="https://github.com/Hinatara/morphe-arcore-patches/actions/workflows/morphe-arcore-release.yml"><img src="https://github.com/Hinatara/morphe-arcore-patches/actions/workflows/morphe-arcore-release.yml/badge.svg?branch=main" alt="Release workflow"></a>
  <a href="https://github.com/Hinatara/morphe-arcore-patches/releases"><img src="https://img.shields.io/github/v/release/Hinatara/morphe-arcore-patches?display_name=tag" alt="Latest release"></a>
  <a href="https://github.com/Hinatara/morphe-arcore-patches/issues"><img src="https://img.shields.io/github/issues/Hinatara/morphe-arcore-patches" alt="Issues"></a>
</p>

> [!WARNING]
> **Experimental — use at your own risk.** This patch changes an ARCore availability check; it does not add missing device calibration, camera capabilities, motion sensors, or an ARCore device profile. An app may report ARCore as available and still fail to create an AR session. The target device fingerprint is **not verified**.

## Overview

This repository contains an experimental Morphe patch for:

- **Package:** `com.google.ar.core` (Google Play Services for AR)
- **Target version:** `1.57.2626402`
- **Initial test target:** ZTE nubia Neo 5G (Z2571N)
- **Patch state by default:** Disabled

The goal is to investigate whether an availability response can be overridden on devices that ARCore does not officially support. This is not an official Google, ZTE, or Morphe project.

## Install with Morphe Manager

1. Open **Morphe Manager**.
2. Go to **Sources → + → Remote**.
3. Add this repository:

   `https://github.com/Hinatara/morphe-arcore-patches`

Or use the direct source link:

<p align="center">
  <a href="https://morphe.software/add-source?github=Hinatara/morphe-arcore-patches"><strong>Add source to Morphe</strong></a>
</p>

The repository needs to be **public** for Morphe to fetch it without authentication. If it is private, configure a GitHub Personal Access Token in Morphe Manager's GitHub PAT settings.

After adding the source, select the available patch in Morphe Manager. Review the patch description and keep it disabled unless you intentionally want to test it.

## Included patch

<!-- PATCHES_START -->
<!-- PATCHES_END -->

### What the patch changes

**Force ARCore availability: `SUPPORTED_INSTALLED` (experimental)**

The patch forces `ArCoreApk.checkAvailability()` to report `SUPPORTED_INSTALLED`.

It does **not**:

- provide an ARCore device profile for the phone;
- certify the device as ARCore-compatible;
- fix missing or unsupported camera, sensor, or tracking capabilities;
- guarantee that an application can start or maintain an AR session.

For the ZTE Z2571N test target, the absence of device profile data may still prevent AR sessions from working.

## Build and releases

The **Release** GitHub Actions workflow runs on pushes to `main`. It is intended to build the Morphe patch package (`.mpp`), publish a GitHub Release, and update `patches-bundle.json` for Morphe source updates.

- [View workflow runs](https://github.com/Hinatara/morphe-arcore-patches/actions)
- [Browse releases](https://github.com/Hinatara/morphe-arcore-patches/releases)
- [Report a problem](https://github.com/Hinatara/morphe-arcore-patches/issues)

A local build requires **JDK 21** and **Android SDK 36**. Access to the Morphe package registry may also require a GitHub token; unauthenticated registry requests can return HTTP 401.

## Compatibility and troubleshooting

| Item | Current information |
|---|---|
| Android package | `com.google.ar.core` |
| Target ARCore version | `1.57.2626402` |
| Test device | ZTE nubia Neo 5G (Z2571N) |
| Device fingerprint | Unverified |
| Patch default | Disabled |
| Official ARCore support | Not established |

If an app still reports that ARCore is unavailable, or an AR session fails after applying the patch, that does not necessarily mean the patch failed: the device may lack the profile or capabilities required by ARCore. Restore the original app state by removing the modified installation and reinstalling the official AR component if needed.

## Disclaimer

This repository is provided for **research and testing**. Compatibility is not guaranteed. Use it only on devices and software you are authorized to modify. You are responsible for any instability, data loss, or application issues resulting from its use.

---

<p align="center">
  <sub>Independent community experiment · Not affiliated with Google, ZTE, or Morphe</sub>
</p>
