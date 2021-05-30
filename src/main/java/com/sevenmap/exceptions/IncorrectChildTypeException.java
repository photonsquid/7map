package com.sevenmap.exceptions;

/**
 * Thrown when a child of an incorrect type is affected to a RootNode subtype.
 */
public class IncorrectChildTypeException extends RuntimeException {

    public IncorrectChildTypeException(String report) {
        super(String.format("[Inheritance fatal error] %s", report));
    }
}
