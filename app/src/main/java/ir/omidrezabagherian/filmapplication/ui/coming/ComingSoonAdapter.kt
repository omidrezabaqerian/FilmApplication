package ir.omidrezabagherian.filmapplication.ui.coming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.omidrezabagherian.filmapplication.databinding.ItemFilmBinding
import ir.omidrezabagherian.filmapplication.domain.model.ResultX

class ComingSoonAdapter(private val detail: (ResultX) -> Unit) :
    ListAdapter<ResultX, ComingSoonAdapter.ComingSoonViewHolder>(ComingSoonDiffCallback()) {

    class ComingSoonViewHolder(
        private val itemFilmBinding: ItemFilmBinding,
        private val detail: (ResultX) -> Unit
    ) : RecyclerView.ViewHolder(itemFilmBinding.root) {
        fun bindView(result: ResultX) {
            itemFilmBinding.root.setOnClickListener {
                detail(result)
            }
            val image = "https://image.tmdb.org/t/p/w500${result.backdrop_path}"
            Glide.with(itemFilmBinding.root).load(image).into(itemFilmBinding.imageViewItem)
            itemFilmBinding.textViewItemTitle.text = result.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonViewHolder =
        ComingSoonViewHolder(
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            detail
        )

    override fun onBindViewHolder(holder: ComingSoonViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

}