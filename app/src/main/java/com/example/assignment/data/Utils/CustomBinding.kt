package com.example.assignment.data.Utils

import android.view.View
import androidx.databinding.BindingAdapter

// creating an binding adapter for show and hide the progressbar
class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("show")
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}