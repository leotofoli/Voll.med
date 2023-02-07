package br.com.tofoli.api.paciente;

import br.com.tofoli.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
