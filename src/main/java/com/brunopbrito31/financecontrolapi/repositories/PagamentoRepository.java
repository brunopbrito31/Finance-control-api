package com.brunopbrito31.financecontrolapi.repositories;

import com.brunopbrito31.financecontrolapi.models.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
