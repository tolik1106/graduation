package com.zhitar.topjavagraduation.controller;

import com.zhitar.topjavagraduation.util.exception.ErrorInfo;
import com.zhitar.topjavagraduation.util.exception.ErrorType;
import com.zhitar.topjavagraduation.util.exception.IllegalRequestDataException;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

    private static final String EXCEPTION_DUPLICATE_EMAIL = "User with this email already exists";

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorInfo notFoundError(HttpServletRequest req, Exception e) {
        return getErrorInfo(req, ErrorType.NOT_FOUND_ERROR, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return getErrorInfo(req, ErrorType.DATA_ERROR, EXCEPTION_DUPLICATE_EMAIL);
    }

    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
        return getErrorInfo(req, ErrorType.VALIDATION_ERROR, e.getMessage());
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorInfo bindValidationError(HttpServletRequest req, Exception e) {
        BindingResult result = e instanceof BindException ?
                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();

        StringJoiner joiner = new StringJoiner("<br/>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (msg != null) {
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    }
                }
        );
        return getErrorInfo(req, ErrorType.VALIDATION_ERROR, joiner.toString());
    }



    static ErrorInfo getErrorInfo(HttpServletRequest req, ErrorType errorType, String message) {
        return new ErrorInfo(req.getRequestURL(), errorType, message);
    }
}
