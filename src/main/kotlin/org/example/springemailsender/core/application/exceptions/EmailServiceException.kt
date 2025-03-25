package org.example.springemailsender.core.application.exceptions

class EmailServiceException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
