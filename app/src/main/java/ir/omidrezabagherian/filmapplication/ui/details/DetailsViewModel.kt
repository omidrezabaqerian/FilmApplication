package ir.omidrezabagherian.filmapplication.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.omidrezabagherian.filmapplication.data.FilmRepository
import ir.omidrezabagherian.filmapplication.domain.model.FilmDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val filmRepository: FilmRepository
) : ViewModel() {
    private val _listFilm = MutableLiveData<FilmDetails>()
    val listFilm: LiveData<FilmDetails> = _listFilm

    fun getFilm(filmID: Int) {
        viewModelScope.launch {
            val response = filmRepository.getFilm(filmID)
            if (response.isSuccessful) {
                _listFilm.value = response.body()
            }
        }
    }

}