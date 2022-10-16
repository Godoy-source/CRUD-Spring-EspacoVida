package com.gabriel.espacovidaspring.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String imgURL;

    @Column(nullable = false)
    private String telefone;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 3, nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String hierarquia;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Funcionario funcionario;

    //@JsonBackReference
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "funcionario_id")
    //private Funcionario pessoa;
}
