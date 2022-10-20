package br.edu.ies.seriados.dto;

import br.edu.ies.seriados.model.Seriado;
import lombok.Data;


@Data
public class TemporadaDTO {
    private Long id;
    private int numero;
    private String descricao;
    private Seriado seriado;
}
