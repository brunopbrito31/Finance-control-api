package com.brunopbrito31.financecontrolapi.models.entities;

import com.brunopbrito31.financecontrolapi.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="tb_despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer despesaId;

    @Column(name="numero_protocolo", nullable = false, unique = true)
    private String numeroProtocolo;

    @Column(name="tipo_despesa")
    private String tipoDespesa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="data_protocolo")
    private LocalDate dataProtocolo = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @Column(name="credor_despesa", nullable = false)
    private String credorDespesa;

    @Column(name="descricao_despesa", nullable = false)
    private String descricaoDespesa;

    @Column(name="valor_despesa", precision = 20, scale = 2)
    private BigDecimal valorDespesa;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    // TODO: Pode ser nulo, verificar
    @OneToMany(mappedBy = "despesa")
    private Set<Empenho> empenhos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Despesa despesa = (Despesa) o;
        return despesaId != null && Objects.equals(despesaId, despesa.despesaId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
