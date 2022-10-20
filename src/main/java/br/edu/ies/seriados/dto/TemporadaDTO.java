package br.edu.ies.seriados.dto;

import lombok.Data;


@Data
public class TemporadaDTO {
    private Long id;
    private int numero;
    private String descricao;
    private SeriadoDTO seriado;
}
