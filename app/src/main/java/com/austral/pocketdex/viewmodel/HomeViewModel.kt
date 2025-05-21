package com.austral.pocketdex.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.data.repository.PokemonRepository
import com.austral.pocketdex.storage.PocketdexDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val database = PocketdexDatabase.getDatabase(context)

    private val _found = MutableStateFlow(0)
    val found: StateFlow<Int> = _found

    val total = 1025

    init {
        viewModelScope.launch {
            _found.value = database.PokemonDao().countAll()
        }
    }
}