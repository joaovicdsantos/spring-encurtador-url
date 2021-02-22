package com.encurtador.encurtadorurl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.encurtador.encurtadorurl.model.Link;
import com.encurtador.encurtadorurl.repositories.LinkRepository;
import com.encurtador.encurtadorurl.util.LinkUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/links")
public class LinkController {

    @Autowired
    private LinkRepository repository;

    @RequestMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Link>> consultarLinks() {
        if (repository.findAll().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<Link> criarLink(@RequestBody String link) {

        Map<String, String> requestBody;
        try {
            requestBody = new ObjectMapper().readValue(link, HashMap.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        link = requestBody.get("link");
        // Se o link já existir
        Optional<Link> linkRetorno = repository.findByLink(link);
        if (linkRetorno.isPresent())
            return ResponseEntity.ok(linkRetorno.get());

        // Senão devemos criar
        String linkEncurtado;
        do linkEncurtado = LinkUtils.gerarHash();
        while (repository.existsByLinkEncurtado(linkEncurtado));
        return ResponseEntity.ok(repository.save(new Link(link, linkEncurtado)));

    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Link> consultarLink(@PathVariable("id") Long id) {
        Optional<Link> linkRetorno = repository.findById(id);
        if (linkRetorno.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(linkRetorno.get());
    }

    @GetMapping("/filtro")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Link> consultarLinkPelosLinks(@RequestParam("link") String link) {
        Optional<Link> linkRetorno = repository.findByLink(link);
        Optional<Link> linkEncurtadoRetorno = repository.findByLinkEncurtado(link);
        if (linkRetorno.isEmpty() && linkEncurtadoRetorno.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (linkRetorno.isPresent())
            return ResponseEntity.ok(linkRetorno.get());
        return ResponseEntity.ok(linkEncurtadoRetorno.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarLink(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

}