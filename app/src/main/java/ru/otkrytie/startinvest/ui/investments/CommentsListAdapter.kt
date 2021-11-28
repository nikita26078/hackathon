package ru.otkrytie.startinvest.ui.investments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otkrytie.startinvest.data.models.Comment
import ru.otkrytie.startinvest.databinding.ListRowCommentsBinding

class CommentsListAdapter() :
    RecyclerView.Adapter<CommentsListAdapter.ViewHolder>() {
    private var list: List<Comment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListRowCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.tvAuthor.text = item.author
            binding.tvBody.text = item.body
        }
    }

    override fun getItemCount() = list.size

    fun setItems(items: List<Comment>) {
        list = items
    }

    class ViewHolder(val binding: ListRowCommentsBinding) : RecyclerView.ViewHolder(binding.root)
}
