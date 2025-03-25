package org.example.springemailsender.core.application

data class EmailRequest(
    val to: String,
    val subject: String,
    val body: String,
)
