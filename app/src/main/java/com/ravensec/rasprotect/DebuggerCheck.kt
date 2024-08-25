package com.ravensec.rasprotect

import android.os.Debug //Lib pra verificar se esta em debug ou nao
object DebuggerCheck {

    fun isDebuggerActive(): Boolean {
        return Debug.isDebuggerConnected()
    }
}

// a função verifica se o app esta sendo debugado de acordo com uma varaivel booleana dada pela lib