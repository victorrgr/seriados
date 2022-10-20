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

    public static Episodio translate(EpisodioDTO episodio) {
        return new Episodio(episodio.getId(), episodio.getNumero(), episodio.getDescricao(), episodio.getResumo(), episodio.getTemporada());
    }
}
