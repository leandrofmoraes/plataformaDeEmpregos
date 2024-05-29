package br.com.plataformaDeEmpregos.controllers;

import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.com.plataformaDeEmpregos.dtos.contato.ContatoDTO;
import br.com.plataformaDeEmpregos.dtos.endereco.EnderecoDTO;
import br.com.plataformaDeEmpregos.dtos.curriculo.*;
import br.com.plataformaDeEmpregos.models.contato.ContatoModel;
import br.com.plataformaDeEmpregos.models.endereco.EnderecoModel;
import br.com.plataformaDeEmpregos.models.curriculo.*;
import br.com.plataformaDeEmpregos.services.CurriculoService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@AutoConfigureMockMvc // Anotação usada para configurar o MockMvc
@AutoConfigureJsonTesters //Annotação usada para configurar os testes de Json
class CandidatoControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JacksonTester<CurriculoDTO> dadosCurriculoJson;

  @Autowired
  private JacksonTester<DetalhamentoCurriculoDTO> dadosDetalhamentoCurriculoJson;

  @MockBean
  private CurriculoService curriculoService;

  @Test // Declara o método como um teste JUnit.
  @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
  @WithMockUser
  void cadastrar_cenario1() throws Exception {
    var response = mvc
              .perform(post("/curriculo"))
              .andReturn().getResponse();

    assertThat(
      response.getStatus()
    ).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
  @WithMockUser
  void cadastrar_cenario2() throws Exception{

    var dadosCurriculo = dadosParaTeste();
    var dadosDetalhamentoCurriculo = new DetalhamentoCurriculoDTO(
        new CurriculoModel(
          new DadosPessoais(dadosCurriculo.dadosPessoais()),
          new ContatoModel(dadosCurriculo.contato()),
          new EnderecoModel(dadosCurriculo.endereco()),
          new Formacao(dadosCurriculo.formacao()),
          dadosCurriculo.dadosProfissionais().stream().map(DadosProfissionaisModel::new).collect(Collectors.toList())
        )
    );

    when(curriculoService.salvar(any())).thenReturn(dadosDetalhamentoCurriculo);

    var response = mvc.perform(post("/curriculo")
      .contentType(MediaType.APPLICATION_JSON)
      .content(dadosCurriculoJson.write(dadosCurriculo).getJson())
    ).andReturn().getResponse();

    var jsonEsperado = dadosDetalhamentoCurriculoJson.write(dadosDetalhamentoCurriculo).getJson();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(jsonEsperado);

  }

  private CurriculoDTO dadosParaTeste(){
    var dadosPessoais = new DadosPessoaisDTO("João", "Silva", "72699577030", LocalDate.of(1990, 01, 01), "Masculino", "Brasileiro");
    var contato = new ContatoDTO("11223344", "99887766", "joao_silva@samplemail.com", "www.joaosilva.com");
    var endereco = new EnderecoDTO("12345678", "Rua Teste", 123, "Apartamento 101", "Centro", "São Paulo", "SP");
    var formacao = new FormacaoDTO("Ciência da Computação", "Graduação", "Univesp", LocalDate.of(2015, 01, 01), LocalDate.of(2019, 12, 31));
    var dadosProfissionais = List.of(
      new DadosProfissionaisDTO(
        "Empresa A",
        "Analista de Dados",
        LocalDate.of(2020, 01, 01),
        LocalDate.of(2021, 12, 31),
        "Responsável pela análise de dados"
      ),
      new DadosProfissionaisDTO(
        "Empresa B",
        "Desenvolvedor Web",
        LocalDate.of(2018, 01, 01),
        LocalDate.of(2019, 12, 31),
        "Desenvolvimento de aplicações web"
      )
    );
    return new CurriculoDTO(dadosPessoais, contato, endereco, formacao, dadosProfissionais);
  }
}
