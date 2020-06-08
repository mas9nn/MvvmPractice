package appetite.com.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import appetite.com.R
import appetite.com.data.network.responses.Articles
import appetite.com.databinding.ActivityDetailsBinding
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    var articles = Articles()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_details)
        val author = intent.getStringExtra("author")
        articles.title = intent.getStringExtra("title")
        articles.publishedAt = intent.getStringExtra("time")
        articles.description = intent.getStringExtra("description")
        articles.urlToImage = intent.getStringExtra("image")
        articles.url = intent.getStringExtra("site")
        articles.author = author
        binding.model = articles

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        app_bar_layout_home.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                toolbar_author.setText(author)
                back.setColorFilter(Color.argb(0, 0, 0, 0))
            } else {
                //Expanded
                toolbar_author.setText("")
                back.setColorFilter(Color.argb(255, 255, 255, 255))
            }
        })
        author_details.setOnClickListener {
            val builder =
                AlertDialog.Builder(this@DetailsActivity)
            builder.setMessage("Open this news on the site?")
                .setCancelable(false)
                .setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(articles.url)
                    startActivity(i)
                    dialog.dismiss()
                }.setNegativeButton(
                    "Cancel"
                ) { dialog, which -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }
    }
}