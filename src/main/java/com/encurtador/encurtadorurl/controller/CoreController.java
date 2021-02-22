package com.encurtador.encurtadorurl.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.encurtador.encurtadorurl.model.Link;
import com.encurtador.encurtadorurl.repositories.LinkRepository;
import com.encurtador.encurtadorurl.util.LinkUtils;

import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Link> linkRetorno = repository.findByLinkEncurtado(linkEncurtado);
        if (linkRetorno.isEmpty())
            return new RedirectView(LinkUtils.PREFIX);
        repository.updateAcessoById(linkRetorno.get().getId());
        return new RedirectView(linkRetorno.get().getLink());
    }

}
