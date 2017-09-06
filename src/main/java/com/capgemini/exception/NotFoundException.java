package com.capgemini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No result")  // 404
public class NotFoundException extends RuntimeException{

}