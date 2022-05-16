package ir.omidrezabagherian.filmapplication.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import ir.omidrezabagherian.filmapplication.R
import ir.omidrezabagherian.filmapplication.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private lateinit var detailsBinding: FragmentDetailsBinding
    private val detailsArgs: DetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsBinding = FragmentDetailsBinding.bind(view)

        detailsViewModel.getFilm(detailsArgs.filmID)

        detailsViewModel.listFilm.observe(viewLifecycleOwner) { result ->
            detailsBinding.textViewDetailsTitle.text = result!!.title
            val image = "https://image.tmdb.org/t/p/w500${result.poster_path}"
            Glide.with(view.context).load(image).into(detailsBinding.imageViewItem)
            detailsBinding.ratingBarDetails.rating = (result.vote_average.toFloat())/2
            detailsBinding.textViewDetailsDescription.text = result.overview
        }

    }

}