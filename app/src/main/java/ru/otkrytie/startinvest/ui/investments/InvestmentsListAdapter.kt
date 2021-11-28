package ru.otkrytie.startinvest.ui.investments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otkrytie.startinvest.data.models.Investment
import ru.otkrytie.startinvest.databinding.InvestListItemBinding

class InvestmentsListAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<InvestmentsListAdapter.ViewHolder>() {
    private var list: List<Investment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            InvestListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.tvTitle.text = "Zoom"
            binding.tvDescription.text = "\$222,95 (+7,13%)\n"
            itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
        }
    }

    override fun getItemCount() = list.size

    fun setItems(items: List<Investment>) {
        list = items
    }

    class OnClickListener(val clickListener: (investment: Investment) -> Unit) {
        fun onClick(investment: Investment) = clickListener(investment)
    }

    class ViewHolder(val binding: InvestListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
