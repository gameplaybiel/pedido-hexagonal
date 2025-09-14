package br.projeto.pedido_hexagonal.adapters.in.dto;

import java.time.LocalDate;

public class PedidoDTO {
    private Long id;
    private String descricao;
    private Double valor;
    private LocalDate dataCriacao;

    public PedidoDTO() {}

    public PedidoDTO(Long id, String descricao, Double valor, LocalDate dataCriacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
