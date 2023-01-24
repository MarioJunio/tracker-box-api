package br.com.trackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrackAlreadyPublished extends RuntimeException {

    public TrackAlreadyPublished(String message) {
        super(message);
    }
}
