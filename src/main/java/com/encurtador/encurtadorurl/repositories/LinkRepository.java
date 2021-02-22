package com.encurtador.encurtadorurl.repositories;

import java.util.Optional;

import com.encurtador.encurtadorurl.model.Link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByLinkEncurtado(String linkEncurtado);
    Optional<Link> findByLink(String link);

    Boolean existsByLinkEncurtado(String linkEncurtado);
    Boolean existsByLink(String link);

    @Transactional
    @Modifying
    @Query(value = "update links set acessos = acessos + 1 where id = ?1", nativeQuery = true)
    void updateAcessoById(Long id);

}
