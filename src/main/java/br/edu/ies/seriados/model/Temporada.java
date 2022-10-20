package br.edu.ies.seriados.model;

import br.edu.ies.seriados.dto.TemporadaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Temporada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private String descricao;
    @ManyToOne
    private Seriado seriado;

    public static Temporada translate(TemporadaDTO dto) {
        return new Temporada(dto.getId(), dto.getNumero(), dto.getDescricao(), dto.getSeriado());
    }
}
