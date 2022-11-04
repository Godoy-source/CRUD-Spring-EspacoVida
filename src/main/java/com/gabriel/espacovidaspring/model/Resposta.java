package com.gabriel.espacovidaspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    private Integer code;
    private String message;
    private String observation;
    private Object object;

}
