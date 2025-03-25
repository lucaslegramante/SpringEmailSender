package org.example.springemailsender.boundaries.controllers

import org.example.springemailsender.core.application.EmailRequest
import org.example.springemailsender.core.application.EmailSenderUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
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
        } catch (e: RuntimeException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email")
        }
    }
}
