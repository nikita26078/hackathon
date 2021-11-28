package ru.otkrytie.startinvest.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.NewsViewFragmentBinding

class NewsViewFragment : Fragment() {
    private var _binding: NewsViewFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NewsViewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle(R.string.title_home)
        }

        binding.tvAuthor.text = "John Smith"
        binding.tvBody.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
        }
        return true
    }

}