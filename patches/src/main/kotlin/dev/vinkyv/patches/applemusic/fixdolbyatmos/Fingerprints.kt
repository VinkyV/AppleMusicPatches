package dev.vinkyv.patches.applemusic.fixdolbyatmos

import app.revanced.patcher.accessFlags
import app.revanced.patcher.composingFirstMethod
import app.revanced.patcher.definingClass
import app.revanced.patcher.patch.BytecodePatchContext
import app.revanced.patcher.strings
import com.android.tools.smali.dexlib2.AccessFlags

private const val AUDIODEVICE_CAPABILITIES_CLASS = $$"Lcom/apple/android/music/util/AudioDeviceCapabilities$Companion;"

internal val BytecodePatchContext.isDolbyDigitalPlus by composingFirstMethod {
    definingClass(AUDIODEVICE_CAPABILITIES_CLASS)
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    strings("audio/eac3-joc")
}