package com.lukhol.spotsfinder.endpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.lukhol.spotsfinder.exception.GeocodingCityException;
import com.lukhol.spotsfinder.exception.InvalidPlaceException;
import com.lukhol.spotsfinder.exception.InvalidPlaceSearchException;
import com.lukhol.spotsfinder.exception.InvalidWrongPlaceReportException;
import com.lukhol.spotsfinder.exception.NotFoundUserException;
import com.lukhol.spotsfinder.exception.RegisterExternalServiceUserException;
import com.lukhol.spotsfinder.exception.RegisterUserException;
import com.lukhol.spotsfinder.exception.ResetPasswordException;
import com.lukhol.spotsfinder.exception.UpdateUserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {//extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= { InvalidPlaceException.class, GeocodingCityException.class, InvalidPlaceSearchException.class,
			IOException.class, ResetPasswordException.class, UpdateUserException.class })
	protected ResponseEntity<RestResponse<Void>> handlePlaceException(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<RestResponse<Void>>(new RestResponse<Void>(Boolean.FALSE, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { InvalidWrongPlaceReportException.class, })
	protected ResponseEntity<RestResponse<Void>> handleWrongReportPlaceException(InvalidWrongPlaceReportException ex, WebRequest request){
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<RestResponse<Void>>(new RestResponse<Void>(Boolean.FALSE, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException error, WebRequest request)  {
	   return parseErrors(error.getBindingResult());
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<?> handleControllerFieldsValidationException(ConstraintViolationException exception, WebRequest request){
		List<String> listOfErrors =  exception
				.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
		
		return new ResponseEntity<List<String>>(listOfErrors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NotFoundUserException.class)
	public ResponseEntity<RestResponse<Void>> handleNotFoundUserException(NotFoundUserException ex, WebRequest request)  {
		log.error(ex.getMessage());
		return new ResponseEntity<RestResponse<Void>>(new RestResponse<Void>(Boolean.FALSE, ex.getMessage(), null), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = { RegisterExternalServiceUserException.class, RegisterUserException.class })
	public ResponseEntity<RestResponse<Void>> handleRegisterUserException(Exception ex, WebRequest request){
		log.error(ex.getMessage());
		return new ResponseEntity<RestResponse<Void>>(new RestResponse<Void>(Boolean.FALSE, ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ResponseEntity<Map<String, String>> parseErrors(BindingResult bindingResult){		//ErrorRestResponse errors = new ErrorRestResponse();
		Map<String, String> errors = new HashMap<>();
		for(ObjectError objectError : bindingResult.getAllErrors()) {
			FieldError fieldError = (FieldError)objectError;
			
			errors.put(fieldError.getField(), objectError.getDefaultMessage());
		}
		
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}
}
