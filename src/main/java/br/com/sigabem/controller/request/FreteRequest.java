package br.com.sigabem.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreteRequest {

    private Double peso;

    private String cepOrigem;

    private String cepDestino;

    private String destinatario;
}
