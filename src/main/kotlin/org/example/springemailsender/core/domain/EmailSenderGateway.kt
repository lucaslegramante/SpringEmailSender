package org.example.springemailsender.core.domain

interface EmailSenderGateway {
    fun sendEmail(
        to: String,
        subject: String,
        body: String,
    )
}
