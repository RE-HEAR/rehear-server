package com.seoridam.rehearserver.global.exception;

import com.seoridam.rehearserver.global.common.FailResponse;
import com.seoridam.rehearserver.global.common.StatusEnum;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public FailResponse handleIllegalArgumentException(IllegalArgumentException e){
        return FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(NullPointerException.class)
    public FailResponse handleNullPointerException(NullPointerException e){
        return FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public FailResponse handleRuntimeException(RuntimeException e){
        return FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
    }
}
