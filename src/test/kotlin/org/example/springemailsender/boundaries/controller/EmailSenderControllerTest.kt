package org.example.springemailsender.boundaries.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.example.springemailsender.boundaries.controllers.EmailSenderController
import org.example.springemailsender.core.application.EmailRequest
import org.example.springemailsender.core.application.EmailSenderUseCase
import org.example.springemailsender.core.application.exceptions.EmailServiceException
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import kotlin.test.Test

@WebMvcTest(EmailSenderController::class)
@ExtendWith(SpringExtension::class)
internal class EmailSenderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var emailSenderUseCase: EmailSenderUseCase // ⬅️ O mock será injetado aqui

    @TestConfiguration
    class TestConfig {
        @Bean
        fun emailSenderUseCase(): EmailSenderUseCase {
            return mockk(relaxed = true) // ⬅️ Criando mock manualmente
        }
    }

    @Test
    fun `should send post email successfully`() {
        val request = EmailRequest(to = "lucas@example.com", subject = "Teste", body = "Corpo do email")
        val response = "Email sent successfully"

        mockMvc
            .post("/api/email/send") {
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper().writeValueAsString(request)
            }.andExpect {
                status { isOk() }
                content { string(response) }
            }
    }

    @Test
    fun `should throw exception when send post email`() {
        val request = EmailRequest(to = "lucas@example.com", subject = "Teste", body = "Corpo do email")

        every { emailSenderUseCase.sendEmail(any()) } throws EmailServiceException("Error sending email")

        mockMvc
            .post("/api/email/send") {
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper().writeValueAsString(request)
            }.andExpect {
                status { isInternalServerError() }
                content { string("Error sending email") }
            }
    }
}
