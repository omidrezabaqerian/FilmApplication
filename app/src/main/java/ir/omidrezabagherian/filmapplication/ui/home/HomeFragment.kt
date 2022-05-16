package ir.omidrezabagherian.filmapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
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
import ir.omidrezabagherian.filmapplication.core.Network
import ir.omidrezabagherian.filmapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding = FragmentHomeBinding.bind(view)

        val homeAdapter = HomeAdapter(detail = { film ->
            val detail = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(film.id)
            navController.navigate(detail)
        })

        homeBinding.recyclerViewHome.layoutManager = LinearLayoutManager(view.context)

        homeBinding.recyclerViewHome.adapter = homeAdapter

        Network(view.context).observe(viewLifecycleOwner) { connect ->
            if (!connect) {
                Snackbar.make(
                    view.context,
                    view,
                    "No Internet, please check connection",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch {
                    homeViewModel.insertFilmList()
                }
                homeBinding.searchViewHome.setOnQueryTextListener(object :
                    OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        homeViewModel.searchFilm(query!!)
                        homeViewModel.searchFilm.observe(viewLifecycleOwner) { data ->
                            homeAdapter.submitList(data)
                            homeBinding.recyclerViewHome.adapter = homeAdapter
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = false

                })
            }
        }

        homeViewModel.getFilmListFromLocal()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                homeViewModel.listFilm.collect { data ->
                    homeAdapter.submitList(data)
                    homeBinding.recyclerViewHome.adapter = homeAdapter
                }
            }
        }

    }
}