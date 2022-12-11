package com.brunopbrito31.financecontrolapi.models.dtos.empenho;

import com.brunopbrito31.financecontrolapi.models.entities.Pagamento;
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
public class EmpenhoCreateDto {

    private String numeroEmpenho;

    private Integer anoEmpenho;

    private LocalDate dataEmpenho;

    private BigDecimal valorEmpenho;

    private String observacao;

    // private Set<Pagamento> pagamentos;

}
