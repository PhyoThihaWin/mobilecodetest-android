package com.pthw.sharebase.extensions

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.pthw.sharebase.R

fun FragmentManager.showDateTimePicker(
    title: String? = null,
    start: Long? = null,
    before: Long? = null,
    after: Long? = null,
    onClickOK: (Long) -> Unit
) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
    datePicker.setTheme(R.style.Widget_AppTheme_MaterialDatePicker)

    title?.let {
        datePicker.setTitleText(title)
    }

    val constraints = CalendarConstraints.Builder()
    start?.let {
        constraints.setOpenAt(it)
    }
    before?.let {
        constraints.setValidator(DateValidatorPointBackward.before(it))
    }
    after?.let {
        constraints.setValidator(DateValidatorPointForward.from(it))
    }
    datePicker.setCalendarConstraints(constraints.build())

    val builder = datePicker.build()
    builder.addOnPositiveButtonClickListener {
        onClickOK.invoke(it)
    }

    builder.show(this, "date_picker")
}