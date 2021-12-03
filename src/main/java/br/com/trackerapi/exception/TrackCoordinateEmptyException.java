package br.com.trackerapi.exception;

public class TrackCoordinateEmptyException extends RuntimeException {

    public TrackCoordinateEmptyException(String message) {
        super(message);
    }
}
