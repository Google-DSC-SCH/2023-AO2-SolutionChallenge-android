package com.ao2.run_eat.ui.setProfile

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView

fun textChangeColor(
    text: TextView,
    color: String,
    start: Int,
    end: Int
): SpannableStringBuilder {
    val builder = SpannableStringBuilder(text.text.toString())

    builder.setSpan(
        ForegroundColorSpan(Color.parseColor(color)),
        start,
        end,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return builder
}

@BindingAdapter("nicknameEditTextCount")
fun TextView.bindNicknameEditTextCount(textLength: Int) {
    this.text = "$textLength/15"
}

@BindingAdapter("ageEditTextCount")
fun TextView.bindAgeEditTextCount(textLength: Int) {
    this.text = "$textLength/8"
}

@BindingAdapter("manBtnClicked")
fun MaterialCardView.bindManBtnClicked(isMan: Boolean?) {
    if (isMan == true) this.strokeColor = Color.parseColor("#007680")
    else this.strokeColor = Color.parseColor("#d9d9d9")
}

@BindingAdapter("womanBtnClicked")
fun MaterialCardView.bindWomanBtnClicked(isMan: Boolean?) {
    if (isMan == false) this.strokeColor = Color.parseColor("#007680")
    else this.strokeColor = Color.parseColor("#d9d9d9")
}