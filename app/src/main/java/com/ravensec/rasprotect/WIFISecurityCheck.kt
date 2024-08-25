package com.ravensec.rasprotect

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.net.wifi.ScanResult

object WiFiSecurityCheck {

    fun getConnectedNetworkSecurityType(context: Context): String {
        val wifiManager = context.getSystemService(WIFI_SERVICE) as WifiManager
        if (!wifiManager.isWifiEnabled) {
            return "Wi-Fi desligado"
        }

        val currentNetwork = wifiManager.connectionInfo
        val networkSSID = currentNetwork.ssid.replace("\"", "")

        val scanResults = wifiManager.scanResults
        val connectedNetwork = scanResults.find { it.SSID == networkSSID }

        return if (connectedNetwork != null) {
            getSecurityType(connectedNetwork)
        } else {
            "Nenhuma rede conectada encontrada"
        }
    }

    private fun getSecurityType(scanResult: ScanResult): String {
        val capabilities = scanResult.capabilities
        return when {
            capabilities.contains("WPA3") -> "WPA3"
            capabilities.contains("WPA2") -> "WPA2"
            capabilities.contains("WPA") -> "WPA"
            capabilities.contains("WEP") -> "WEP"
            else -> "Desconhecida"
        }
    }
}

// a função getConnectedNetworkSecurityType vai trazer se o wifi esta ligado ou não, o estado
// da conexão, o SSID da rede conectada
// a função getSecurityType vai trazer o tipo de segurança da rede, informada no getConnectedNetworkSecurityType