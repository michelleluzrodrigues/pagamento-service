package br.com.michelle.pagamentoservice.controller;

import br.com.michelle.pagamentoservice.dto.PagamentoRecebido;
import br.com.michelle.pagamentoservice.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> receberPagamento(@RequestBody PagamentoRecebido pagamentoRecebido, @PathVariable Long id) {
        pagamentoService.baixaPagamento(id, pagamentoRecebido);

        return ResponseEntity.ok("Pagamento recebido com sucesso!");
    }
}
