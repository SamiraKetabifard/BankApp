package com.example.bankapp.exception;

import java.time.LocalDateTime;

public record ErrorDetails(String message,
                           String errorCode,
                           LocalDateTime timestamp,
                           String details) {
}
