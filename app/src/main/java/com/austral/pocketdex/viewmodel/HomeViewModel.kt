package com.austral.pocketdex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    private val _found = MutableStateFlow(0)
    val found: StateFlow<Int> = _found

    val total = 1025

    init {
        viewModelScope.launch {
            _found.value = 10
        }
    }
}