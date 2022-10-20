package br.edu.ies.seriados.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SeriadoDTO {
    private Long id;
    private String nome;
    private LocalDate dataLancamento;
    private String descricao;
}
