package com.lupesoft.appshoppingcenter.application.extensions

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lupesoft.appshoppingcenter.R

fun String.showMessage(context: Context) {
    MaterialAlertDialogBuilder(context)
        .setTitle(context.resources.getString(R.string.info))
        .setMessage(this)
        .setPositiveButton(context.resources.getString(R.string.confirm), null)
        .show()
}

