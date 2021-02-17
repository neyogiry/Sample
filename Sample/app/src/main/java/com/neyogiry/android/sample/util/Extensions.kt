package com.neyogiry.android.sample.util

import android.app.Activity
import android.widget.Toast

/**
 * Extensions
 */

fun Activity.toast(resource: Int) {
    toast(getString(resource))
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}