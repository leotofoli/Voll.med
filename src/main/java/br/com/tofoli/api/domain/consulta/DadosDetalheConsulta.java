package br.com.tofoli.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalheConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
