package com.ravensec.rasprotect

import android.os.Build
import java.io.File

object RootCheckUtil {

    // Verifica se o arquivo binário especificado está presente nos seguintes diretórios
    private fun checkForBinary(binaryName: String): Boolean {
        val paths = arrayOf(
            "/sbin/", "/system/bin/", "/system/xbin/",
            "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/",
            "/system/bin/failsafe/", "/data/local/"
        )

        return paths.any { path ->
            File(path + binaryName).exists()
        }
    }


    // Verifica a presença de comandos comuns para root, incluindo su, busybox, magisk, supersu, koush, etc.
    private fun checkForRootCommands(): Boolean {
        val commands = arrayOf("su", "busybox", "magisk", "supersu", "koush", "su_daemon", "frida-server")
        return commands.any { command ->
            checkForBinary(command)
        }
    }

    // Verifica a presença de arquivos comuns indicativos de root
    private fun checkForFiles(): Boolean {
        val files = arrayOf(
            "/system/app/Superuser.apk", "/system/etc/init.d/", "/system/bin/.ext/.su",
            "/system/xbin/ku.sud", "/data/local/tmp/frida-server"
        )

        return files.any { filePath ->
            File(filePath).exists()
        }
    }

    private fun checkRootAccess(): Boolean {
        val commands = arrayOf("su", "magisk", "busybox", "supersu", "frida-server")
        return commands.any { command ->
            try {
                val process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", command))
                val input = process.inputStream
                val output = StringBuffer()

                input.bufferedReader().use { reader ->
                    reader.forEachLine { line ->
                        output.append(line)
                    }
                }

                output.toString().isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }
    }

    // Método público para verificar se o dispositivo está rooteado
    fun isDeviceRooted(): Boolean {
        return checkForRootCommands() || checkRootAccess() || isTestKeysBuild()
    }

    // Verifica se o dispositivo está usando uma versão de build de teste
    private fun isTestKeysBuild(): Boolean {
        return Build.TAGS != null && Build.TAGS.contains("test-keys")
    }
}
