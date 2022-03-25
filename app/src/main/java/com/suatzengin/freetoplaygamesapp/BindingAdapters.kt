package com.suatzengin.freetoplaygamesapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suatzengin.freetoplaygamesapp.data.network.GamesApiStatus


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String) {
    imgUrl.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }

}

@BindingAdapter("status")
fun bindStatus(imageView: ImageView, status: GamesApiStatus){

    when(status){
        GamesApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        GamesApiStatus.DONE -> {
            imageView.visibility = View.GONE
        }
        GamesApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}