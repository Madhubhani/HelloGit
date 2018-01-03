package com.helloRest.exception;

import org.springframework.http.HttpStatus;

import com.helloRest.constant.ExceptionMessages;

/**
 * Custom Exception for Invalid Request Handling.
 * 
 * @author
 *
 */
public class BadRequestException extends BaseException {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 1310982982607004391L;

    /**
     * Constructor Method. (Default Message).
     *
     */
    public BadRequestException() {
	super(HttpStatus.BAD_REQUEST, ExceptionMessages.BAD_REQUEST_DEFAULT);
    }

    /**
     * Constructor Method.
     * 
     * @param message
     *            : The message for the exception
     */
    public BadRequestException(String message) {
	super(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * Constructor Method.
     * 
     * @param message
     *            : The message for the exception
     * 
     * @param e
     *            : The cause.
     */
    public BadRequestException(String message, Exception e) {
	super(HttpStatus.BAD_REQUEST, message, e);
    }

}
