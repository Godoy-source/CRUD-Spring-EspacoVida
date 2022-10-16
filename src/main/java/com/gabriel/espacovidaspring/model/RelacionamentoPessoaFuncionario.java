package com.gabriel.espacovidaspring.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelacionamentoPessoaFuncionario {

    private Long pessoa_id;
    private Long funcionario_id;
    private String cargo;
    private String dt_contratacao;
    private double salario;

}
