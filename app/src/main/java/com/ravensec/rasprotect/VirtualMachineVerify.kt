package com.ravensec.rasprotect

import android.os.Build //Lib que usamos para a função isRunningOnEmulator

object VirtualMachineVerify {
    fun isRunningOnEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") ||
                Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK built for x86") ||
                Build.MANUFACTURER.contains("Genymotion") || Build.PRODUCT.contains("sdk_google") ||
                Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("google_sdk"))
    }
}

// Estamos procurando essas strings no sistema inteiro do celular