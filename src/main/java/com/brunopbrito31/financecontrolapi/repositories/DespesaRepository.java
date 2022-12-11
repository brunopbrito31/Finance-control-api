package com.brunopbrito31.financecontrolapi.repositories;

import com.brunopbrito31.financecontrolapi.models.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

}
