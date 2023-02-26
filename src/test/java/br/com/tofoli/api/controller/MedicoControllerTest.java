package br.com.tofoli.api.controller;


import br.com.tofoli.api.domain.endereco.DadosEndereco;
import br.com.tofoli.api.domain.endereco.Endereco;
import br.com.tofoli.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicoRepository repository;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJacksonTester;

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua da monica",
                "bairro do limao",
                "00000000",
                "Sao Paulo",
                "SP",
                "Proximo ao cebolinha",
                "2"
        );
    }

    private DadosCadastroMedico getDadosCadastroMedico() {
        return new DadosCadastroMedico(
                "Medico",
                "medico@voll.med",
                "11912345678",
                "123456",
                Especialidade.DERMATOLOGIA,
                dadosEndereco()
        );
    }

    private DadosDetalhamentoMedico getDadosDetalhamentoMedico(DadosCadastroMedico dados) {
        return new DadosDetalhamentoMedico(
                null,
                dados.nome(),
                dados.email(),
                dados.crm(),
                dados.telefone(),
                dados.especialidade(),
                new Endereco(dados.endereco()));
    }


        @DisplayName("Deveria devolver codigo HTTP 400 quando informacoes estao invalidas")
    @Test
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("Deveria devolver codigo HTTP 200 quando informacoes estao validas")
    @Test
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var cadastroMedico = getDadosCadastroMedico();

        when(repository.save(any())).thenReturn(new Medico(cadastroMedico));


        var response = mvc
                .perform(
                        post("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroMedicoJacksonTester.write(cadastroMedico).getJson())
        ).andReturn().getResponse();

        var detalheMedico = getDadosDetalhamentoMedico(cadastroMedico);

        var jsonEsperado = dadosDetalhamentoMedicoJacksonTester.write(detalheMedico).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}