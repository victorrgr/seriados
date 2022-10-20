package br.edu.ies.seriados.dto;


import lombok.Data;

@Data
public class EpisodioDTO {
    private Long id;
    private int numero;
    private String descricao;
    private String resumo;
    private TemporadaDTO temporada;
}
