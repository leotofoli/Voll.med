package br.com.tofoli.api.medico;

import br.com.tofoli.api.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;

	public Medico(DadosCadastroMedico dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
		this.ativo = true;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
		if (dados.nome() != null)
			this.nome = dados.nome();
		
		if (dados.telefone() != null)
			this.telefone = dados.telefone();
		
		if(dados.endereco()!= null)
			this.endereco.atualizarInformacoes(dados.endereco());

	}

	public void excluir() {
		this.ativo = false;
		
	}
}
