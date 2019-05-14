package com.zhitar.topjavagraduation.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String message;

    public ErrorInfo(CharSequence url, ErrorType type, String message) {
        this.url = url.toString();
        this.type = type;
        this.message = message;
    }
}
