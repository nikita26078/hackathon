package ru.otkrytie.startinvest.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otkrytie.startinvest.data.models.News
import ru.otkrytie.startinvest.databinding.ListRowPostsBinding

class PostListAdapter(private val user: Boolean, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private var list: List<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListRowPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.tvBody.text = item.body
            binding.tvAuthor.text = item.author

            itemView.setOnClickListener {
                onClickListener.onClick(item)
            }

            if (user) {
                binding.ibMore.visibility = View.VISIBLE
            } else {
                binding.ibMore.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(items: List<News>) {
        list = items
    }

    class OnClickListener(val clickListener: (news: News) -> Unit) {
        fun onClick(news: News) = clickListener(news)
    }

    class ViewHolder(val binding: ListRowPostsBinding) : RecyclerView.ViewHolder(binding.root)
}
