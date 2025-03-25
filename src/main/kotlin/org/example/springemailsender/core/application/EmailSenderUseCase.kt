package org.example.springemailsender.core.application

interface EmailSenderUseCase {
    fun sendEmail(emailRequest: EmailRequest)
}
