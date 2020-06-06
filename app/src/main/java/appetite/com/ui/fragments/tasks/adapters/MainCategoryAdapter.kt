package appetite.com.ui.fragments.tasks.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import appetite.com.R
import appetite.com.data.network.responses.MainCategory
import appetite.com.databinding.ItemCategoryMainBinding
import appetite.com.ui.fragments.tasks.TasksViewModel

class MainCategoryAdapter(
    private val categories: MutableList<MainCategory>,
    private val viewmodel: TasksViewModel
) :
    RecyclerView.Adapter<MainCategoryAdapter.TasksViewHolder>() {
    var index: Int = 0

    inner class TasksViewHolder(val itemCategoryMainBinding: ItemCategoryMainBinding) :
        RecyclerView.ViewHolder(itemCategoryMainBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TasksViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category_main,
            parent,
            false
        )
    )


    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.itemCategoryMainBinding.model = categories[position].also {
            it.color = position
        }
        holder.itemCategoryMainBinding.viewmodel = viewmodel
        if (index == position) {
            holder.itemCategoryMainBinding.categoryName.setTextColor(Color.parseColor("#FF9100"))
        } else {
            holder.itemCategoryMainBinding.categoryName.setTextColor(Color.parseColor("#000000"))
        }
    }

    fun changeColor(position: Int) {
        index = position
        notifyDataSetChanged()
    }
}