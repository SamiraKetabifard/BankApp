package com.example.bankapp.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> handleAccountException(AccountException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                webRequest.getDescription(false),
                exception.getMessage(),
                LocalDateTime.now(),
                "ACCOUNT_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity <ErrorDetails>genericHandleException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails
        (webRequest.getDescription(false),
                exception.getMessage(),
                LocalDateTime.now(),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
