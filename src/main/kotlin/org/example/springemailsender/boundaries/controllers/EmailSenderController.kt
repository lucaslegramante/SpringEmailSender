package org.example.springemailsender.boundaries.controllers

import org.example.springemailsender.core.application.EmailRequest
import org.example.springemailsender.core.application.EmailSenderUseCase
import org.example.springemailsender.core.application.exceptions.EmailServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/email")
class EmailSenderController(
    private val emailSenderUseCase: EmailSenderUseCase,
) {
    @PostMapping("/send")
    fun sendEmail(
        @RequestBody emailRequest: EmailRequest,
    ): ResponseEntity<String> {
        try {
            emailSenderUseCase.sendEmail(
                emailRequest,
            )
            return ResponseEntity.ok("Email sent successfully")
        } catch (e: EmailServiceException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email")
        }
    }
}
