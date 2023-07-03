package com.tnt.food_delivery.core.extensions

fun String.isPhoneNumber(): Boolean {
    val pattern = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$".toRegex()
    return !pattern.matches(this)
}