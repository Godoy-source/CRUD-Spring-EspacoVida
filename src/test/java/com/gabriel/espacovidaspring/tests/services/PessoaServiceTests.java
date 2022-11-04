package com.gabriel.espacovidaspring.tests.services;

import com.gabriel.espacovidaspring.AplicationConfigTests;
import com.gabriel.espacovidaspring.model.Pessoa;
import com.gabriel.espacovidaspring.model.Resposta;
import com.gabriel.espacovidaspring.repository.PessoaRepository;
import com.gabriel.espacovidaspring.services.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@DisplayName("PessoaServiceTest")

public class PessoaServiceTests extends AplicationConfigTests {


    @Mock
    private PessoaRepository pessoaRepository ;

    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        pessoaService = new PessoaService(pessoaRepository);
    }

    @Test
    @DisplayName("Deve crirar uma nova pessoa no database")
    public void createNewPessoa() {

        Pessoa p = new Pessoa();
        p.setNome("Fernando");
        p.setEmail("Email teste");
        p.setEndereco("Endereco teste");
        p.setImgURL("URL teste");
        p.setTelefone("414231313");
        p.setCpf("Cpf Teste");
        p.setIdade(14);
        p.setHierarquia("Adm");
        var retorno = pessoaService.save(p);
        Mockito.verify(pessoaRepository).findByCPF(p.getCpf());
        Mockito.verify(pessoaRepository).save(p);
        System.out.println(retorno);

    }

    @Test
    @DisplayName("Deve devolver todos as Pessoas criadas")
    public void listPessoas() {
        var retorno= pessoaService.findAll();
        verify(pessoaRepository).findAll();
        System.out.println(retorno);
    }

    @Test
    @DisplayName("Deve encontrar com sucesso")
    public void findByCPF_ComSucesso() {
        var cpfMock = "12345678900";

        when(pessoaRepository.findByCPF(any(String.class)))
                .thenReturn(mockPessoa());

        var response = pessoaService.findByCPF(cpfMock);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getObject());

    }
    @Test
    @DisplayName("Não deve encontrar")
    public void findByCPF_ComErroTamanhoCPF() {
        var cpfMock = "123456789001";

        when(pessoaRepository.findByCPF(any(String.class)))
                .thenReturn(mockPessoa());

        var response = pessoaService.findByCPF(cpfMock);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(400, response.getBody().getCode());

    }

    private Pessoa mockPessoa() {
        var p = new Pessoa();
        p.setCpf("12345678900");
        p.setIdade(18);
        return p;
    }

    /*private Pessoa criarOBJPessoa() {
        Pessoa p = new Pessoa();
        p.setNome("Fernando");
        p.setEmail("Email teste");
        p.setEndereco("Endereco teste");
        p.setImgURL("URL teste");
        p.setTelefone("414231313");
        p.setCpf("Cpf Teste");
        p.setIdade(14);
        p.setHierarquia("Adm");

        Mockito.when(p);
        System.out.println(p);
        return p;
    }*/

}
