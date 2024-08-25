package com.ravensec.rasprotect

import android.content.Context
import android.hardware.usb.UsbManager //Lib para verificar se tem algum USB conectado e se o host USB esta ativo

object UsbCheck {
    fun isUsbConnected(context: Context): Boolean {
        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList = usbManager.deviceList
        return deviceList.isNotEmpty()
    }

    fun isUsbHostSupported(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_USB_HOST)
    }
}

