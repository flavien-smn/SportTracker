package com.taskflow.sporttracker.exception.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    private String message;
    private int status;
}
