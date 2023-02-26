package br.com.tofoli.api.domain.consulta.validacoes.cancelamento;

import br.com.tofoli.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}