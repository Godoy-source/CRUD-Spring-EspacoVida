package com.gabriel.espacovidaspring.controller;

import com.gabriel.espacovidaspring.model.Pessoa;
import com.gabriel.espacovidaspring.model.Resposta;
import com.gabriel.espacovidaspring.services.PessoaService;
import com.gabriel.espacovidaspring.utils.ParamEmpty;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@AllArgsConstructor
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Pessoa> listPessoas() {
        return pessoaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Resposta> createPessoa(@RequestBody Pessoa pessoa) {

        ParamEmpty paramEmpty = new ParamEmpty();

        List<String> camposVaziosPermitidos = new ArrayList<String>();
        camposVaziosPermitidos.add("id");
        camposVaziosPermitidos.add("funcionario");

        var verify = paramEmpty.verifyCampsEmpty(pessoa, camposVaziosPermitidos);

        if (verify.getBody().getCode() == 400) {
            verify.getBody().setObservation("Camps ID and Funcionario can be a null");
            return verify;
        }


        return pessoaService.save(pessoa);
    }

    @PostMapping(path = "filtrar")
    public ResponseEntity<Resposta> findPessoaByCpf(@RequestBody Pessoa pessoa) {
        if (pessoa.getCpf() == null) {
            Resposta resposta = new Resposta(400, "Error, params required null", "Camp cpf can not a be null", null);
            return ResponseEntity.status(resposta.getCode()).body(resposta);
        }
        return pessoaService.findByCPF(pessoa.getCpf());
    }

    @DeleteMapping(path = {"/{data}"})
    public ResponseEntity<Resposta> deletePessoa(@PathVariable(required = true, name = "data") String data) {
        return pessoaService.delete(data);
    }

    /*@GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<Pessoa> listPessoas(@PathVariable(required=false,name="data") String data) {
        System.out.println(data);
        return null;
        //return pessoaService.filter(urlParameter);
    }*/
}
