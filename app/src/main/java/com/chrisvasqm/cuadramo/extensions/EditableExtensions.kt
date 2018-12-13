package com.chrisvasqm.cuadramo.extensions

import android.text.Editable

fun Editable?.toInt(): Int = this.toString().toInt()
