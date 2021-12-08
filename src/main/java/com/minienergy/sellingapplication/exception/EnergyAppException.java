package com.minienergy.sellingapplication.exception;

public class EnergyAppException extends Exception {
    private String errorMessage;
    private Throwable ex ;
    private int errorCode;


    public EnergyAppException(final String message, final Throwable exception, final int errCode) {
        super(message);
        ex = exception;
        errorCode = errCode;
    }

    public EnergyAppException(final String message, final int errCode) {
        super(message);
        errorCode = errCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Throwable getEx() {
        return ex;
    }
}
