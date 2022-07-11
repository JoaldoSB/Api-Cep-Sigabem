package br.com.sigabem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CepDTO {

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String ddd;
}
