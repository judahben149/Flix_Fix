package com.judahben149.flixfix.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.Locale

object Extensions {

    fun String.parseFriendlyDate(): String {

        if (this != null) {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = inputFormat.parse(this)

                val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
                return outputFormat.format(date!!)
        } else {
            return "none"
        }
    }

    fun Int.toRunTimeString(): String {
        var hours = 0
        var minutes = 0

        return if (this >= 60) {
            hours = this / 60
            minutes = this % 60
            "${hours.toString()}h, ${minutes.toString()}m"
        }
        else {
            minutes = this
            "${minutes.toString()}m"
        }

    }

    fun ImageView.loadImage(context: Context, url: String, onLoadingComplete: () -> Unit) {
        Glide.with(context).load(url).addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadingComplete()
                return false
            }

        }).into(this)

    }
}