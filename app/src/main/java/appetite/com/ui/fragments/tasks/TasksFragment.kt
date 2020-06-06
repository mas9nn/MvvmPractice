package appetite.com.ui.fragments.tasks

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import appetite.com.R
import appetite.com.data.network.responses.MainCategory
import appetite.com.data.network.responses.MainTasksItem
import appetite.com.databinding.TasksFragmentBinding
import appetite.com.ui.fragments.tasks.adapters.CategoryAdapter
import appetite.com.ui.fragments.tasks.adapters.MainCategoryAdapter
import appetite.com.ui.fragments.tasks.adapters.TasksAdapter
import kotlinx.android.synthetic.main.tasks_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TasksFragment : Fragment(), TaskListener, KodeinAware {

    override val kodein by kodein()

    private val factory: TasksViewModelFactory by instance(arg = "https://orzu.org")


    private lateinit var viewModel: TasksViewModel
    private lateinit var adapter: MainCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TasksFragmentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.tasks_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(TasksViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.listener = this
        viewModel.getCategories()
        viewModel.categories.observe(requireActivity(), Observer { categories ->
            categories.add(0, MainCategory("0", "Все категорий", "0"))
            category_main_rv.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.setHasFixedSize(true)
                adapter = MainCategoryAdapter(categories, viewModel)
                it.adapter = adapter
            }
        })
        viewModel.getSubCategories(0, "0")
        viewModel.getTasks()
        return binding.root
    }

    override fun onSuccess(data: MutableList<MainCategory>, position: Int) {
        subcategory_main_rv.also {
            it.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.setHasFixedSize(true)
            it.adapter = CategoryAdapter(data)
            adapter.changeColor(position)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onSuccessTasks(data: MutableLiveData<MutableList<MainTasksItem>>) {
        data.observe(requireActivity(), Observer { list ->
            rv.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = TasksAdapter(list)
                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        val view1 =
                            it.getChildAt(it.childCount - 1) as View
                        val diff =
                            view1.bottom - (it.height + it
                                .scrollY)
                        if (diff <= 0) {
                            viewModel.getTasks()
                        }
                    }
                })
            }
        })
    }

}
