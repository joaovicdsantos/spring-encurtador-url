package com.encurtador.encurtadorurl.controller;

import com.encurtador.encurtadorurl.model.Link;
import com.encurtador.encurtadorurl.repositories.LinkRespository;
import com.encurtador.encurtadorurl.utils.LinkUtils;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/links")
public class LinkController {

    @Autowired
    private LinkRespository repository;

    @RequestMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity consultarLinks() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("resposta", "Não há links cadastrados!"));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/link")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<Link> criarLink(@RequestBody String link) {

        // Se o link já existir
        if (repository.existsByLink(link))
            return ResponseEntity.ok(repository.findByLink(link));

        // Senão devemos criar
        String linkEncurtado;
        do {
            linkEncurtado = LinkUtils.gerarHash();
        } while (repository.existsByLinkEncurtado(linkEncurtado));
        return ResponseEntity.ok(repository.save(new Link(link, linkEncurtado)));

    }

}