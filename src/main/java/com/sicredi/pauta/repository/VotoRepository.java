package com.sicredi.pauta.repository;

import com.sicredi.pauta.domain.voto.Voto;
import com.sicredi.pauta.domain.voto.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK> {
}
