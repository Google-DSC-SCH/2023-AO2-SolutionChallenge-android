package com.ao2.run_eat.ui.setProfile.bottom

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.FrameLayout
import android.widget.NumberPicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.ao2.run_eat.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class BottomAgeNumberPicker(
    val callback: (age: AgeNumberPicker) -> Unit
) : BottomSheetDialogFragment(

) {
    private lateinit var dlg: BottomSheetDialog

    var ageYear: String? = null
    var ageMonth: String? = null
    var ageDate: String? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면 XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = (super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet =
                    findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.isDraggable = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }) as BottomSheetDialog
        return dlg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(
            R.layout.bottom_sheet_age_number_picker_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sendReservationToolbar = requireView().findViewById<Toolbar>(R.id.toolbar)
        sendReservationToolbar.setNavigationOnClickListener { dismiss() }

        val npYear = requireView().findViewById<NumberPicker>(R.id.np_year)
        val npMonth = requireView().findViewById<NumberPicker>(R.id.np_month)
        val npDate = requireView().findViewById<NumberPicker>(R.id.np_date)


        val yearList = (1970..2025).toList()
        val monthList = (1..12).toList()
        val dateList = (1..31).toList()

        val yearStrConvertList = yearList.map { it.toString() }
        val monthStrConvertList = monthList.map { it.toString() }
        val dateStrConvertList = dateList.map { it.toString() }

        npYear.run {
            minValue = 0
            maxValue = yearStrConvertList.size - 1
            wrapSelectorWheel = false
            displayedValues = yearStrConvertList.toTypedArray()
        }

        npMonth.run {
            minValue = 0
            maxValue = monthStrConvertList.size - 1
            wrapSelectorWheel = false
            displayedValues = monthStrConvertList.toTypedArray()
        }

        npDate.run {
            minValue = 0
            maxValue = dateStrConvertList.size - 1
            wrapSelectorWheel = false
            displayedValues = dateStrConvertList.toTypedArray()
        }

        val thisTimeSendCardView =
            requireView().findViewById<CardView>(R.id.this_time_send_card_view)

        thisTimeSendCardView.setOnClickListener {
            callback.invoke(
                AgeNumberPicker(
                    year = npYear.value.toString(),
                    month = npMonth.value.toString(),
                    date = npDate.value.toString()
                )
            )
            dismiss()
        }
    }
}

data class AgeNumberPicker(
    val year: String,
    val month: String,
    val date: String
)
