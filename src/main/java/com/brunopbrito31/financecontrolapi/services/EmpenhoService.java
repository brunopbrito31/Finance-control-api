package com.brunopbrito31.financecontrolapi.services;


import com.brunopbrito31.financecontrolapi.models.entities.Empenho;
import com.brunopbrito31.financecontrolapi.repositories.EmpenhoRepository;
import com.brunopbrito31.financecontrolapi.services.base.ServiceMaster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpenhoService extends ServiceMaster<Empenho, EmpenhoRepository> {

    public List<Empenho> teste() {
        var repository = super.getRepository();
        return repository.findAll();
    }

}
