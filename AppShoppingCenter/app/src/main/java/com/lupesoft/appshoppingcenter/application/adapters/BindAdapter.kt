package com.lupesoft.appshoppingcenter.application.adapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lupesoft.appshoppingcenter.BuildConfig.BaseUrlImage
import com.lupesoft.appshoppingcenter.domain.entitys.Movie

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}


@BindingAdapter("setDataMovies")
fun setDataMovies(recycler: RecyclerView, data: List<Movie>?) {
    data?.also {
        (recycler.adapter as MovieAdapter).submitList(it)
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(BaseUrlImage + url)
            .override(200, 300)
            .into(view)
    }
}

@BindingAdapter("imageUrlDetail")
fun loadImageDetail(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(BaseUrlImage + url)
            .override(600, 700)
            .into(view)
    }
}

