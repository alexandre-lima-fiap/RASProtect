package com.ravensec.rasprotect;

import android.content.Context
import android.provider.Settings //Lib pra conferir se o modo desenvolvedor esta ativo nas config do celular

object DeveloperModeUtil {
// Método público para verificar se o modo de desenvolvedor está ativado
fun isDeveloperModeEnabled(context: Context): Boolean {
    return Settings.Secure.getInt(context.contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
}
}
// A função isDeveloperModeEnabled vai nos dizer se a opção de desenvolvedor está ativa
// baseada no valor booelano, 0 desatiavado e 1 (!= 0) ativado

