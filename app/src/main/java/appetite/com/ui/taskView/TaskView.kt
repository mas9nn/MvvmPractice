package appetite.com.ui.taskView

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import appetite.com.R
import appetite.com.databinding.ActivityTaskViewMainBinding
import appetite.com.ui.fragments.tasks.TasksViewModel
import appetite.com.ui.fragments.tasks.TasksViewModelFactory
import kotlinx.android.synthetic.main.activity_task_view_main.*
import kotlinx.android.synthetic.main.item_news.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TaskView : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    private val factory: TasksViewModelFactory by instance(arg = "https://orzu.org")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTaskViewMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_task_view_main)
        val viewModel = ViewModelProvider(this,factory).get(TasksViewModel::class.java)
        card_of_task_view.setBackgroundResource(R.drawable.shape_card_topcorners)
        viewModel.getTasksById()
        viewModel.getUser()
        viewModel.task.observe(this, Observer {
            binding.model = it
        })
    }
    fun close(view:View){
        finish()
    }
}
