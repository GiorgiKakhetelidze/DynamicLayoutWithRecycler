package com.example.shemajamebeli5.extensions

import android.graphics.Color
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.markRequired(required: Boolean) {
    if (required) {
        hint = buildSpannedString {
            append(hint)
            color(Color.RED) {
                append(" *")
            }
        }
    }
}


