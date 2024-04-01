package br.com.michelle.pagamentoservice.service;

import br.com.michelle.pagamentoservice.dto.PagamentoRecebido;
import br.com.michelle.pagamentoservice.entity.Pagamento;
import br.com.michelle.pagamentoservice.repository.PagamentoRepository;
import br.com.michelle.pagamentoservice.sender.PagamentoSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagamentoService {

    private PagamentoRepository pagamentoRepository;

    private PagamentoSender pagamentoSender;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoSender pagamentoSender) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoSender = pagamentoSender;
    }

    public void save(Pagamento pagamento) {
        pagamento.setValorRecebido(BigDecimal.ZERO);
        pagamentoRepository.save(pagamento);
    }

    public void baixaPagamento(Long id, PagamentoRecebido pagamentoRecebido) {
        var pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        pagamento.setValorRecebido(pagamento.getValorRecebido().add(pagamentoRecebido.getValor()));
        var pagamentoAtualizado = pagamentoRepository.save(pagamento);

        pagamentoSender.send(pagamentoAtualizado);

    }
}
