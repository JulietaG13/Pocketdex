package com.austral.pocketdex.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.R
import com.austral.pocketdex.security.BiometricAuthManager
import com.austral.pocketdex.storage.PocketdexDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val biometricAuthManager: BiometricAuthManager
) : ViewModel() {

    private var _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private val database = PocketdexDatabase.getDatabase(context)

    private val _found = MutableStateFlow(0)
    val found: StateFlow<Int> = _found

    val total = 1025

    init {
        viewModelScope.launch {
            _found.value = database.PokemonDao().countAll()
        }
    }

    fun deleteAll(context: Context) {
        biometricAuthManager.authenticate(
            context,
            onError = {
                _isAuthenticated.value = false
                Toast.makeText(
                    context,
                    context.getString(R.string.bio_auth_on_error),
                    Toast.LENGTH_SHORT
                ).show()
            },
            onSuccess = {
                _isAuthenticated.value = true
                viewModelScope.launch {
                    database.PokemonDao().deleteAll()
                    _found.value = 0
                }
            },
            onFail = {
                _isAuthenticated.value = false
                Toast.makeText(
                    context,
                    context.getString(R.string.bio_auth_on_fail),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}