package com.mashup;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mashup.hotel.model.StatusDetails;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler{

	
	 @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
	      StatusDetails errorDetails = new StatusDetails(new Date(), "Validation Failed",
	        ex.getBindingResult().toString());
	    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

      }
}
