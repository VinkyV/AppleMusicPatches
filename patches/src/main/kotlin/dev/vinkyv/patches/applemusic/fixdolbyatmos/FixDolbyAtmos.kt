package dev.vinkyv.patches.applemusic.fixdolbyatmos

import app.revanced.patcher.extensions.instructions
import app.revanced.patcher.extensions.replaceInstruction
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode;
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

@Suppress("unused")
val fixDolbyAtmos = bytecodePatch(
    name = "Fix Dolby Atmos for TV",
    description = "Replaces audio/eac3-joc to audio/eac3 to make it working on TV!",
) {
    compatibleWith("com.apple.android.music")

    apply {
        isDolbyDigitalPlus.apply {
            method.instructions.filter { it.opcode == Opcode.CONST_STRING }.forEach {
                val constStringIndex = it.location.index
                val constStringRegister = (it as OneRegisterInstruction).registerA

                method.replaceInstruction(
                    constStringIndex,
                    "const-string v$constStringRegister, \"audio/eac3\""
                )
                println("Replaced audio/eac3-joc -> audio/eac3")
            }
        }
    }
}