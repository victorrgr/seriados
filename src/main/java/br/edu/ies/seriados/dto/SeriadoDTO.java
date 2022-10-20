package br.edu.ies.seriados.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SeriadoDTO {
    private Long id;
    private String nome;
    private Date dataLancamento;
    private String descricao;
}
