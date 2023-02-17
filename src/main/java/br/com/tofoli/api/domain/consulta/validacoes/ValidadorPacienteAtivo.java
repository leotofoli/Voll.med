package br.com.tofoli.api.domain.consulta.validacoes;

import br.com.tofoli.api.domain.ValidacaoException;
import br.com.tofoli.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.tofoli.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var paciente = repository.findAtivoById(dados.idPaciente());
        if(!paciente) {
            throw  new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
