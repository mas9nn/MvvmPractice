package appetite.com.ui.fragments.tasks.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import appetite.com.R
import appetite.com.data.network.responses.MainTasksItem
import appetite.com.databinding.ItemMainTasksBinding
import appetite.com.ui.main.MainActivity
import appetite.com.ui.taskView.TaskView

class TasksAdapter(
    private var tasks: MutableList<MainTasksItem>
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

    fun addItems(items:MutableList<MainTasksItem>){
        tasks = items
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.itemMainTasksBinding.model = tasks[position]
        holder.itemMainTasksBinding.root.setOnClickListener {
            startActivity(it.context, Intent(it.context, TaskView::class.java), null)
        }
    }
}