package ir.omidrezabagherian.filmapplication.ui.coming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.omidrezabagherian.filmapplication.data.FilmRepository
import ir.omidrezabagherian.filmapplication.domain.model.ResultX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(private val filmRepository: FilmRepository) :
    ViewModel() {
    private val _listComingSoon = MutableStateFlow<List<ResultX>>(emptyList())
    val listComingSoon: StateFlow<List<ResultX>> = _listComingSoon

    suspend fun insertComingList() {
        filmRepository.insertComingListLocal()
    }

    fun getComingSoonListFromLocal() {
        viewModelScope.launch {
            filmRepository.getComingSoonListLocal().collect { list ->
                _listComingSoon.emit(list)
            }
        }
    }

}