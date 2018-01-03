package com.helloRest.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailureException extends BaseException {

    private static final long serialVersionUID = 1L;

    private String sysPiToken;

    public AuthenticationFailureException(String sysPiToken) {

	super("Incorrect PI Id [" + sysPiToken + "]");
	this.sysPiToken = sysPiToken;

    }

    public AuthenticationFailureException(String message, Exception e) {
	super(HttpStatus.UNAUTHORIZED, message, e);
    }

    public String getCourseId() {
	return sysPiToken;
    }

}
