package com.gabriel.espacovidaspring.controller;

import com.gabriel.espacovidaspring.model.Funcionario;
import com.gabriel.espacovidaspring.model.RelacionamentoPessoaFuncionario;
import com.gabriel.espacovidaspring.model.Resposta;
import com.gabriel.espacovidaspring.services.FuncionarioService;
import com.gabriel.espacovidaspring.utils.ParamEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<Funcionario> listFuncionarios() {
        return funcionarioService.listFuncionarios();
    }

    @PostMapping()
    public ResponseEntity<Resposta> newFuncionario(@RequestBody RelacionamentoPessoaFuncionario funcionario) {

        ParamEmpty paramEmpty = new ParamEmpty();

        var verify = paramEmpty.verifyCampsEmpty(funcionario, Collections.singletonList("funcionario_id"));

        if (verify.getBody().getCode() == 400) {
            verify.getBody().setObservation("Camps funcionario_id and dt_contratacao can be a null");
            return verify;
        }
        var resposta = funcionarioService.newFuncionario(funcionario);
        return ResponseEntity.status(resposta.getCode()).body(resposta);
    }


  /*@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public Map listFuncionarios() {
        return Collections.singletonMap("status", "teste");
        //return  (List<Funcionario>) ResponseEntity.status(200).body(funcionariosRepository.findAll());
    }*/
}
