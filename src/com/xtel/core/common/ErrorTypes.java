package com.xtel.core.common;

public class ErrorTypes {
    public static final Error SUCCESS = new Error(0, "SUCCESS");
    public static final Error UNKNOWN = new Error(99, "UNKNOWN");
    public static final Error TOKEN_INVALID = new Error(1, "TOKEN_INVALID");
    public static final Error TOKEN_EXPIRED = new Error(2, "TOKEN_EXPIRED");
    public static final Error REQUEST_INVALID = new Error(3, "REQUEST_INVALID");
}
