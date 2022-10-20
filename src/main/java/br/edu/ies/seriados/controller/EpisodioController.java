package br.edu.ies.seriados.controller;

import br.edu.ies.seriados.dto.EpisodioDTO;
import br.edu.ies.seriados.model.Episodio;
import br.edu.ies.seriados.repository.EpisodioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodio")
public class EpisodioController {

    @Autowired
    private EpisodioRepository repository;

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
        var entity = Episodio.translate(dto);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Episodio> update(@PathVariable Long id, @RequestBody @Validated EpisodioDTO dto) {
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
