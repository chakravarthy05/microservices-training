package com.slokam.healthcare.dpi.controlleradvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.slokam.healthcare.dpi.response.ErrorResponse;

@RestControllerAdvice
public class GenereralExceptionHandler {

	  @ExceptionHandler(Exception.class)
	  public ResponseEntity<ErrorResponse> handleException(Exception ex ){
		   ErrorResponse  error = new ErrorResponse();
		   error.setErrorCode("Genereral");
		   error.setDescription(ex.getMessage());
		   error.setException(ex);
		   return new ResponseEntity<ErrorResponse>(error , HttpStatus.INTERNAL_SERVER_ERROR); 
		  
	  }
	  
	  @ExceptionHandler(IOException.class)
	  public ResponseEntity<ErrorResponse> handleIoException(IOException ex){
		  ErrorResponse error = new ErrorResponse();
		  error.setErrorCode("FileUpload");
		  error.setDescription(ex.getMessage());
		  error.setException(ex);
		  return new ResponseEntity<ErrorResponse>(error , HttpStatus.INTERNAL_SERVER_ERROR); 
	  }
	  
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity< Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
			
			
			BindingResult bundingResult = ex.getBindingResult();
			List<ObjectError> list = bundingResult.getAllErrors();
			
			Map<String, String> map = new HashMap<>();
			
			for (ObjectError objectError : list) {
				map.put(  ((FieldError)objectError).getField(), objectError.getDefaultMessage());
			}
			
			
			
			//return ResponseEntity.badRequest().body(map);
			
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		}
		 
		/*
		 * @ExceptionHandler(MethodArgumentNotValidException.class) public
		 * ResponseEntity<ErrorResponse>
		 * handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		 * 
		 * ErrorResponse error = new ErrorResponse(); error.setErrorCode("Validation");
		 * error.setDescription(ex.getMessage()); error.setException(ex); return new
		 * ResponseEntity<ErrorResponse>(error , HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
}
