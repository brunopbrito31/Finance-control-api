package com.brunopbrito31.financecontrolapi.controllers;

import com.brunopbrito31.financecontrolapi.models.dtos.despesa.DespesaListDto;
import com.brunopbrito31.financecontrolapi.models.dtos.empenho.EmpenhoCreateDto;
import com.brunopbrito31.financecontrolapi.models.dtos.pagamento.PagamentoCreateDto;
import com.brunopbrito31.financecontrolapi.models.entities.Despesa;
import com.brunopbrito31.financecontrolapi.models.entities.Empenho;
import com.brunopbrito31.financecontrolapi.models.entities.Pagamento;
import com.brunopbrito31.financecontrolapi.services.DespesaService;
import com.brunopbrito31.financecontrolapi.services.EmpenhoService;
import com.brunopbrito31.financecontrolapi.services.PagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private EmpenhoService empenhoService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            var despesaList = despesaService.getAll();
            var result = despesaList.stream().map(despesa -> modelMapper.map(despesa, DespesaListDto.class)).toList();
            return ResponseEntity.ok(result);
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Despesa despesa) {
        try{
            despesa = despesaService.create(despesa);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(despesa.getDespesaId()).toUri();

            return ResponseEntity.created(uri).body(despesa);
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("{despesaId}/empenhos")
    public ResponseEntity<?> createEmpenho(
        @PathVariable Integer despesaId,
        @RequestBody EmpenhoCreateDto empenhoToCreate
    ) {
        try{
            var optDespesa = despesaService.getById(despesaId);
            if (!optDespesa.isPresent()) return ResponseEntity.badRequest().body("Despesa inválida");

            var empenho = modelMapper.map(empenhoToCreate, Empenho.class);
            empenho.setDespesa(optDespesa.get());

            empenho = empenhoService.create(empenho);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(empenho.getEmpenhoId()).toUri();

            return ResponseEntity.created(uri).body(empenho);

        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{despesaId}/empenhos/{empenhoId}")
    public ResponseEntity<?> getEmpenhoInDespesa(@PathVariable Integer empenhoId) {
        try{
            var optEmpenho = empenhoService.getById(empenhoId);
            if (!optEmpenho.isPresent()) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(optEmpenho.get());
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{despesaId}/empenhos/{empenhoId}")
    public ResponseEntity<?> deleteEmpenhoInDespesa(@PathVariable Integer empenhoId) {
        try{
            var optEmpenho = empenhoService.getById(empenhoId);
            if (!optEmpenho.isPresent()) return ResponseEntity.badRequest().body("Empenho inválido");

            empenhoService.deleteById(empenhoId);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("{despesaId}/empenhos/{empenhoId}/pagamentos")
    public ResponseEntity<?> createPagamentoInEmpenho(
        @PathVariable Integer despesaId,
        @PathVariable Integer empenhoId,
        @RequestBody PagamentoCreateDto pagamentoToCreate)
    {
        try{
            var optEmpenho = empenhoService.getById(empenhoId);
            if (!optEmpenho.isPresent())
                return ResponseEntity.badRequest().body("Empenho inválido");

            if (optEmpenho.get().getDespesa().getDespesaId() != despesaId)
                return ResponseEntity.badRequest().body("Despesa inválida");

            var pagamento = modelMapper.map(pagamentoToCreate, Pagamento.class);
            pagamento = pagamentoService.create(pagamento);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(pagamento.getPagamentoId()).toUri();

            return ResponseEntity.created(uri).body(pagamento);
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{despesaId}/empenhos/{empenhoId}/pagamentos/{pagamentoId}")
    public ResponseEntity<?> getPagamentoInEmpenho(
            @PathVariable Integer despesaId,
            @PathVariable Integer empenhoId,
            @PathVariable Integer pagamentoId)
    {
        try{
            var optEmpenho = empenhoService.getById(empenhoId);
            if (!optEmpenho.isPresent())
                return ResponseEntity.notFound().build();

            if (optEmpenho.get().getDespesa().getDespesaId() != despesaId)
                return ResponseEntity.notFound().build();

            var optPagamento = pagamentoService.getById(pagamentoId);
            if (!optPagamento.isPresent()) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(optPagamento.get());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
