package br.com.tofoli.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tofoli.api.medico.DadosCadastroMedico;
import br.com.tofoli.api.medico.DadosListagemMedico;
import br.com.tofoli.api.medico.Medico;
import br.com.tofoli.api.medico.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		repository.save(new Medico(dados));
	}

	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = { "nome" }) Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosListagemMedico::new);
	}
}