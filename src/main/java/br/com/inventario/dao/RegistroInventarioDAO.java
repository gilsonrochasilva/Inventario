package br.com.inventario.dao;

import br.com.inventario.dao.common.GenericDAO;
import br.com.inventario.model.RegistroInventario;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class RegistroInventarioDAO extends GenericDAO<RegistroInventario> {

    public List<RegistroInventario> ultimosMovimentos() {
        Query query = getEM().createQuery("select r from RegistroInventario r order by r.dataHoraRegistro desc");
        query.setMaxResults(50);

        return query.getResultList();
    }
}
