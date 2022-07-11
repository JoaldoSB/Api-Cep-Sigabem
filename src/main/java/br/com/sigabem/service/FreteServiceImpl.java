package br.com.sigabem.service;

import br.com.sigabem.controller.request.FreteRequest;
import br.com.sigabem.domain.Frete;
import br.com.sigabem.dto.CepDTO;
import br.com.sigabem.repository.FreteRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class FreteServiceImpl implements FreteService{

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private Gson gson;

    @Override
    public CepDTO buscarCep(String cep) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(1, ChronoUnit.MINUTES))
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://viacep.com.br/ws/"+cep+"/json"))
                .build();

        HttpResponse<String> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(httpResponse.body(), CepDTO.class);
    }

    @Override
    public Frete calcularFrete(FreteRequest freteRequest) throws IOException, InterruptedException {

        CepDTO cepOrigem = buscarCep(freteRequest.getCepOrigem());
        CepDTO cepDestino = buscarCep(freteRequest.getCepDestino());
        LocalDate dataPrevistaEntrega = LocalDate.now();
        BigDecimal vlTotalFrete = BigDecimal.valueOf(freteRequest.getPeso());

        if(cepOrigem.getDdd().equals(cepDestino.getDdd())) {
            if (cepOrigem.getUf().equals(cepDestino.getUf())) {
                vlTotalFrete = vlTotalFrete.multiply(BigDecimal.valueOf(0.25));
                dataPrevistaEntrega = dataPrevistaEntrega.plusDays(3);
            } else {
                vlTotalFrete = vlTotalFrete.divide(BigDecimal.valueOf(2));
                dataPrevistaEntrega = dataPrevistaEntrega.plusDays(1);
            }
        } else {
            dataPrevistaEntrega = dataPrevistaEntrega.plusDays(10);
        }
        Frete frete = new Frete(
                freteRequest.getPeso(),
                cepOrigem.getCep(),
                cepDestino.getCep(),
                freteRequest.getDestinatario(),
                vlTotalFrete,
                dataPrevistaEntrega,
                LocalDate.now());

        freteRepository.save(frete);
    return frete;
    }
}
