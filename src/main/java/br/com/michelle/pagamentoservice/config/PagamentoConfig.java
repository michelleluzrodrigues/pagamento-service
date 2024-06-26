package br.com.michelle.pagamentoservice.config;

import br.com.michelle.pagamentoservice.receiver.PagamentoReceiver;
import br.com.michelle.pagamentoservice.sender.PagamentoSender;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfig {

    //Rotea mensagens de acordo com os padrões de roteamento
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("financeiro.topic");
    }

    //Rotea mensagens diretamento para filas especificas
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("pagamento.direct");
    }

    //configura a associacao entre as filas e troca de topicos
    private static class ReceiverConfig {

        //cria e retorna fila anonimas
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue3() {
            return new AnonymousQueue();
        }
        
        //rotea a mensagens para filas com base aos roteamento especificado
        @Bean
        public Binding binding1a(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("PIX.*");
        }
            
        @Bean
        public Binding binding2a(TopicExchange topic,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("CARTAO_CREDITO.BRADESCO");
        }

        @Bean
        public Binding binding2b(TopicExchange topic,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("CARTAO_DEBITO.*");
        }

        @Bean
        public Binding binding3a(TopicExchange topic,
                                 Queue autoDeleteQueue3) {
            return BindingBuilder.bind(autoDeleteQueue3)
                    .to(topic)
                    .with("CARTAO_CREDITO.ITAU");
        }
        @Bean
        public Binding binding3b(TopicExchange topic,
                                 Queue autoDeleteQueue3) {
            return BindingBuilder.bind(autoDeleteQueue3)
                    .to(topic)
                    .with("CARTAO_CREDITO.SANTANDER");
        }

        //envia mensagens de pagamento
        @Bean
        public PagamentoReceiver receiver() {
            return new PagamentoReceiver();
        }
    }

    @Bean
    public PagamentoSender sender() {
        return new PagamentoSender();
    }

}
