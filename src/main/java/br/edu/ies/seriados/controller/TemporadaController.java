package br.edu.ies.seriados.controller;

import br.edu.ies.seriados.dto.TemporadaDTO;
import br.edu.ies.seriados.model.Temporada;
import br.edu.ies.seriados.repository.SeriadoRepository;
import br.edu.ies.seriados.repository.TemporadaRepository;
import br.edu.ies.seriados.standard.StandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temporada")
public class TemporadaController extends StandardController {

    @Autowired
    private TemporadaRepository repository;

    @Autowired
    private SeriadoRepository seriadoRepository;

    @GetMapping
    private ResponseEntity<List<Temporada>> findAll() {
        var list = repository.findAll();
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Temporada> findById(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity.get());
    }

    @PostMapping
    private ResponseEntity<Temporada> save(@RequestBody @Validated TemporadaDTO dto) {
        if (dto.getSeriado() == null || dto.getSeriado().getId() == null)
            throw new IllegalArgumentException("Seriado não informado.");
        var seriado = seriadoRepository.findById(dto.getSeriado().getId());
        if (seriado.isEmpty())
            throw new IllegalArgumentException("Seriado informado não existe.");

        var entity = Temporada.translate(dto);
        entity.setSeriado(seriado.get());
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Temporada> update(@PathVariable Long id, @RequestBody @Validated TemporadaDTO dto) {
        if (dto.getSeriado() == null || dto.getSeriado().getId() == null)
            throw new IllegalArgumentException("Seriado não informado.");
        var seriado = seriadoRepository.findById(dto.getSeriado().getId());
        if (seriado.isEmpty())
            throw new IllegalArgumentException("Seriado informado não existe.");

        var entity = Temporada.translate(dto);
        var actualEntity = repository.findById(id);
        if (actualEntity.isEmpty())
            return ResponseEntity.notFound().build();

        entity.setId(id);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Temporada> delete(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        repository.delete(entity.get());
        return ResponseEntity.ok().build();
    }
}
