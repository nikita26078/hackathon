package ru.otkrytie.startinvest.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otkrytie.startinvest.data.models.Subscription
import ru.otkrytie.startinvest.databinding.ListRowSubsBinding

class SubsListAdapter : RecyclerView.Adapter<SubsListAdapter.ViewHolder>() {
    private var list: List<Subscription> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListRowSubsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.tvAuthor.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(items: List<Subscription>) {
        list = items
    }

    class ViewHolder(val binding: ListRowSubsBinding) : RecyclerView.ViewHolder(binding.root)
}
