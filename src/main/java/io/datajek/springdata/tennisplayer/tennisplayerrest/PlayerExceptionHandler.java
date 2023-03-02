package io.datajek.springdata.tennisplayer.tennisplayerrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.time.ZonedDateTime;

@ControllerAdvice
public class PlayerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> playerNotFoundHandler(PlayerNotFoundException exception,
                                                                     HttpServletRequest req){
            PlayerErrorResponse errorResponse = new PlayerErrorResponse(
                    ZonedDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    req.getRequestURI(),
                    exception.getMessage());

            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> genericHandler(Exception exception,
                                                                     HttpServletRequest req){
        PlayerErrorResponse errorResponse = new PlayerErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                req.getRequestURI(),
                exception.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
