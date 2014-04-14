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
}