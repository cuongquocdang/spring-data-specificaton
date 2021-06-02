package com.example.demo.exception;

import com.example.demo.exception.vo.RestErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class RestGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCriteriaException.class)
    ResponseEntity<?> handleInvalidCriteriaException(Exception ex, WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);
        return handleEveryException(request);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);
        return handleEveryException(request);
    }

    protected ResponseEntity<RestErrorResponse> handleEveryException(WebRequest request) {
        return RestErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .message("Server encountered an error")
                .path(getPath(request))
                .entity();
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).substring(4);
    }

}
