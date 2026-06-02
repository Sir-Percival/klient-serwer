package dev.walichnowski.springbootrestapis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeErrorResponse
{
    private int status;
    private String message;
    private Instant timestamp;
}
