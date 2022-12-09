package com.brunopbrito31.financecontrolapi.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="tb_empenhos")
public class Empenho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empenhoId;

    @Column(name="numero_empenho", unique = true, nullable = false)
    private String numeroEmpenho;

    @Column(name="ano_empenho", unique = true)
    private Integer anoEmpenho;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="data_empenho")
    private LocalDate dataEmpenho = LocalDate.now();

    @Column(name="valor_empenho", precision = 20, scale = 2)
    private BigDecimal valorEmpenho;

    private String observacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "despesa_id", referencedColumnName = "despesaId", nullable = false)
    private Despesa despesa;

    // TODO: Pode ser nulo, verificar
    @OneToMany(mappedBy = "empenho")
    private Set<Pagamento> pagamentos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empenho empenho = (Empenho) o;
        return Objects.equals(empenhoId, empenho.empenhoId) && Objects.equals(numeroEmpenho, empenho.numeroEmpenho) && Objects.equals(anoEmpenho, empenho.anoEmpenho) && Objects.equals(dataEmpenho, empenho.dataEmpenho) && Objects.equals(valorEmpenho, empenho.valorEmpenho) && Objects.equals(observacao, empenho.observacao) && Objects.equals(despesa, empenho.despesa) && Objects.equals(pagamentos, empenho.pagamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empenhoId, numeroEmpenho, anoEmpenho, dataEmpenho, valorEmpenho, observacao, despesa, pagamentos);
    }
}
