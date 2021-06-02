package com.example.demo.exception.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.StringJoiner;

@Setter
@Getter
public class RestErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private int status;

    private String error;

    private String message;

    private String path;

    public static RestErrorResponseBuilder builder() {
        return new RestErrorResponseBuilder();
    }

    public String toJson() {
        return new StringJoiner(", ", "{", "}")
                .add("\"timestamp\": \"" + timestamp + "\"")
                .add("\"status\": " + status)
                .add("\"error\": \"" + error + "\"")
                .add("\"message\": \"" + message + "\"")
                .add("\"path\": \"" + path + "\"")
                .toString();
    }
}