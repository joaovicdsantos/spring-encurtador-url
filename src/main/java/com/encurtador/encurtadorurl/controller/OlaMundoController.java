package com.encurtador.encurtadorurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OlaMundoController {

    @RequestMapping("/")
    @ResponseBody
    public String olaMundo() {
        return "Olá, Mundo!";
    }
}
