package br.com.michelle.pagamentoservice.entity;

import br.com.michelle.pagamentoservice.enums.Banco;
import br.com.michelle.pagamentoservice.enums.MeioPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MeioPagamento meioPagamento;
    private String nomeTitular;
    private BigDecimal valor;
    private Banco banco;
    private BigDecimal valorRecebido;

}
