package ir.omidrezabagherian.filmapplication.ui.coming

import androidx.recyclerview.widget.DiffUtil
import ir.omidrezabagherian.filmapplication.domain.model.ResultX

class ComingSoonDiffCallback : DiffUtil.ItemCallback<ResultX>() {
    override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.id == newItem.id
    }
}