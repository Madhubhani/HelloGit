package com.helloRest.constant;

public final class ExceptionMessages {

    public static final String BASE_DEFAULT = "An internal server error has occured.";

    public static final String UNAUTHORIZED_DEFAULT = "Unauthorized Request";

    public static final String BAD_GATEWAY_DEFAULT = "Invalid Response from service request";

    public static final String BAD_REQUEST_DEFAULT = "The request is invalid. Please try again";

    public static final String FORBIDDEN_DEFAULT = "Forbidden: No permissions to view this item";

    public static final String GATEWAY_DEFAULT = "Gateway Time Out Error";

    public static final String NOT_FOUND_DEFAULT = "Resource Not Found";

    public static final String VALIDATION_DEFAULT = "Data input is invalid.";

    public static final String INTERNAL_DEFAULT = "An internal error has occured, Please try again later";

    public static final String METHOD_NOT_ALLOWED_DEFAULT = "Method Not Allowed";

    private ExceptionMessages() {
	// private constructor to prevent creation.
    }
}
