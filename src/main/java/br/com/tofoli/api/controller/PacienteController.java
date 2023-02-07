package br.com.tofoli.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tofoli.api.medico.DadosAtualizacaoMedico;
import br.com.tofoli.api.paciente.DadosAtualizacaoPaciente;
import br.com.tofoli.api.paciente.DadosCadastroPaciente;
import br.com.tofoli.api.paciente.DadosListagemPaciente;
import br.com.tofoli.api.paciente.Paciente;
import br.com.tofoli.api.paciente.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
		repository.save(new Paciente(dados));
	}

	@GetMapping
	public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable pagina) {
		return repository.findByAtivoTrue(pagina).map(DadosListagemPaciente::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.excluir();
	}

}
