package com.example.photosearchapp.presentation.photo_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosearchapp.common.NetworkResponse
import com.example.photosearchapp.domain.use_case.FetchPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val fetchPhotoDetailUseCase: FetchPhotoDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(PhotoDetailState())
    val state: State<PhotoDetailState> = _state

    init {
        savedStateHandle.get<String>("photoId")?.let { photoId ->
            fetchPhotoDetail(photoId = photoId)
        }
    }

    private fun fetchPhotoDetail(photoId: String) {
        fetchPhotoDetailUseCase(photoId = photoId).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value = PhotoDetailState(photoDetail = result.data)
                }

                is NetworkResponse.Failure -> {
                    _state.value = PhotoDetailState(error = result.error)
                }

                is NetworkResponse.Loading -> {
                    _state.value = PhotoDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}