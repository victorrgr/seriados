package br.edu.ies.seriados.repository;

import br.edu.ies.seriados.model.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporadaRepository extends JpaRepository<Temporada, Long> {
}
