package org.example.springemailsender.infra

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException
import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import org.example.springemailsender.core.application.EmailRequest
import org.example.springemailsender.core.application.EmailSenderUseCase
import org.springframework.stereotype.Service

@Service
class SesEmailSender(
    private val amazonSimpleEmailService: AmazonSimpleEmailService
) : EmailSenderUseCase {
    override fun sendEmail(emailRequest: EmailRequest) {
        val request = SendEmailRequest().withSource(
            "lucaslegramante@gmail.com"
        ).withDestination(
            Destination()
                .withToAddresses(emailRequest.to)
        ).withMessage(
            Message()
                .withSubject(Content(emailRequest.subject))
                .withBody(Body(Content(emailRequest.body)))
        )

        try {
            amazonSimpleEmailService.sendEmail(request)
        } catch (e: AmazonSimpleEmailServiceException) {
            throw EmailServiceException("Error sending email", e)
        }
    }
}
