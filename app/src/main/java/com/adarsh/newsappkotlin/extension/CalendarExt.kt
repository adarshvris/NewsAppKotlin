package com.adarsh.newsappkotlin.extension

import java.text.SimpleDateFormat
import java.util.*

val ISOINSTANT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

fun getReadableDate(date: String?): String {

    if (date.isNullOrEmpty())
        return ""

    val sdf = ISOINSTANT
    val sdfDate = sdf.parse(date)
    val displaySdf = getSimpleDateFormat()

    return displaySdf.format(sdfDate).toString()

}

fun getSimpleDateFormat(): SimpleDateFormat {
    return SimpleDateFormat("dd-MM-YYYY HH:mm:ss", Locale.ENGLISH)
}