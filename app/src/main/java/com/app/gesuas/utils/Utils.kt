package com.app.gesuas.utils

import android.content.Context
import android.content.Intent

fun nextActivity(context: Context, chosenClass: Class<*>) {
    val intent = Intent(context, chosenClass)
    context.startActivity(intent)
}

fun formatCPF(cpf: String): String {
    return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})".toRegex(), "$1.$2.$3-$4")
}
fun formatBrithDate(data: String): String {
    val dia = data.substring(0, 2)
    val mes = data.substring(2, 4)
    val ano = data.substring(4)

    return "$dia/$mes/$ano"
}

