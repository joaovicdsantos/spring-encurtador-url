package com.encurtador.encurtadorurl.repositories;

import com.encurtador.encurtadorurl.model.Link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface LinkRespository extends JpaRepository<Link, Long> {

    Link findByLinkEncurtado(String linkEncurtado);
    Link findByLink(String link);

    Boolean existsByLinkEncurtado(String linkEncurtado);
    Boolean existsByLink(String link);

    @Modifying
    @Query(value = "update links set acessos = acessos + 1 where id = ?1", nativeQuery = true)
    void setAcessoById(Long id);

}
