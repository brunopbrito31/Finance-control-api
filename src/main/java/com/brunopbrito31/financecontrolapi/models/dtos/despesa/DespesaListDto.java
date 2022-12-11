package com.brunopbrito31.financecontrolapi.models.dtos.despesa;

import com.brunopbrito31.financecontrolapi.models.entities.Empenho;
import com.brunopbrito31.financecontrolapi.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DespesaListDto {

    private Integer despesaId;

    private String numeroProtocolo;

    private String tipoDespesa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataProtocolo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    private String credorDespesa;

    private String descricaoDespesa;

    private BigDecimal valorDespesa;

    private String status;

    private Set<Empenho> empenhos;

}
