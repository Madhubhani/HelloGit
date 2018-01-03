package com.helloRest.exception;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {
    
//    private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

    /**
     * Return 304 Not Found if resource cannot be found.
     * 
     * @param ex
     */
//    @ExceptionHandler(value = ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND) 
//    protected void handleConflict(Exception ex, WebRequest request) {
//	
//	LOGGER.info("Resource not found");
////	String bodyOfResponse = "This should be application specific";
////	return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }

}
