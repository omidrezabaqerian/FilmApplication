package ir.omidrezabagherian.filmapplication.ui.coming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.omidrezabagherian.filmapplication.R
import ir.omidrezabagherian.filmapplication.databinding.FragmentComingSoonBinding
import ir.omidrezabagherian.filmapplication.ui.home.HomeFragmentDirections
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComingSoonFragment : Fragment(R.layout.fragment_coming_soon) {
    private lateinit var comingSoonBinding: FragmentComingSoonBinding
    private val comingSoonViewModel: ComingSoonViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comingSoonBinding = FragmentComingSoonBinding.bind(view)

        val comingSoonAdapter = ComingSoonAdapter(detail = { film ->
            val detail = ComingSoonFragmentDirections.actionComingSoonFragmentToDetailsFragment(film.id)
            navController.navigate(detail)
        })

        comingSoonBinding.recyclerViewComingSoon.layoutManager = LinearLayoutManager(view.context)

        comingSoonBinding.recyclerViewComingSoon.adapter = comingSoonAdapter

        ir.omidrezabagherian.filmapplication.core.Network(view.context)
            .observe(viewLifecycleOwner) { connect ->
                if (!connect) {
                    Snackbar.make(
                        view.context,
                        view,
                        "No Internet, please check connection",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    lifecycleScope.launch {
                        comingSoonViewModel.insertComingList()
                    }
                }
            }

        comingSoonViewModel.getComingSoonListFromLocal()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                comingSoonViewModel.listComingSoon.collect { data ->
                    comingSoonAdapter.submitList(data)
                    comingSoonBinding.recyclerViewComingSoon.adapter = comingSoonAdapter
                }
            }
        }

    }
}