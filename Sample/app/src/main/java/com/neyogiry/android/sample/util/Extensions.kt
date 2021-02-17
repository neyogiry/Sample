package com.neyogiry.android.sample.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

fun Activity.launch(newActivity: Class<*>) {
    launch(newActivity, null, false)
}

fun Activity.launch(newActivity: Class<*>, bundle: Bundle?) {
    launch(newActivity, bundle, false)
}

fun Activity.launch(newActivity: Class<*>, destroy: Boolean) {
    launch(newActivity, null, destroy)
}

fun Activity.launch(newActivity: Class<*>, bundle: Bundle?, destroy: Boolean) {
    val intent = Intent(this, newActivity)
    bundle?.let { intent.putExtras(bundle) }
    this.startActivity(intent)
    if (destroy) this?.finish()
}