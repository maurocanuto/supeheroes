package com.superheroes.exceptions;

/**
 * @author Mauro Canuto (mauro.canuto@bsc.es)
 *
 * This class deals with exceptions
 */
public class SuperheroException extends Exception {

    public SuperheroException() {
        super();
    }

    public SuperheroException(String message) {
        super(message);
    }

    public SuperheroException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuperheroException(Throwable cause) {
        super(cause);
    }
}
