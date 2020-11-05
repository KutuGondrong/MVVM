package com.kutugondrong.kutugondronggithub.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager


var ASC = "Asc"
var DESC = "Desc"
var MESSAGEENDLESS = "Sudah di akhir"
var NODATA = "Waduh sial datanya gak ada"

fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager: InputMethodManager = activity.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus!!.windowToken, 0
    )
}