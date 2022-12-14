package br.edu.ies.seriados.model;

import br.edu.ies.seriados.dto.SeriadoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seriado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataLancamento;
    private String descricao;

    public static Seriado translate(SeriadoDTO dto) {
        assert dto != null;
        return new Seriado(dto.getId(), dto.getNome(), dto.getDataLancamento(), dto.getDescricao());
    }
}