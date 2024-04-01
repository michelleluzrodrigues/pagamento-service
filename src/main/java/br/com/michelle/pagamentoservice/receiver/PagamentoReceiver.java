package br.com.michelle.pagamentoservice.receiver;

import br.com.michelle.pagamentoservice.entity.Pagamento;
import br.com.michelle.pagamentoservice.service.PagamentoService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoReceiver {

    @Autowired
    private PagamentoService pagamentoService;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void receive3(String in) throws InterruptedException {
        receive(in, 3);
    }

    public void receive(String in, int receiver) throws InterruptedException {
        System.out.println("instance " + receiver + " [x] Received '" + in + "'");

        var pagamento = new Gson().fromJson(in, Pagamento.class);
        pagamentoService.save(pagamento);

    }
}
