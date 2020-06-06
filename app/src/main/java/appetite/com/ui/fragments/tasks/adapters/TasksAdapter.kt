package appetite.com.ui.fragments.tasks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import appetite.com.R
import appetite.com.data.network.responses.MainTasksItem
import appetite.com.databinding.ItemMainTasksBinding

class TasksAdapter(
    private val tasks: MutableList<MainTasksItem>
) :
    RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    inner class TasksViewHolder(val itemMainTasksBinding: ItemMainTasksBinding) :
        RecyclerView.ViewHolder(itemMainTasksBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TasksViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_main_tasks,
            parent,
            false
        )
    )


    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.itemMainTasksBinding.model = tasks[position]
    }
}