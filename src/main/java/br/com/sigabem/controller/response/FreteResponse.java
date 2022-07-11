package br.com.sigabem.controller.response;

import br.com.sigabem.domain.Frete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreteResponse {

    private String cepOrigem;

    private String cepDestino;

    private BigDecimal vlTotalFrete;

    private LocalDate dataPrevistaEntrega;

    public FreteResponse(Frete frete) {
        this.cepOrigem = frete.getCepOrigem();
        this.cepDestino = frete.getCepDestino();
        this.vlTotalFrete = frete.getVlTotalFrete();
        this.dataPrevistaEntrega = frete.getDataPrevistaEntrega();
    }

}
