package org.example.springemailsender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringEmailSenderApplication

fun main(args: Array<String>) {
    runApplication<SpringEmailSenderApplication>(*args)
}
