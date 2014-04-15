package br.com.inventario.dao;

import br.com.inventario.dao.common.GenericDAO;
import br.com.inventario.model.RegistroInventario;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class RegistroInventarioDAO extends GenericDAO<RegistroInventario> {

    public List<RegistroInventario> ultimosMovimentos(String estacao) {
        Query query = getEM().createQuery("select r from RegistroInventario r where id.estacao = :estacao order by r.dataHoraRegistro desc");
        query.setParameter("estacao", estacao);
        query.setMaxResults(100);

        return query.getResultList();
    }
}
