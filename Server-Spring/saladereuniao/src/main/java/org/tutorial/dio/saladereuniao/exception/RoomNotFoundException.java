package org.tutorial.dio.saladereuniao.exception;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(String message) {
        super(message);
    }
}
