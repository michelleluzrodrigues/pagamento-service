package br.com.michelle.pagamentoservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PagamentoRecebido {

    private BigDecimal valor;
    private Date dataPagamento;
}
