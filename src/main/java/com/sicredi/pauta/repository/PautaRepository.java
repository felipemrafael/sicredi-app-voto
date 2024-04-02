package com.sicredi.pauta.repository;

import com.sicredi.pauta.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    List<Pauta> findAllByStatus(String aberta);
}
