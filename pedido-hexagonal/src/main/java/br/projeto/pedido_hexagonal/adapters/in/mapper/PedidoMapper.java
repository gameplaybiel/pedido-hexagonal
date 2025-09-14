package br.projeto.pedido_hexagonal.adapters.in.mapper;

import br.projeto.pedido_hexagonal.adapters.in.dto.PedidoDTO;
import br.projeto.pedido_hexagonal.domain.entity.Pedido;

public class PedidoMapper {
    public static Pedido toEntity(PedidoDTO dto) {
        return new Pedido(
                dto.getId(),
                dto.getDescricao(),
                dto.getValor(),
                dto.getDataCriacao()
        );
    }

    public static PedidoDTO toDTO(Pedido entity) {
        return new PedidoDTO(
                entity.getId(),
                entity.getDescricao(),
                entity.getValor(),
                entity.getDataCriacao()
        );
    }
}
