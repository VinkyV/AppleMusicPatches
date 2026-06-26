package dev.vinkyv.patches.applemusic.leanback

import app.revanced.patcher.patch.resourcePatch
import org.w3c.dom.Element

@Suppress("unused")
val fixLeanback = resourcePatch(
    name = "Fix leanback for TV",
    description = "Make leanback & touchscreen not required!",
) {
    compatibleWith("com.apple.android.music")

    apply {
        document("AndroidManifest.xml").use { document ->
            document.createElement("uses-feature").apply {
                setAttribute("android:name", "android.software.leanback")
                setAttribute("android:required", "false")
            }
            document.createElement("uses-feature").apply {
                setAttribute("android:name", "android.hardware.touchscreen")
                setAttribute("android:required", "false")
            }

            val intentFilters = document.getElementsByTagName("intent-filter")
            intentFilters.item(0).apply {
                document.createElement("category").apply {
                    setAttribute("android:name", "android.intent.category.LEANBACK_LAUNCHER")
                }
            }
        }
    }
}