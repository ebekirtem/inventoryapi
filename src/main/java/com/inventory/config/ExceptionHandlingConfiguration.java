package com.inventory.config;

import com.inventory.dto.ApiResponseDto;
import com.inventory.exception.BadRequestException;
import com.inventory.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * This is exception handling config class. new branch added. then merged.
 */
@RestControllerAdvice
public class ExceptionHandlingConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingConfiguration.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return new ApiResponseDto(false, ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map((error) -> error.getDefaultMessage())
                .collect(Collectors.joining(",")));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ApiResponseDto handleBadRequestException(BadRequestException ex) {
        logger.error(ex.getMessage());
        return new ApiResponseDto(false,ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponseDto handleBadRequestException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        return new ApiResponseDto(false, ex.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ApiResponseDto handleAnyException(Exception e) {
        logger.error("Error while processing exception", e);
        return new ApiResponseDto(false, e.getMessage());
    }
}
