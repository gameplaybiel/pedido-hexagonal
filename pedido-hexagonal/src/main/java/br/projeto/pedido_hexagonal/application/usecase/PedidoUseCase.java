package br.projeto.pedido_hexagonal.application.usecase;

import br.projeto.pedido_hexagonal.domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoUseCase {
    Pedido criarPedido(Pedido pedido);
    Optional<Pedido> buscaPedidoPorId(Long id);
    List<Pedido> listarPedido();
    void deletarPedido(Long id);
}
