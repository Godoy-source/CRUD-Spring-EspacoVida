package com.gabriel.espacovidaspring.services;

import com.gabriel.espacovidaspring.model.Pessoa;
import com.gabriel.espacovidaspring.model.Resposta;
import com.gabriel.espacovidaspring.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaService {

    final PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public ResponseEntity<Resposta> findByCPF(String cpf) {
        var resposta = new Resposta(200, "Pessoa was find with success", null, null);

        if (cpf.length() > 11) {
            resposta.setCode(400);
            resposta.setMessage("CPF Invalid");
            resposta.setObservation("Don't exist cpf with length > 11");
            return ResponseEntity.status(resposta.getCode()).body(resposta);
        }

        var procura = pessoaRepository.findByCPF(cpf);

        if (procura == null) {
            resposta.setCode(404);
            resposta.setMessage("CPF don't registred in Data Base");
        }

        resposta.setObject(procura);
        return ResponseEntity.status(resposta.getCode()).body(resposta);
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.findByID(id);
    }

    public ResponseEntity<Resposta> save(Pessoa pessoa) {
        var resposta = new Resposta(201, "New pessoa created with successful", null, pessoa);
        var procura = findByCPF(pessoa.getCpf()).getBody();
        try {

            if (pessoa.getCpf().length() > 11) {
                return ResponseEntity.status(procura.getCode()).body(procura);
            }
            if (procura.getObject() != null) {
                procura.setCode(400);
                procura.setMessage("Cpf already exists in Data Base");
                return ResponseEntity.status(procura.getCode()).body(procura);
            }

            pessoaRepository.save(pessoa);
            return ResponseEntity.status(resposta.getCode()).body(resposta);


        } catch (Exception err) {
            resposta.setCode(400);
            resposta.setMessage("Error in registering new pessoa");

            return ResponseEntity.status(resposta.getCode()).body(resposta);
        }
    }

    public ResponseEntity<Resposta> delete(String cpf) {
        var procura = findByCPF(cpf);
        var resposta = new Resposta(200, "Pessoa deleted with successful", null, procura.getBody().getObject());

        try {
            if (procura.getBody().getObject() == null) {
                return ResponseEntity.status(procura.getBody().getCode()).body(procura.getBody());
            }
            pessoaRepository.delete((Pessoa) procura.getBody().getObject());
            return ResponseEntity.status(procura.getStatusCode()).body(resposta);
        } catch (Exception err) {
            resposta.setCode(400);
            resposta.setMessage("Error in delete pessoa");
            return ResponseEntity.status(resposta.getCode()).body(resposta);
        }
    }
}
