package com.helloRest.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.helloRest.constant.ExceptionMessages;
import com.helloRest.constant.HeaderParameters;
import com.pearson.ed.pi.authentication.authenticator.TokenAuthenticator;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private TokenAuthenticator tokenAuthenticator;

    /**
     * Verifies the validity of the PI token sent with the request for
     * authentication. If the token is invalid, the request is rejected.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	String authenticationToken = request.getHeader(HeaderParameters.AUTHENTICATION);

	if (authenticationToken != null && !StringUtils.isBlank(authenticationToken.trim())) {

	    String piId = getPiId(authenticationToken);

	    if (piId != null) {

		MDC.put(HeaderParameters.PIID, piId);

		LOGGER.info("Requested Pi Id: {} Request Path {}", piId, request.getRequestURI());

		return true;

	    }

	}

	returnUnauthorized(response);

	return false;

    }

    /**
     * Access the pi id from the token.
     * 
     * @param authenticationToken
     *            : The token sent from the request.
     * 
     * @return Pi id.
     */
    private String getPiId(String authenticationToken) {
	try {
	    return tokenAuthenticator.validateTokenAndDetermineIdentityForSystem(authenticationToken, "piid");
	} catch (Exception e) {
	    LOGGER.error("Exception Occured: ", e);
	    return null;
	}
    }

    /**
     * Modifies the response when authentication fails.
     * 
     * @param response
     *            // * : The Http servlet response.
     * 
     * @throws IOException
     *             : Exception when writing to the response object.
     */
    private void returnUnauthorized(HttpServletResponse response) {

	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	StringBuilder errorResponse = new StringBuilder("{\n");
	errorResponse.append("\t\"status\" : \"Error\",\n");
	errorResponse.append("\t\"code\" : \"" + HttpStatus.UNAUTHORIZED.value() + "\",\n");
	errorResponse.append("\t\"message\" : \"" + ExceptionMessages.UNAUTHORIZED_DEFAULT + "\"\n");
	errorResponse.append("}");

	try (PrintWriter writer = response.getWriter()) {
	    writer.write(errorResponse.toString());
	} catch (IOException e) {
	    LOGGER.error(e.getMessage(), e);
	}

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
	// Do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception {
	// Do nothing
    }
}
