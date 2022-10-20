package br.edu.ies.seriados.repository;

import br.edu.ies.seriados.model.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodioRepository extends JpaRepository<Episodio, Long> {
}
