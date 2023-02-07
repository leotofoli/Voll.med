package br.com.tofoli.api.medico;

import br.com.tofoli.api.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
		Especialidade especialidade, Endereco endereco) {

	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
				medico.getEspecialidade(), medico.getEndereco());
	}
}
