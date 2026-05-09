package com.cardano.prototype1.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Email and Password required"
            return
        }

        isLoading = true
        errorMessage = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    isLoggedIn = true
                } else {
                    errorMessage = task.exception?.message
                }
            }
    }
}