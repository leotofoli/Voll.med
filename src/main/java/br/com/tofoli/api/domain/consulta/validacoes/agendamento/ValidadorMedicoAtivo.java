package br.com.tofoli.api.domain.consulta.validacoes.agendamento;

import br.com.tofoli.api.domain.ValidacaoException;
import br.com.tofoli.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.tofoli.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medico = repository.getReferenceById(dados.idMedico());
        if(!medico.getAtivo()){
            throw  new ValidacaoException("Consulta não pode ser agendado com médico inativo");
        }
    }
}
