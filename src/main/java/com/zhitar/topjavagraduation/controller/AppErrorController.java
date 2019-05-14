package com.zhitar.topjavagraduation.controller;

import com.zhitar.topjavagraduation.util.exception.ErrorInfo;
import com.zhitar.topjavagraduation.util.exception.ErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppErrorController implements ErrorController {

    private static final String ERR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(ERR_PATH)
    public ResponseEntity<ErrorInfo> error(HttpServletRequest req) {
        WebRequest requestAttributes = new ServletWebRequest(req);
        Throwable error = this.errorAttributes.getError(requestAttributes);
        return new ResponseEntity(ExceptionInfoHandler.getErrorInfo(req, ErrorType.APP_ERROR, error.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }
}
