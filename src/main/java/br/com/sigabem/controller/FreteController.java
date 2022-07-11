package br.com.sigabem.controller;

import br.com.sigabem.controller.request.FreteRequest;
import br.com.sigabem.controller.response.FreteResponse;
import br.com.sigabem.domain.Frete;
import br.com.sigabem.service.FreteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("Response/Output")
public class FreteController {

    @Autowired
    private FreteService freteService;

    @PostMapping
    public ResponseEntity<FreteResponse> calcularFrete(@RequestBody FreteRequest freteRequest) throws IOException, InterruptedException {

        Frete frete = freteService.calcularFrete(freteRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(frete.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new FreteResponse(frete));
    }

}
