package appetite.com.ui.fragments.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import appetite.com.R
import appetite.com.data.network.responses.Articles
import appetite.com.databinding.ItemNewsBinding
import appetite.com.ui.MainActivity

class NewsAdapter(private val news: List<Articles>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(val itemNewsBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemNewsBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemNewsBinding.viewmodel = news[position]
        holder.itemNewsBinding.mainRoot.setOnClickListener {
            startActivity(it.context, Intent(it.context, MainActivity::class.java), null)
        }
    }
}