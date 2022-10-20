package br.edu.ies.seriados.repository;

import br.edu.ies.seriados.model.Seriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriadoRepository extends JpaRepository<Seriado, Long> {
}
