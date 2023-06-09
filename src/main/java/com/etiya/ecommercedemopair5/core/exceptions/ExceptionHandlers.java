package com.etiya.ecommercedemopair5.core.exceptions;

import com.etiya.ecommercedemopair5.core.exceptions.types.BusinessException;
import com.etiya.ecommercedemopair5.core.exceptions.types.NotFoundException;
import com.etiya.ecommercedemopair5.core.utils.result.ErrorDataResult;
import com.etiya.ecommercedemopair5.core.utils.result.ErrorResult;
import com.etiya.ecommercedemopair5.core.utils.result.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBusinessException(BusinessException exception){
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHttpMessageConversionException(HttpMessageConversionException exception){
        return exception.getMessage();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
        //Gelen validasyon hatalarını liste olarak dönmemizi sağlar.
        Map<String,String> errors = new HashMap<>();

        for (FieldError fieldError:exception.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<Object>(errors, "Validation error.");
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNotFoundException(NotFoundException exception){
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        return exception.getMessage();
    }
}
