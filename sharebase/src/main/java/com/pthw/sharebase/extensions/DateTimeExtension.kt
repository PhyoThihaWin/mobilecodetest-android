package com.pthw.sharebase.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val DMY_DATE_FORMAT = "dd/MM/yyyy"

fun Date.dateFormatterDMY(format: String = DMY_DATE_FORMAT): String {
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    return formatter.format(this)
}
