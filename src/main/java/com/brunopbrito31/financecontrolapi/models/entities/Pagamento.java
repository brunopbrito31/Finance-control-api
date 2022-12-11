package com.brunopbrito31.financecontrolapi.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="tb_pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pagamentoId;

    private String numeroPagamento;

    @Column(unique = true)
    private Integer anoPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento = LocalDate.now();

    @Column(precision = 20, scale = 2)
    private BigDecimal valorPagamento;

    private String observacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empenho_id", referencedColumnName = "empenhoId", nullable = false)
    @JsonIgnore
    private Empenho empenho;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(pagamentoId, pagamento.pagamentoId) && Objects.equals(numeroPagamento, pagamento.numeroPagamento) && Objects.equals(anoPagamento, pagamento.anoPagamento) && Objects.equals(dataPagamento, pagamento.dataPagamento) && Objects.equals(valorPagamento, pagamento.valorPagamento) && Objects.equals(observacao, pagamento.observacao) && Objects.equals(empenho, pagamento.empenho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagamentoId, numeroPagamento, anoPagamento, dataPagamento, valorPagamento, observacao, empenho);
    }
}
