package ru.otkrytie.startinvest.ui.investments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.otkrytie.startinvest.data.models.Investment
import ru.otkrytie.startinvest.databinding.InvestListItemBinding

class InvestmentListAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<InvestmentListAdapter.ViewHolder>() {
    private var list = ArrayList<Investment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            InvestListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.desc
            itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
        }
    }

    override fun getItemCount() = list.size

    fun setItems(items: List<Investment>) {
        val diffCallback = InvestmentDiffCallback(list, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    class OnClickListener(val clickListener: (investment: Investment) -> Unit) {
        fun onClick(investment: Investment) = clickListener(investment)
    }

    class ViewHolder(val binding: InvestListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
