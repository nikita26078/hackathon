package ru.otkrytie.startinvest.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.CreatePostFragmentBinding

class CreatePostFragment : Fragment() {
    private var _binding: CreatePostFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CreatePostFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreatePostFragmentBinding.inflate(inflater, container, false)
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
            actionBar.setTitle(R.string.create)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
        }
        return true
    }

}
