package com.example.photosearchapp.presentation.search_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosearchapp.common.NetworkResponse
import com.example.photosearchapp.domain.use_case.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPhotosViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(SearchPhotosState())
    var state: State<SearchPhotosState> = _state

    fun searchPhotos(query: String) {
        searchPhotosUseCase(query).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value = SearchPhotosState(
                        isLoading = false,
                        photos = result.data ?: emptyList(),
                    )
                }

                is NetworkResponse.Failure -> {
                    _state.value = SearchPhotosState(error = result.error)
                }

                is NetworkResponse.Loading -> {
                    _state.value = SearchPhotosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}