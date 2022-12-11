package com.brunopbrito31.financecontrolapi.services;

import com.brunopbrito31.financecontrolapi.models.entities.Pagamento;
import com.brunopbrito31.financecontrolapi.repositories.PagamentoRepository;
import com.brunopbrito31.financecontrolapi.services.base.ServiceMaster;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService extends ServiceMaster<Pagamento, PagamentoRepository> {
}
