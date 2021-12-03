package ru.otkrytie.startinvest.ui.news

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import ru.otkrytie.startinvest.data.models.Subscription

class SubsDiffCallback(
    private val oldList: List<Subscription>,
    private val newList: List<Subscription>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, value) = oldList[oldPosition]
        val (_, value1) = newList[newPosition]

        return value == value1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}
