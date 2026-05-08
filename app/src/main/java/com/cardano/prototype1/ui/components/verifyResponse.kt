package com.cardano.prototype1.ui.components

data class VerifyResponse(
    val status: String,
    val name: String?,
    val batchId: String?,
    val manufacturer: String?
)