package com.rod.api.user.exception;

import com.rod.api.common.component.Messenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Messenger> handleUserNotFoundException(UserNotFoundException ex) {
        // 예외 처리 로직
        Messenger response = Messenger.builder()
                .message("User not found")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
