package ru.otkrytie.startinvest.ui.profile

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
import ru.otkrytie.startinvest.databinding.ProfileFragmentBinding
import ru.otkrytie.startinvest.ui.news.NewsViewFragment
import ru.otkrytie.startinvest.ui.settings.SettingsFragment

class ProfileFragment : Fragment() {
    private var _binding: ProfileFragmentBinding? = null
    private lateinit var adapter: PostListAdapter
    private lateinit var viewModel: ProfileViewModel

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = PostListAdapter(true, PostListAdapter.OnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, NewsViewFragment.newInstance())
                .addToBackStack(null)
                .commit()
        })
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(
            ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
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
            actionBar.setTitle(R.string.profile)
        }

        val list = binding.rvPosts
        list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayoutManager.VERTICAL)
        list.addItemDecoration(dividerItemDecoration)
        binding.ivSettings.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, SettingsFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, CreatePostFragment())
                .addToBackStack(null)
                .commit()
        }
        viewModel.allNewsData.observe(viewLifecycleOwner, {
            binding.tvPostCount.text = it.size.toString()
            adapter.setItems(it)
            adapter.notifyDataSetChanged()
        })
    }

}
