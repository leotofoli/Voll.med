package br.com.tofoli.api.domain.paciente;

import br.com.tofoli.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DadosCadastroPaciente(
		@NotBlank String nome, 
		@NotBlank @Email String email, 
		@NotBlank String telefone, 
		@NotBlank String cpf, 
		@NotNull @Valid DadosEndereco endereco) {

}
