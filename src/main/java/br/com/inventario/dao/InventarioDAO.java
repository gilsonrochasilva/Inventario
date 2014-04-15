package br.com.inventario.dao;

import br.com.inventario.dao.common.GenericDAO;
import br.com.inventario.model.Inventario;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class InventarioDAO extends GenericDAO<Inventario> {

    public List<Inventario> listar() {
        Query query = getEM().createQuery("select i from Inventario i order by i.id");
        return query.getResultList();
    }

    public void finalizarTodos() {
        List<Inventario> listar = listar();
        for (Inventario inventario : listar) {
            inventario.setFinalizado(true);
            atualizar(inventario);
        }
    }

    public Inventario getAtivo() {
        Query query = getEM().createQuery("select i from Inventario i where i.finalizado = false");
        return (Inventario) query.getSingleResult();
    }
}