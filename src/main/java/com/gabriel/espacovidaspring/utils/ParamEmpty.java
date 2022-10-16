package com.gabriel.espacovidaspring.utils;

import com.gabriel.espacovidaspring.model.Resposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ParamEmpty {
    public ResponseEntity<Resposta> verifyCampsEmpty(Object any, List<String> canBeANull) {
        var resposta = new Resposta(200, null, null, any);
        var objectString = any.toString();

        for (int i = 0; i < canBeANull.size(); i++) {
            if (objectString.contains(canBeANull.get(i) + "=null")) {
                objectString = objectString.replace(canBeANull.get(i) + "=null", canBeANull.get(i) + "=Undefined");
            }
        }


        if (objectString.contains("null")) {
            resposta.setCode(400);
            resposta.setMessage("All camps is required, some one camp has empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }

        return ResponseEntity.status(resposta.getCode()).body(resposta);
    }
}
