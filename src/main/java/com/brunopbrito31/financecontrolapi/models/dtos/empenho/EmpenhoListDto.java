package com.brunopbrito31.financecontrolapi.models.dtos.empenho;

import com.brunopbrito31.financecontrolapi.models.entities.Pagamento;
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
public class EmpenhoListDto {

    private Integer empenhoId;

    private String numeroEmpenho;

    private Integer anoEmpenho;

    private LocalDate dataEmpenho;

    private BigDecimal valorEmpenho;

    private String observacao;

    private Integer despesaDespesaId;

    private Set<Pagamento> pagamentos;

}
