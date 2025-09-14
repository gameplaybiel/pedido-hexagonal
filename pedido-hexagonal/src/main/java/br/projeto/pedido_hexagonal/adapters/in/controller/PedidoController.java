package br.projeto.pedido_hexagonal.adapters.in.controller;

import br.projeto.pedido_hexagonal.adapters.in.dto.PedidoDTO;
import br.projeto.pedido_hexagonal.adapters.in.mapper.PedidoMapper;
import br.projeto.pedido_hexagonal.application.usecase.PedidoUseCase;
import br.projeto.pedido_hexagonal.domain.entity.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoUseCase useCase;

    public PedidoController(PedidoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = PedidoMapper.toEntity(pedidoDTO);
        Pedido novoPedido = useCase.criarPedido(pedido);
        return ResponseEntity.ok(PedidoMapper.toDTO(novoPedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscaPedido(@PathVariable Long id) {
        return useCase.buscaPedidoPorId(id)
                .map(PedidoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listaPedido() {
        List<PedidoDTO> pedidos = useCase.listarPedido()
                .stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPedido(@PathVariable Long id) {
        useCase.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
