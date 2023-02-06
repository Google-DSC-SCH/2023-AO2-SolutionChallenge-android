package com.AO2.run_eat.base

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textVisibleCheck")
fun TextView.setTextVisibleCheck(contents: String?) {
    if(contents == null) this.visibility = View.GONE
    else {
        this.visibility = View.VISIBLE
        this.text = contents
    }
}