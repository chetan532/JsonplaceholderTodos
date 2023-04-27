package com.jsontodos.jsonplaceholdertodos.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.animateAndShow() {
    visibility = View.VISIBLE
    alpha = 0.0f

    animate().setStartDelay(400)
        .translationY(0f)
        .alpha(1f)
        .setListener(null)
}