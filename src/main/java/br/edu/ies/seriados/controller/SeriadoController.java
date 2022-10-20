package br.edu.ies.seriados.controller;

import br.edu.ies.seriados.dto.SeriadoDTO;
import br.edu.ies.seriados.model.Seriado;
import br.edu.ies.seriados.repository.SeriadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seriado")
public class SeriadoController {

    @Autowired
    private SeriadoRepository repository;

    @GetMapping
    private ResponseEntity<List<Seriado>> findAll() {
        var list = repository.findAll();
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Seriado> findById(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity.get());
    }

    @PostMapping
    private ResponseEntity<Seriado> save(@RequestBody SeriadoDTO dto) {
        var entity = Seriado.translate(dto);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Seriado> update(@PathVariable Long id, @RequestBody SeriadoDTO dto) {
        var entity = Seriado.translate(dto);
        var actualEntity = repository.findById(id);
        if (actualEntity.isEmpty())
            return ResponseEntity.notFound().build();
        entity.setId(id);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Seriado> delete(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        repository.delete(entity.get());
        return ResponseEntity.ok().build();
    }
}
