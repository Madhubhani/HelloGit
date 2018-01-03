package com.helloRest.exception;

import org.springframework.http.HttpStatus;

import com.helloRest.constant.ExceptionMessages;

/**
 * Customized Exception for Duplicate Record Exception. Exception used when record exists 
 * 
 * @author: Isura A
 *
 */
public class DuplicateRecordException extends BaseException {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -3926855032933143474L;

    /**
     * Constructor Method.(Default Message).
     *
     */
    public DuplicateRecordException() {
	super(HttpStatus.CONFLICT, ExceptionMessages.METHOD_NOT_ALLOWED_DEFAULT);
    }

    /**
     * Constructor Method. (Customized Exception)
     * 
     * @param message
     */
    public DuplicateRecordException(String message) {
	super(HttpStatus.CONFLICT, message);
    }

    /**
     * Constructor Method. (Exception and Customized Message).
     * 
     * @param message
     *            : Customization message.
     * 
     * @param e
     *            : Exception.
     */
    public DuplicateRecordException(String message, Exception e) {
	super(HttpStatus.CONFLICT, message, e);
    }

}