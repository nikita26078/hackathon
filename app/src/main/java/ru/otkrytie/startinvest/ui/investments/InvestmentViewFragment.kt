package ru.otkrytie.startinvest.ui.investments

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.InvestViewFragmentBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InvestmentViewFragment : Fragment() {
    private var _binding: InvestViewFragmentBinding? = null
    private lateinit var adapter: CommentsListAdapter
    private lateinit var viewModel: InvestmentCommentViewModel
    private var userId = 0

    private val binding get() = _binding!!

    companion object {
        const val ITEM_ID = "item_id"

        fun newInstance(id: Int) = InvestmentViewFragment().apply {
            arguments = Bundle(1).apply {
                putInt(ITEM_ID, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CommentsListAdapter()
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(InvestmentCommentViewModel::class.java)
        userId = requireArguments().getInt(ITEM_ID, -1)
        if (userId == -1) {
            userId = 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InvestViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val testData = ArrayList<Entry>()
        testData.add(Entry(1635714000000f, 278.63f))
        testData.add(Entry(1636059600000f, 259.84f))
        testData.add(Entry(1636664400000f, 263.7f))
        testData.add(Entry(1637528400000f, 227.63f))
        val dataSet = LineDataSet(testData, "Price")
        val data = LineData(dataSet)
        data.setValueTextSize(11f)

        binding.tvTitle.text = "Zoom"
        binding.tvDescription.text = "\$222,95 (-7,13%)\n"

        val chart = binding.chart

        val textColorPrimary = requireContext().getColorThemeRes(android.R.attr.textColorPrimary)
        chart.axisRight.textColor = textColorPrimary
        chart.xAxis.textColor = textColorPrimary
        data.setValueTextColor(textColorPrimary)

        chart.xAxis.valueFormatter = BarChartXAxisValueFormatter()
        chart.xAxis.axisMaximum = data.xMax + 200000000f
        chart.axisLeft.isEnabled = false
        chart.legend.isEnabled = false
        chart.description.textSize = 14f
        chart.description.text = "Price ($)"
        chart.description.textColor = textColorPrimary
        chart.data = data
        chart.invalidate()

        binding.rvComments.adapter = adapter

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.setDisplayHomeAsUpEnabled(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
        }
        return true
    }

    class BarChartXAxisValueFormatter : IndexAxisValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            val timeMilliseconds = Date(value.toLong())
            val dateTimeFormat = SimpleDateFormat("dd.MM", Locale.US)
            return dateTimeFormat.format(timeMilliseconds)
        }
    }

    @ColorInt
    fun Context.getColorThemeRes(@AttrRes id: Int): Int {
        val resolvedAttr = TypedValue()
        this.theme.resolveAttribute(id, resolvedAttr, true)
        return this.getColor(resolvedAttr.resourceId)
    }

}