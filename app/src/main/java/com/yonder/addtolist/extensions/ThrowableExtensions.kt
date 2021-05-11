package com.yonder.addtolist.extensions

/**
 * Yusuf Onder on 09,May,2021
 */

private const val UNKNOWN_ERROR = "UNKNOWN ERROR"

fun Throwable?.toReadableMessage(): String {
  return this?.message ?: this?.localizedMessage ?: UNKNOWN_ERROR
}