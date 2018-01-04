package com.helloRest.constant;

public final class HeaderParameters {
	
    public static final String AUTHENTICATION = "X-Authorization";
    
    public static final String AUTHORIZATION = "Authorization";
    
    //public static final String BEARER = "Bearer ";

    public static final String CORRELATIONID = "correlation-id";

    public static final String PIID = "Piid";

    public static final String LOCATION = "Location";

    public static final int HTTP_STATUS_200 = 200;

    public static final int HTTP_STATUS_300 = 300;

    private HeaderParameters() {
	// private constructor to prevent creation.
    }
}
