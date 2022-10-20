package br.edu.ies.seriados.standard;

import br.edu.ies.seriados.standard.StandardResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class StandardController {

    private final SimpleDateFormat formatter;

    public StandardController() {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:ms'Z'");
		this.formatter.setTimeZone(TimeZone.getTimeZone("Brazil/East"));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<StandardResponseError> handleException(HttpServletRequest request, Exception e) {
        var response = new StandardResponseError();
        response.setException(e.toString().split(":")[0]);
        response.setMethod(request.getMethod());
        response.setPath(request.getRequestURI());
        response.setTimestamp(this.formatter.format(new Date()));

        if (e instanceof IllegalArgumentException iae) {
            response.setMessage(iae.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        } else if (e instanceof HttpMessageNotReadableException) {
            response.setMessage("JSON enviado em formato inválido.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(response);
        }

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

}
