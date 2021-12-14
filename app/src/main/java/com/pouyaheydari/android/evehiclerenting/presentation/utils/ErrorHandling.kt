package com.pouyaheydari.android.evehiclerenting.presentation.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.pouyaheydari.android.evehiclerenting.R

fun showGeneralError(view: View) {
    Snackbar.make(view, view.context.getString(R.string.please_try_again), Snackbar.LENGTH_LONG)
        .show()
}