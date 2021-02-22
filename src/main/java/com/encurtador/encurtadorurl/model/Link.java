package com.encurtador.encurtadorurl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String link;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String linkEncurtado;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private Integer acessos;

    public Link(String link, String linkEncurtado) {
        this.link = link;
        this.linkEncurtado = linkEncurtado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkEncurtado() {
        return linkEncurtado;
    }

    public void setLinkEncurtado(String linkEncurtado) {
        this.linkEncurtado = linkEncurtado;
    }

    public Integer getAcessos() {
        return acessos;
    }

    public void setAcessos(Integer acessos) {
        this.acessos = acessos;
    }

}
