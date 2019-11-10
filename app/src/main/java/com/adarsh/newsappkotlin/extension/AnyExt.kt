package com.adarsh.newsappkotlin.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.convertToJsonString(): String {
    return Gson().toJson(this) ?: ""
}

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: TypeToken<T>() {}.type)