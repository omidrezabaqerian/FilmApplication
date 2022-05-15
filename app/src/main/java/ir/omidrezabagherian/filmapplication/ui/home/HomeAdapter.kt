package ir.omidrezabagherian.filmapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.omidrezabagherian.filmapplication.databinding.ItemFilmBinding
import ir.omidrezabagherian.filmapplication.domain.model.Result

class HomeAdapter(private val detail: (Result) -> Unit) :
    ListAdapter<Result, HomeAdapter.HomeViewHolder>(HomeDiffCallback()) {

    class HomeViewHolder(
        private val itemFilmBinding: ItemFilmBinding,
        private val detail: (Result) -> Unit
    ) : RecyclerView.ViewHolder(itemFilmBinding.root) {
        fun bindView(result: Result) {
            itemFilmBinding.root.setOnClickListener {
                detail(result)
            }
            val image = "https://image.tmdb.org/t/p/w500${result.backdrop_path}"
            Glide.with(itemFilmBinding.root).load(image).into(itemFilmBinding.imageViewItem)
            itemFilmBinding.textViewItemTitle.text = result.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            detail
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

}