package br.com.inventario.dao;

import br.com.inventario.dao.common.GenericDAO;
import br.com.inventario.model.Produto;
import br.com.inventario.xml.Produtos;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 25/12/13.
 */
public class ProdutoDAO extends GenericDAO<Produto> {

    public void importarProduto(Produtos.Produto produto) {
        Produto _produto = getPor(new Long(produto.getCodigoBarras()).toString());
        if(_produto == null) {
            _produto = new Produto();
        }

        _produto.setCodigoBarras(new Long(produto.getCodigoBarras()).toString());
        _produto.setCompras(produto.getCompras());
        _produto.setCor(produto.getCor());
        _produto.setEstoqueAtual(produto.getEstoqueAtual());
        _produto.setEstoqueInicial(produto.getEstoqueInicial());
        _produto.setProduto(produto.getDescricao());
        _produto.setMarca(produto.getMarca());
        _produto.setReferencia(produto.getReferencia());
        _produto.setTamanho(produto.getTamanho());
        _produto.setVenda(produto.getValorVendas());
        _produto.setVendas(produto.getVendas());

        if(_produto.getId() == null) {
            salvar(_produto);
        } else {
            atualizar(_produto);
        }
    }

    public Produto getPor(String codigoBarras) {
        Query query = getEM().createQuery("select p from Produto p where p.codigoBarras = :codigoBarras");
        query.setParameter("codigoBarras", codigoBarras);

        List<Produto> produtos = query.getResultList();

        return produtos.isEmpty() ? null : produtos.get(0);
    }
}