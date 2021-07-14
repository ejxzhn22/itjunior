package com.spring.itjunior.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String exception404(NoHandlerFoundException e) {
        return "error404.." + e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500 error
    public String handleArgumentException(IllegalArgumentException e) {
        return e.getMessage() + "exception 500,,";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String exceptionDelete(EmptyResultDataAccessException e) {
        return "회원 삭제(탈퇴)실패하였습니다."+e.getMessage();
    }

//    @ExceptionHandler(Exception.class)
//    public String bindingException(Exception e) {
//        return "exception,,"+e.getMessage();
//    }
}
