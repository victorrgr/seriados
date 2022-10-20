package br.edu.ies.seriados.controller;

import br.edu.ies.seriados.dto.EpisodioDTO;
import br.edu.ies.seriados.model.Episodio;
import br.edu.ies.seriados.repository.EpisodioRepository;
import br.edu.ies.seriados.repository.TemporadaRepository;
import br.edu.ies.seriados.standard.StandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodio")
public class EpisodioController extends StandardController {

    @Autowired
    private EpisodioRepository repository;

    @Autowired
    private TemporadaRepository temporadaRepository;

    @GetMapping
    private ResponseEntity<List<Episodio>> findAll() {
        var list = repository.findAll();
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Episodio> findById(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity.get());
    }

    @PostMapping
    private ResponseEntity<Episodio> save(@RequestBody @Validated EpisodioDTO dto) {
        if (dto.getTemporada() == null || dto.getTemporada().getId() == null)
            throw new IllegalArgumentException("Temporada não informada.");
        var temporada = temporadaRepository.findById(dto.getTemporada().getId());
        if (temporada.isEmpty())
            throw new IllegalArgumentException("Temporada informada não existe.");

        var entity = Episodio.translate(dto);
        entity.setTemporada(temporada.get());
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Episodio> update(@PathVariable Long id, @RequestBody @Validated EpisodioDTO dto) {
        if (dto.getTemporada() == null || dto.getTemporada().getId() == null)
            throw new IllegalArgumentException("Temporada não informada.");
        var temporada = temporadaRepository.findById(dto.getTemporada().getId());
        if (temporada.isEmpty())
            throw new IllegalArgumentException("Seriado informado não existe.");

        var entity = Episodio.translate(dto);
        var actualEntity = repository.findById(id);
        if (actualEntity.isEmpty())
            return ResponseEntity.notFound().build();

        entity.setId(id);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Episodio> delete(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        repository.delete(entity.get());
        return ResponseEntity.ok().build();
    }
}
