package br.edu.ies.seriados.dto;


import br.edu.ies.seriados.model.Temporada;
import lombok.Data;

@Data
public class EpisodioDTO {
    private Long id;
    private int numero;
    private String descricao;
    private String resumo;
    private Temporada temporada;
}
