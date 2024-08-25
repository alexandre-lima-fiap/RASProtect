package com.ravensec.rasprotect

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewRootResult: TextView = findViewById(R.id.textViewRootResult)
        val textViewDeveloperModeResult: TextView = findViewById(R.id.textViewDeveloperModeResult)
        val textViewDebuggerCheckResult: TextView = findViewById(R.id.textViewDebuggerCheckResult)
        val textViewVMCheckResult: TextView = findViewById(R.id.textViewVMCheckResult)
        val textViewBluetoothResult: TextView = findViewById(R.id.textViewBluetoothResult)
        val textViewUsbResult: TextView = findViewById(R.id.textViewUsbResult)
        val textViewUsbHostResult: TextView = findViewById(R.id.textViewUsbHostResult)
        val textViewWifiSecurity: TextView = findViewById(R.id.textViewWifiSecurity)

        checkPermissions()

        val resultText = if (RootCheckUtil.isDeviceRooted()) {
            "O dispositivo está rooteado"
        } else {
            "O dispositivo não está rooteado"
        }

        val developerModeText = if (DeveloperModeUtil.isDeveloperModeEnabled(this)) {
            "O modo de desenvolvedor está ativado"
        } else {
            "O modo de desenvolvedor não está ativado"
        }

        val debuggerText = if (DebuggerCheck.isDebuggerActive()) {
            "O debugger está ativado"
        } else {
            "O debugger não está ativado"
        }

        val vmText = if (VirtualMachineVerify.isRunningOnEmulator()) {
            "O aplicativo está rodando em uma máquina virtual"
        } else {
            "O aplicativo não está rodando em uma máquina virtual"
        }

        val bluetoothStatusText = if (BluetoothCheck.isBluetoothEnabled()) {
            "O Bluetooth está ativado"
        } else {
            "O Bluetooth não está ativado"
        }

        val usbStatusText = if (UsbCheck.isUsbConnected(this)) {
            "Há dispositivos USB conectados"
        } else {
            "Não há dispositivos USB conectados"
        }

        val usbHostStatusText = if (UsbCheck.isUsbHostSupported(this)) {
            "O host USB está ativo"
        } else {
            "O host USB não está ativo"
        }

        val wifiSecurityText = WiFiSecurityCheck.getConnectedNetworkSecurityType(this)

        textViewRootResult.text = resultText
        textViewDeveloperModeResult.text = developerModeText
        textViewDebuggerCheckResult.text = debuggerText
        textViewVMCheckResult.text = vmText
        textViewBluetoothResult.text = bluetoothStatusText
        textViewUsbResult.text = usbStatusText
        textViewUsbHostResult.text = usbHostStatusText
        textViewWifiSecurity.text = wifiSecurityText
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_CODE)
        }
    }
}
