package com.example.web.api.v1.transport;

import java.util.List;

/**
 *
 */
public class ErrorResponseTransport {
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
