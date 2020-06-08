package appetite.com.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Picasso.get().load(url).fetch(object : Callback {
            override fun onSuccess() {
                Picasso.get().load(url).into(view)
            }
            override fun onError(e: Exception) {}
        })
    }
}

