package org.example.springemailsender.core.application

import org.example.springemailsender.core.domain.EmailSenderGateway
import org.springframework.stereotype.Service

@Service
class EmailSenderUseCase(
    val emailSenderGateway: EmailSenderGateway,
) {
    fun sendEmail(emailRequest: EmailRequest) {
        emailSenderGateway.sendEmail(
            emailRequest.to,
            emailRequest.subject,
            emailRequest.body,
        )
    }
}
