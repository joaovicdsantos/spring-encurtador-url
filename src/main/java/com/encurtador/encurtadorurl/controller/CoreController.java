package com.encurtador.encurtadorurl.controller;

import java.util.Collections;
import java.util.Map;

import com.encurtador.encurtadorurl.model.Link;
import com.encurtador.encurtadorurl.repositories.LinkRepository;
import com.encurtador.encurtadorurl.util.LinkUtils;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class CoreController {

    @Autowired
    private LinkRepository repository;

    @GetMapping
    public Map<String, String> inicio() {
        return Collections.singletonMap("Guia de Uso", "bla bla");
    }

    @GetMapping("{linkEncurtado}")
    public RedirectView acessarLink(@PathVariable("linkEncurtado") String linkEncurtado) {
        linkEncurtado = LinkUtils.PREFIX + linkEncurtado;
        if (!repository.existsByLinkEncurtado(linkEncurtado))
            return new RedirectView(LinkUtils.PREFIX);
        Link link = repository.findByLinkEncurtado(linkEncurtado);
        repository.updateAcessoById(link.getId());
        return new RedirectView(link.getLink());
    }

}
