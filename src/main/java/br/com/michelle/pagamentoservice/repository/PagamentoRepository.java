package br.com.michelle.pagamentoservice.repository;

import br.com.michelle.pagamentoservice.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
