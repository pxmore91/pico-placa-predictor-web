package com.stackbuilders.prediction.helper;

public class PlateException extends Exception {
    public PlateException() {
        super("The plate is invalid. The plate needs to be a string conformed by 3 letters followed by 3 to 4 numbers");
    }

    public PlateException(String message) {
        super(message);
    }
}
