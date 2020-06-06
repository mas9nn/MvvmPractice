package appetite.com.ui.fragments.tasks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import appetite.com.R
import appetite.com.data.network.responses.MainCategory
import appetite.com.databinding.ItemSubcategoryMainBinding
import java.util.*

class CategoryAdapter(private val categories: MutableList<MainCategory>) :
    RecyclerView.Adapter<CategoryAdapter.SubCategoryAdapter>() {
    var backs = intArrayOf(
        R.drawable.subcategory_back1,
        R.drawable.subcategory_back2,
        R.drawable.subcategory_back3,
        R.drawable.subcategory_back4,
        R.drawable.subcategory_back5,
        R.drawable.subcategory_back6
        ,
        R.drawable.subcategory_back7,
        R.drawable.subcategory_back8,
        R.drawable.subcategory_back9,
        R.drawable.subcategory_back10,
        R.drawable.subcategory_back11
    )
    var last_index = -1
    inner class SubCategoryAdapter(val itemSubcategoryMainBinding: ItemSubcategoryMainBinding) :
        RecyclerView.ViewHolder(itemSubcategoryMainBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubCategoryAdapter(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_subcategory_main,
            parent,
            false
        )
    )


    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: SubCategoryAdapter, position: Int) {
        holder.itemSubcategoryMainBinding.model = categories[position]
        holder.itemSubcategoryMainBinding.subcategoryBackItem.setImageResource(backs.get(randomInt()))
    }
    private fun randomInt(): Int {
        val random = Random()
        var rand = random.nextInt(backs.size)
        while (true) {
            if (rand != last_index) {
                last_index = rand
                return rand
            }
            rand = random.nextInt(6)
        }
    }
}