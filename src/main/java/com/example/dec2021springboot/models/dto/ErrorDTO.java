package com.example.dec2021springboot.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private String message;
    private int code;
    private String field;
    public ErrorDTO(MethodArgumentNotValidException exception,int code){
        this.code=code;
        this.message=exception.getFieldError().getDefaultMessage();
        this.field=exception.getFieldError().getField();
    }
}
