/*
 * www.paulocollares.com.br
 */
package br.com.paulocollares.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Classe responsável para gerar os erros padronizados
 * @author Paulo Collares
 */
public class ApiError {

    public static ResponseEntity badRequest(String mensagem) {
        return error(mensagem, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity notFound(String mensagem) {
        return error(mensagem, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity internalServerError(String mensagem) {
        return error(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity unauthorized(String mensagem) {
        return error(mensagem, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity error(String mensagem, HttpStatus httpStatus) {
        Map<Object, Object> model = new HashMap<>();
        model.put("error", httpStatus.getReasonPhrase());
        model.put("status", httpStatus.value());
        model.put("message", mensagem);

        return new ResponseEntity(model, httpStatus);
    }

}
