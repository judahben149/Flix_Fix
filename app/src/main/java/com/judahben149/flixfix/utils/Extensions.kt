package com.judahben149.flixfix.utils

import android.icu.text.SimpleDateFormat
import java.util.Locale

object Extensions {

    fun parseFriendlyDate(inputDate: String?): String {

        if (inputDate != null) {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = inputFormat.parse(inputDate)

                val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
                return outputFormat.format(date!!)
        } else {
            return "none"
        }
    }
}