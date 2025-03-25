package org.example.springemailsender.infra

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException
import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import org.example.springemailsender.core.application.exceptions.EmailServiceException
import org.example.springemailsender.core.domain.EmailSenderGateway
import org.springframework.stereotype.Service

@Service
class SesEmailSender(
    private val amazonSimpleEmailService: AmazonSimpleEmailService
) : EmailSenderGateway {
    override fun sendEmail(to: String, subject: String, body: String) {
        val request = SendEmailRequest().withSource(
            "lucaslegramante@gmail.com"
        ).withDestination(
            Destination()
                .withToAddresses(to)
        ).withMessage(
            Message()
                .withSubject(Content(subject))
                .withBody(
                    Body()
                        .withText(Content(body))
                )
        )

        try {
            amazonSimpleEmailService.sendEmail(request)
        } catch (e: AmazonSimpleEmailServiceException) {
            throw EmailServiceException("Error sending email", e)
        }
    }
}
