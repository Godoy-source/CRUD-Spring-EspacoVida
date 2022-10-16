package com.gabriel.espacovidaspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    private Integer code;
    private String message;
    private String observation;
    private Object object;

}
