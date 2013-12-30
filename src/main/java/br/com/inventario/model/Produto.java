package br.com.inventario.model;

import javax.persistence.*;

/**
 * Created by GilsonRocha on 21/12/13.
 */
@Table
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_barras", length = 13, nullable = false)
    private String codigoBarras;

    @Column(length = 500, nullable = false)
    private String produto;

    @Column(length = 50)
    private String referencia;

    @Column(length = 50)
    private String marca;

    @Column(length = 50)
    private String cor;

    @Column(length = 10)
    private String tamanho;

    @Column(name = "estoque_inicial")
    private Integer estoqueInicial;

    @Column
    private Integer compras;

    @Column
    private Integer vendas;

    @Column(name = "estoque_atual")
    private Integer estoqueAtual;

    @Column(name = "estoque_inventario")
    private Integer estoqueInventario;

    @Column
    private Integer divergencia;

    @Column
    private Float venda;

    @Column
    private Float quebra;

    @Column(name = "quebra_percentual")
    private Float quebraPercentual;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getEstoqueInicial() {
        return estoqueInicial;
    }

    public void setEstoqueInicial(Integer estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }

    public Integer getCompras() {
        return compras;
    }

    public void setCompras(Integer compras) {
        this.compras = compras;
    }

    public Integer getVendas() {
        return vendas;
    }

    public void setVendas(Integer vendas) {
        this.vendas = vendas;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Integer getEstoqueInventario() {
        return estoqueInventario;
    }

    public void setEstoqueInventario(Integer estoqueInventario) {
        this.estoqueInventario = estoqueInventario;
    }

    public Integer getDivergencia() {
        return divergencia;
    }

    public void setDivergencia(Integer divergencia) {
        this.divergencia = divergencia;
    }

    public Float getVenda() {
        return venda;
    }

    public void setVenda(Float venda) {
        this.venda = venda;
    }

    public Float getQuebra() {
        return quebra;
    }

    public void setQuebra(Float quebra) {
        this.quebra = quebra;
    }

    public Float getQuebraPercentual() {
        return quebraPercentual;
    }

    public void setQuebraPercentual(Float quebraPercentual) {
        this.quebraPercentual = quebraPercentual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (!id.equals(produto.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}