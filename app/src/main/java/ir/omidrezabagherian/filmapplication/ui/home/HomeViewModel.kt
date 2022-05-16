package ir.omidrezabagherian.filmapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.omidrezabagherian.filmapplication.data.FilmRepository
import ir.omidrezabagherian.filmapplication.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val filmRepository: FilmRepository) : ViewModel() {
    private val _searchFilm = MutableLiveData<List<Result>>()
    val searchFilm: LiveData<List<Result>> = _searchFilm

    private val _listFilm = MutableStateFlow<List<Result>>(emptyList())
    val listFilm: StateFlow<List<Result>> = _listFilm

    suspend fun insertFilmList() {
        filmRepository.insertFilmListLocal()
    }

    fun searchFilm(query: String) {
        viewModelScope.launch {
            val response = filmRepository.searchFilm(query)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _searchFilm.postValue(response.body()!!.results)
                }
            }

        }
    }

    fun getFilmListFromLocal() {
        viewModelScope.launch {
            filmRepository.getFilmListLocal().collect { list ->
                _listFilm.emit(list)
            }
        }
    }
}