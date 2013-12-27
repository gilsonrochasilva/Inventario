package br.com.inventario.gui;

import br.com.inventario.Util;
import br.com.inventario.dao.UsuarioDAO;
import br.com.inventario.model.Usuario;
import br.com.inventario.model.emuns.Perfil;

import javax.swing.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main extends JFrame {
    private JPanel contentPane;
    private JButton btRegistroInventario;
    private JButton btTodosMovimentos;
    private JDesktopPane jDesktopPane;
    private JButton btImportar;

    public Main() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Ypsilon Consulting - Sistema de Inventário");

        btRegistroInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroDeInventario registroDeInventario = new RegistroDeInventario(jDesktopPane);
                jDesktopPane.add("RegistroDeInventario", registroDeInventario);
            }
        });
    }
}