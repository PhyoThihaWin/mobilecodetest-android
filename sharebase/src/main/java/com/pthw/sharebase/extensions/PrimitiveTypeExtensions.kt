package com.pthw.sharebase.extensions

import android.os.Build
import android.text.Html
import android.util.Base64
import android.util.Patterns
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

private const val CHAR_CODE = 4112
fun String.toMMNum() = this.fold("") { acc, c ->
    if (c in '0'..'9') {
        // acc + ((c.toInt() - '0'.toInt()) + '၀'.toInt())
        acc + (c.toInt() + CHAR_CODE).toChar()
    } else {
        acc + c
    }
}

fun Int.toMMNum() = this.toString().fold("") { acc, c ->
    if (c in '0'..'9') {
        // acc + ((c.toInt() - '0'.toInt()) + '၀'.toInt())
        acc + (c.toInt() + CHAR_CODE).toChar()
    } else {
        acc + c
    }
}


fun String.toDayMonthYearTime(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val dateSdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    val simpleDate = simpleDateFormat.parse(this)

    return dateSdf.format(simpleDate)
}


fun String.toLastSixDigitNrcCode(): String {
    var code = this
    code = code.substring(code.length - 6, code.length)
    return code
}

fun String.toLanguageFormat(): String {
    return if (this.isNotEmpty()) {
        var value = this.replace("[", "")
            .replace("]", "")
            .replace("\\t", "")
            .replace(",", "/")
            .replace("\"", "")

        if (value.equals("null", true)) {
            value = ""
        }
        value
    } else this
}

fun String.toAppointmentTypeForApiCall(): String {
    return if (this.uppercase() == "ONLINE") this.uppercase()
    else "INPERSON"
}


fun String.setSeparator(format: String = "#,###.##"): String {
    return runCatching {
        DecimalFormat(format).format(this.toDouble())
    }.getOrElse { "0" }
}

fun Int.setSeparator(format: String = "#,###.##"): String =
    DecimalFormat(format).format(this.toDouble())

fun Double.setSeparator(format: String = "#,###.##"): String = DecimalFormat(format).format(this)

fun Long.setSeparator(format: String = "#,###.##"): String = DecimalFormat(format).format(this)


fun <T> List<T>.toArrayList() = this.toCollection(ArrayList())

fun String.isAllUpperCase(): Boolean {
    var isUppercase = false
    forEach {
        isUppercase = it.isUpperCase()
    }
    return isUppercase
}

fun String.replaceLetterUppercase(): String {
    val stringBuilder = StringBuilder()
    forEachIndexed { i, s ->
        val modifiedChar = if (i == 0) {
            s
        } else {
            s.lowercaseChar()
        }
        stringBuilder.append(modifiedChar)
    }
    return stringBuilder.toString()
}

fun String.trimZero(add9: Boolean = false): String {
    return if (add9) {
        "9${if (this.startsWith('0')) this.substring(1) else this}"
    } else {
        if (this.startsWith('0')) this.substring(1) else this
    }
}

fun String.with95(): String {
    return "95$this"
}

fun String.toSupportHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
else Html.fromHtml(this)

fun String.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()



val String.encodedText get() = Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)

fun String?.whenValue(job: (String) -> Unit) {
    if (!this.isNullOrEmpty()) job.invoke(this)
}

fun Int.convertSecondsToMinutesAndSeconds(): Pair<Int, Int> {
    val minutes = this / 60
    val remainingSeconds = this % 60
    return Pair(minutes, remainingSeconds)
}

fun Pair<Int, Int>.toMinutesSeconds(): String {
    return "$first:${String.format(Locale.ENGLISH, "%02d", second)}"
}
