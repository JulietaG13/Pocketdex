package com.austral.pocketdex.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.austral.pocketdex.R
import com.austral.pocketdex.security.BiometricAuthManager
import com.austral.pocketdex.storage.PocketdexDatabase
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val biometricAuthManager: BiometricAuthManager
) : ViewModel() {

    private var _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private val database = PocketdexDatabase.getDatabase(context)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val credentialManager = CredentialManager.create(context)

    private val _userData = MutableStateFlow(auth.currentUser)
    val userData = _userData.asStateFlow()

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

    fun launchCredentialManager(activityContext: Context) {
        // Instantiate a Google sign-in request
        val googleIdOption = GetGoogleIdOption.Builder()
            // Your server's client ID, not your Android client ID.
            .setServerClientId(context.getString(R.string.GOOGLE_CLIENT_ID))
            // Only show accounts previously used to sign in.
            .setFilterByAuthorizedAccounts(false)
            .build()

        // Create the Credential Manager request
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        viewModelScope.launch {
            try {
                // Launch Credential Manager UI
                val result = credentialManager.getCredential(
                    context = activityContext,
                    request = request
                )

                // Extract credential from the result returned by Credential Manager
                handleSignIn(result.credential)
            } catch (e: GetCredentialException) {
                Log.e(TAG, "Couldn't retrieve user's credentials: ${e.localizedMessage}")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Sign in to Firebase with using the token
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w(TAG, "Credential is not of type Google ID!")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    viewModelScope.launch {
                        _userData.emit(user)
                    }
                } else {
                    // If sign in fails, display a message to the user
                    viewModelScope.launch {
                        _userData.emit(null)
                    }
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun signOut() {
        auth.signOut()
        viewModelScope.launch {
            try {
                val clearRequest = ClearCredentialStateRequest()
                credentialManager.clearCredentialState(clearRequest)
                viewModelScope.launch {
                    _userData.emit(null)
                }
            } catch (e: ClearCredentialException) {
                Log.e(TAG, "Couldn't clear user credentials: ${e.localizedMessage}")
            }
        }
    }
}