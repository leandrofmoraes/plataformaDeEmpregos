package br.com.plataformaDeEmpregos.dtos.curriculo;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
// import jakarta.validation.constraints.Pattern;

/**
* Record que representa os dados necessários para instanciar um objeto da classe DadosPessoais.
*
* @author Leandro F. Moraes
*
*/
public record DadosPessoaisDTO(
  @NotBlank
  String nome,
  @NotBlank
  String sobrenome,
  @NotBlank @CPF
  String cpf,

  @NotNull @Past
  // @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato YYYY-MM-DD")
  LocalDate dataNascimento,

  String genero,
  String nacionalidade
){
}
