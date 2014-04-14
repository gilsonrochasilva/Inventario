package br.com.inventario.dao;

import br.com.inventario.dao.common.GenericDAO;
import br.com.inventario.model.Produto;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 25/12/13.
 */
public class ProdutoDAO extends GenericDAO<Produto> {

    public void importarProduto(Produto produto) {

        Produto _prod = getUm(Produto.class, produto.getId());
        if(_prod != null) {
            atualizar(produto);
        } else {
            salvar(produto);
        }
    }

    public Produto getPor(String codigoBarras) {
        Query query = getEM().createQuery("select p from Produto p where p.codigoBarras = :codigoBarras");
        query.setParameter("codigoBarras", codigoBarras);

        List<Produto> produtos = query.getResultList();

        if(produtos.isEmpty()) {
            return getUm(Produto.class, Integer.parseInt(codigoBarras));
        } else {
            return produtos.get(0);
        }
    }
}