package br.com.tofoli.api.domain.consulta.validacoes.agendamento;

import br.com.tofoli.api.domain.ValidacaoException;
import br.com.tofoli.api.domain.consulta.ConsultaRepository;
import br.com.tofoli.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui consulta agendada nesse horário");
        }
    }
}
