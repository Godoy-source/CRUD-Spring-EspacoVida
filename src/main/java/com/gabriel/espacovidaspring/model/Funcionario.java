package com.gabriel.espacovidaspring.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Funcionario implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id //Chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long id;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    //@JsonManagedReference
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pessoa")
    //private Pessoa funcionario;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    // Mudar para Date
    private String dt_contratacao;

    @Column(nullable = false)
    private double salario;

}
