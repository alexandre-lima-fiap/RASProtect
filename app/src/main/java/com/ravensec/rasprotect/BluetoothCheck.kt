package com.ravensec.rasprotect

import android.bluetooth.BluetoothAdapter //Lib que vai verificar o state do bluetooth

object BluetoothCheck {
    fun isBluetoothEnabled(): Boolean {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        return bluetoothAdapter?.isEnabled == true
    }
}

//fun ta pegando variavel booleana do estado do bluetooth adapter, e vai retornar se é true ou false