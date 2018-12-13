package com.chrisvasqm.cuadramo.extensions

import android.text.Editable

fun Editable?.toInt(): Int =
        if (this.toString().isNotBlank()) this.toString().toInt()
        else 0
