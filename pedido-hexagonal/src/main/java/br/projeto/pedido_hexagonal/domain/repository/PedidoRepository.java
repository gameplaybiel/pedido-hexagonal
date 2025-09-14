package br.projeto.pedido_hexagonal.domain.repository;

import br.projeto.pedido_hexagonal.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
