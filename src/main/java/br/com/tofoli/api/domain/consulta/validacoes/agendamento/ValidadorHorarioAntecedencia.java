package br.com.tofoli.api.domain.consulta.validacoes.agendamento;

import br.com.tofoli.api.domain.ValidacaoException;
import br.com.tofoli.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora,dataConsulta).toMinutes();
        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta dever ser agendad com antecedência mínima de 30 minutos");
        }
    }
}
