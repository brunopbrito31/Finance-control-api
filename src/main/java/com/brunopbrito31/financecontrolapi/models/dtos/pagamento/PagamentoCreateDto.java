package com.brunopbrito31.financecontrolapi.models.dtos.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoCreateDto {

    private String numeroPagamento;

    private Integer anoPagamento;

    private LocalDate dataPagamento = LocalDate.now();

    private BigDecimal valorPagamento;

    private String observacao;
}
