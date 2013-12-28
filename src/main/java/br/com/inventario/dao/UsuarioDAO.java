package br.com.inventario.dao;

import br.com.inventario.util.Util;
import br.com.inventario.dao.common.GenericDAO;
import br.com.inventario.model.Usuario;

import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public Usuario getPor(String login, String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Query query = getEM().createQuery("select u from Usuario u where u.login = :login and u.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", Util.toMD5(senha));

        List<Usuario> usuarios = query.getResultList();

        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
}