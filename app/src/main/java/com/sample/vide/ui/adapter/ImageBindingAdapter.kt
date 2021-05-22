package com.sample.vide.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sample.vide.R


class ImageBindingAdapter {


    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImage: String?) {
            profileImage?.let {
                Glide
                    .with(view.context)
                    .load(it)
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(view)
            }
        }
    }

}
