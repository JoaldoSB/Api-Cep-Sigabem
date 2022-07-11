package br.com.sigabem.service;

import br.com.sigabem.controller.request.FreteRequest;
import br.com.sigabem.domain.Frete;
import br.com.sigabem.dto.CepDTO;


import java.io.IOException;

public interface FreteService {

    CepDTO buscarCep(String cep) throws IOException, InterruptedException;

    Frete calcularFrete(FreteRequest freteRequest) throws IOException, InterruptedException;

}
