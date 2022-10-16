package com.gabriel.espacovidaspring.services;

import com.gabriel.espacovidaspring.model.Funcionario;
import com.gabriel.espacovidaspring.model.RelacionamentoPessoaFuncionario;
import com.gabriel.espacovidaspring.model.Resposta;
import com.gabriel.espacovidaspring.repository.FuncionariosRepository;
import com.gabriel.espacovidaspring.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioService {
    final FuncionariosRepository funcionariosRepository;

    final PessoaRepository pessoaRepository;
    @Autowired
    private PessoaService pessoaService;

    public List<Funcionario> listFuncionarios() {
        return funcionariosRepository.findAll();
    }

    public Resposta newFuncionario(RelacionamentoPessoaFuncionario funcionario) {
        var pessoaData = pessoaService.findById(funcionario.getPessoa_id());
        var resposta = new Resposta(200, "Funcionario attributed with success", null, pessoaData);

        try {

            if (pessoaData == null) {
                resposta.setCode(404);
                resposta.setMessage("Pessoa don't found in Data base");
                return resposta;
            }

            if (pessoaData.getFuncionario() != null) {
                resposta.setCode(400);
                resposta.setMessage("This Pessoa have one Funcionario attributed");
                return resposta;
            }

            Funcionario funcionarioData = new Funcionario();
            funcionarioData.setCargo(funcionario.getCargo());
            funcionarioData.setSalario(funcionario.getSalario());
            funcionarioData.setDt_contratacao(funcionario.getDt_contratacao());

            var funcionarioSaveData = funcionariosRepository.save(funcionarioData);
            funcionariosRepository.updateFuncionario(pessoaData.getId(), funcionarioSaveData.getId());

            pessoaData.setFuncionario(funcionarioData);
            return resposta;
        } catch (Exception err) {
            resposta.setCode(400);
            resposta.setMessage("Error in atribuing Pessoa for Funcionario");
            return resposta;
        }
    }

}
