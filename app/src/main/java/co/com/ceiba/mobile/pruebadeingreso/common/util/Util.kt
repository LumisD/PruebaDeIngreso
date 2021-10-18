package co.com.ceiba.mobile.pruebadeingreso.common.util

import co.com.ceiba.mobile.pruebadeingreso.common.AppConfig.previousClickTimeMillis
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.LONG_DELAY
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.SHORT_DELAY

fun isClickedSingle(): Boolean {
    return isAlreadyClick(LONG_DELAY)
}


fun isClickedShort(): Boolean {
    return isAlreadyClick(SHORT_DELAY)
}


fun isAlreadyClick(time: Long): Boolean {
    val currentTimeMillis = System.currentTimeMillis()
    if (currentTimeMillis >= previousClickTimeMillis + time) {
        previousClickTimeMillis = currentTimeMillis
        return false
    } else {
        return true
    }
}