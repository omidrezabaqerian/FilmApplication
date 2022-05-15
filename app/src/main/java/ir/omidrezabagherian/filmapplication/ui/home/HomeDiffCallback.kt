package ir.omidrezabagherian.filmapplication.ui.home

import androidx.recyclerview.widget.DiffUtil
import ir.omidrezabagherian.filmapplication.domain.model.Result

class HomeDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }
}