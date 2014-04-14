package br.com.inventario.model;

import javax.persistence.*;
import java.util.Collections;

/**
 * Created by GilsonRocha on 21/12/13.
 */
@Table
@Entity
public class Produto {

    @Id
    private Integer id;

    @Column(name = "codigo_barras", length = 13)
    private String codigoBarras;

    @Column(length = 500)
    private String descricao;

    @Column(length = 150)
    private String referencia;

    @Column(name = "preco_custo")
    private Double precoCusto;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @Column(length = 150)
    private String categoria;

    @Column(length = 150)
    private String subcategoria;

    @Column(length = 150)
    private String fabricante;

    @Column(name = "saldo_geral")
    private Double saldoGeral;

    @Column(name = "saldo_deposito")
    private Double saldoDeposito;

    @Column(name = "saldo_loja")
    private Double saldoLoja;

    @Column(name = "saldo_it")
    private Double saldoIT;

    @Column(name = "saldo_inventario")
    private Double saldoInventario;

    public static Produto toProduto(String linha) {
        String[] fields = linha.split("\\|");

        Produto produto = new Produto();
        produto.setId(Integer.parseInt(fields[0]));
        produto.setCodigoBarras(fields[1]);
        produto.setDescricao(fields[2]);
        produto.setReferencia(fields[3]);
        produto.setPrecoCusto(Double.parseDouble(fields[4]));
        produto.setPrecoVenda(Double.parseDouble(fields[5]));
        produto.setCategoria(fields[6]);
        produto.setSubcategoria(fields[7]);
        produto.setFabricante(fields[8]);
        produto.setSaldoGeral(Double.parseDouble(fields[9]));
        produto.setSaldoDeposito(Double.parseDouble(fields[10]));
        produto.setSaldoLoja(Double.parseDouble(fields[11]));
        produto.setSaldoIT(Double.parseDouble(fields[12]));

        if(produto.getId().equals(0)) {
            produto.setId(null);
        }

        return produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Double getSaldoGeral() {
        return saldoGeral;
    }

    public void setSaldoGeral(Double saldoGeral) {
        this.saldoGeral = saldoGeral;
    }

    public Double getSaldoDeposito() {
        return saldoDeposito;
    }

    public void setSaldoDeposito(Double saldoDeposito) {
        this.saldoDeposito = saldoDeposito;
    }

    public Double getSaldoLoja() {
        return saldoLoja;
    }

    public void setSaldoLoja(Double saldoLoja) {
        this.saldoLoja = saldoLoja;
    }

    public Double getSaldoIT() {
        return saldoIT;
    }

    public void setSaldoIT(Double saldoIT) {
        this.saldoIT = saldoIT;
    }

    public Double getSaldoInventario() {
        return saldoInventario;
    }

    public void setSaldoInventario(Double saldoInventario) {
        this.saldoInventario = saldoInventario;
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