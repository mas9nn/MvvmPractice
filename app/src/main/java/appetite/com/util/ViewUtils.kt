package appetite.com.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

@BindingAdapter("image")
fun loadImage(view:ImageView,url:String){
    Glide.with(view).load(url).into(view)
}

