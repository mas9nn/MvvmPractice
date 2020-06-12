package appetite.com.ui.fragments.profile

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import appetite.com.R
import appetite.com.databinding.ProfileFragmentBinding
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware, Toolbar.OnMenuItemClickListener {

    override val kodein by kodein()

    private val factory: ProfileViewModelFactory by instance(arg = "https://newsapi.org/v2/")


    private lateinit var viewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding: ProfileFragmentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.profile_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.homeToolbar.setOnMenuItemClickListener(this)
        viewModel.getNews()
        viewModel.news.observe(requireActivity(), Observer { art ->
            recycler_view.also {

                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = NewsAdapter(art.articles)
                Log.wtf("sadasd", art.totalResults)
                val messageSwipeController = Helper(requireContext(), object : actions {
                    override fun showReplyUI(position: Int) {
                        Log.wtf("asdas",position.toString())
                    }
                })
                val itemTouchHelper = ItemTouchHelper(messageSwipeController)
                itemTouchHelper.attachToRecyclerView(it)
            }

        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.news_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.russia -> viewModel.getNews("ru")
            R.id.usa -> viewModel.getNews("us")
            R.id.italy -> viewModel.getNews("it")
        }
        return true
    }
}
