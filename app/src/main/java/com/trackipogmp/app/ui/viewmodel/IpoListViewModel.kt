package com.trackipogmp.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackipogmp.app.data.model.IpoItem
import com.trackipogmp.app.data.repository.IpoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IpoListViewModel @Inject constructor(
    private val repository: IpoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<IpoUiState>(IpoUiState.Loading)
    val uiState: StateFlow<IpoUiState> = _uiState.asStateFlow()

    init {
        loadIpos()
    }

    fun loadIpos() {
        viewModelScope.launch {
            _uiState.value = IpoUiState.Loading
            try {
                // First, ensure we have data
                repository.refreshIpos()
                
                // Then observe the database
                repository.allIpos.collect { ipos ->
                    if (ipos.isEmpty()) {
                        _uiState.value = IpoUiState.Error("No IPOs available even after refresh")
                    } else {
                        _uiState.value = IpoUiState.Success(ipos)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = IpoUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}

sealed class IpoUiState {
    object Loading : IpoUiState()
    data class Success(val ipos: List<IpoItem>) : IpoUiState()
    data class Error(val message: String) : IpoUiState()
}
