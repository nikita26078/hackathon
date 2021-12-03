package ru.otkrytie.startinvest.ui.investments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.InvestFragmentBinding
import ru.otkrytie.startinvest.utils.Constants


class InvestmentFragment : Fragment() {
    private var _binding: InvestFragmentBinding? = null
    private lateinit var adapter: InvestmentListAdapter
    private lateinit var sp: SharedPreferences
    private lateinit var viewModel: InvestmentViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = InvestmentListAdapter(InvestmentListAdapter.OnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, InvestmentViewFragment.newInstance(it.id))
                .addToBackStack(null)
                .commit()
        })
        sp = PreferenceManager.getDefaultSharedPreferences(context)
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(InvestmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InvestFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val list = binding.investList
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayoutManager.VERTICAL)
        list.addItemDecoration(dividerItemDecoration)

        update()
        binding.ibMore.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(R.string.category)
            builder.setItems(resources.getStringArray(R.array.categories)
            ) { _, which ->
                sp.edit().putInt(Constants.PREF_CATEGORY, which).commit()
                update()
            }
            builder.show()
        }
        return root
    }

    private fun update() {
        val cat = sp.getInt(Constants.PREF_CATEGORY, 0)
        val catStr = resources.getStringArray(R.array.categories)[cat]

        binding.tvCategory.text = catStr
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setTitle(R.string.title_investments)
        }

        viewModel.allData.observe(viewLifecycleOwner, {
            adapter.setItems(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}