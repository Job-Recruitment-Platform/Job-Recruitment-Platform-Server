package org.toanehihi.jobrecruitmentplatformserver.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.toanehihi.jobrecruitmentplatformserver.interfaces.web.dtos.DataResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<DataResponse<Void>> handlingRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .badRequest()
                .body(DataResponse.<Void>builder()
                        .code(ErrorCode.SYSTEM_INTERNAL_ERROR.getCode())
                        .message(ErrorCode.SYSTEM_INTERNAL_ERROR.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<DataResponse<Void>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(DataResponse.<Void>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    ResponseEntity<DataResponse<Void>> handlingAuthorizationDeniedException(AuthorizationDeniedException exception) {
        ErrorCode errorCode = ErrorCode.AUTH_UNAUTHORIZED;
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(DataResponse.<Void>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataResponse<Void>> handleJsonParseException(HttpMessageNotReadableException exception) {
        ErrorCode errorCode = ErrorCode.ENUM_INVALID_VALUE;
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(DataResponse.<Void>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<DataResponse<Void>> handlingValidation(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.SYSTEM_UNKNOWN_ERROR;

        FieldError fieldError = exception.getFieldError();
        if (fieldError != null) {
            String enumKey = fieldError.getDefaultMessage();
            if (enumKey != null) {
                try {
                    errorCode = ErrorCode.valueOf(enumKey);
                } catch (IllegalArgumentException e) {
                    log.warn("Unknown error code: {}", enumKey);
                    errorCode = ErrorCode.SYSTEM_UNKNOWN_ERROR;
                }
            }
        }
        DataResponse<Void> dataResponse = new DataResponse<>();
        dataResponse.setCode(errorCode.getCode());
        dataResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(dataResponse);
    }

    @ExceptionHandler({ BadCredentialsException.class, UsernameNotFoundException.class,
            InternalAuthenticationServiceException.class })
    public ResponseEntity<DataResponse<Void>> handleAuthenticationException(Exception ex) {
        return ResponseEntity.status(ErrorCode.INVALID_CREDENTIALS.getStatus())
                .body(DataResponse.<Void>builder()
                        .code(ErrorCode.INVALID_CREDENTIALS.getCode())
                        .message(ErrorCode.INVALID_CREDENTIALS.getMessage())
                        .build());
    }
}
