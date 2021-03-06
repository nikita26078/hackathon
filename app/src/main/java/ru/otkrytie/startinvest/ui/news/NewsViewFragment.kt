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
import ru.otkrytie.startinvest.ui.investments.InvestmentViewFragment

class NewsViewFragment : Fragment() {
    private var _binding: NewsViewFragmentBinding? = null
    private var userId = 0

    private val binding get() = _binding!!

    companion object {
        const val ITEM_ID = "item_id"

        fun newInstance(id: Int) = NewsViewFragment().apply {
            arguments = Bundle(1).apply {
                putInt(ITEM_ID, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getInt(InvestmentViewFragment.ITEM_ID, -1)
        if (userId == -1) {
            userId = 0
        }
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