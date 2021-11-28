package ru.otkrytie.startinvest.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.NewsFragmentBinding
import ru.otkrytie.startinvest.ui.profile.PostListAdapter

class NewsFragment : Fragment() {
    private var _binding: NewsFragmentBinding? = null
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsListAdapter: PostListAdapter
    private lateinit var subsListAdapter: SubsListAdapter

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsListAdapter = PostListAdapter(false, PostListAdapter.OnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, NewsViewFragment.newInstance())
                .addToBackStack(null)
                .commit()
        })
        subsListAdapter = SubsListAdapter()
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setTitle(R.string.title_home)
        }

        val newsList = binding.rvNews
        newsList.adapter = newsListAdapter
        val dividerItemDecoration = DividerItemDecoration(newsList.context, LinearLayoutManager.VERTICAL)
        newsList.addItemDecoration(dividerItemDecoration)

        val subsList = binding.rvSubs
        subsList.adapter = subsListAdapter

        viewModel.allNewsData.observe(viewLifecycleOwner, {
            newsListAdapter.setItems(it)
            newsListAdapter.notifyDataSetChanged()
        })

        viewModel.allSubsData.observe(viewLifecycleOwner, {
            subsListAdapter.setItems(it)
            subsListAdapter.notifyDataSetChanged()
        })
    }

}