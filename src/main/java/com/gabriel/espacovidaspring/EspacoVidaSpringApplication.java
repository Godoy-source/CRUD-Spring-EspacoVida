package com.gabriel.espacovidaspring;

import com.gabriel.espacovidaspring.model.Funcionario;
import com.gabriel.espacovidaspring.model.Pessoa;
import com.gabriel.espacovidaspring.repository.FuncionariosRepository;
import com.gabriel.espacovidaspring.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EspacoVidaSpringApplication {

    @Autowired
    private PessoaRepository pessoaRepository;

    public static void main(String[] args) {
        SpringApplication.run(EspacoVidaSpringApplication.class, args);

    }

    @Bean
    CommandLineRunner initDataBase(PessoaRepository pessoaRepository, FuncionariosRepository funcionariosRepository) {
        return args -> {
            /*Pessoa p = new Pessoa();
            p.setNome("Fernando");
            p.setEmail("Email teste");
            p.setEndereco("Endereco teste");
            p.setImgURL("URL teste");
            p.setTelefone("414231313");
            p.setCpf("Cpf Teste");
            p.setIdade(14);
            p.setHierarquia("Adm");


            Funcionario f = new Funcionario();

            f.setCargo("Cargo teste");
            f.setDt_contratacao("Um dia ae");
            f.setSalario(4411.22);

            f.setPessoa(p);
            p.setFuncionario(f);
            pessoaRepository.save(p);
*/
        };
    }

	/*@Bean
	CommandLineRunner initDataBase(HospedesRepository hospedesRepository, ResponsavelRepository responsavelRepository) {
		return args -> {
			hospedesRepository.deleteAll();
			Responsavel r = new Responsavel();
			Hospedes h = new Hospedes();
			
			r.setEmail("testeeee");
			r.setNomeDoResponsavel("testeeee@emailresponsavel.com");

			h.setNome("Teste Banco");
			h.setIdade(12);
			h.setCpf("01878555600");
			h.setResponsavel(responsavelRepository.save(r));
			hospedesRepository.save(h);
		};
	}*/

}
