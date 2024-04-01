package br.com.michelle.pagamentoservice.sender;

import br.com.michelle.pagamentoservice.entity.Pagamento;
import com.google.gson.Gson;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoSender {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private DirectExchange direct;

    public void send(Pagamento pagamento) {
        var key = pagamento.getValor().compareTo(pagamento.getValorRecebido()) <= 0 ? "pago" : "pendente";

        Gson gson = new Gson();
        var json = gson.toJson(pagamento);

        template.convertAndSend(direct.getName(), key, json);
        System.out.println(" [x] Sent '" + json + "'");
    }
}
