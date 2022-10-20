package br.edu.ies.seriados.model;

import br.edu.ies.seriados.dto.EpisodioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private String descricao;
    private String resumo;
    @ManyToOne
    private Temporada temporada;

    public static Episodio translate(EpisodioDTO dto) {
        assert dto != null;
        var temporada = dto.getTemporada() != null ? Temporada.translate(dto.getTemporada()) : null;
        return new Episodio(dto.getId(), dto.getNumero(), dto.getDescricao(), dto.getResumo(), temporada);
    }
}
