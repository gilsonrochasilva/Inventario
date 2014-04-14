package br.com.inventario.gui;

import br.com.inventario.dao.RegistroInventarioDAO;
import br.com.inventario.dao.UsuarioDAO;
import br.com.inventario.model.RegistroInventario;
import br.com.inventario.model.Usuario;
import br.com.inventario.model.emuns.Perfil;
import br.com.inventario.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilson on 4/14/14.
 */
public class FormUsuario extends JInternalFrame {

    private JDesktopPane jDesktopPane;
    private JPanel mainContent;
    private JTable tableUsuarios;
    private JTextField tfLogin;
    private JTextField tfNome;
    private JPasswordField tfSenha;
    private JRadioButton rbInventario;
    private JRadioButton rbGerente;
    private JRadioButton rbAdministrador;
    private JButton btSalvar;

    public FormUsuario(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;

        getContentPane().add(mainContent);

        setTitle("Usuários");
        setClosable(true);
        setBounds(0, 0, 500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(rbInventario);
        group.add(rbGerente);
        group.add(rbAdministrador);

        carregarUsuarios();

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            try {
                onSalvar(actionEvent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            }
        });
    }

    private void onSalvar(ActionEvent actionEvent) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(tfLogin.getText() == null || tfLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
        }

        if(tfNome.getText() == null || tfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
        }

        if(tfSenha.getPassword() == null) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.getUm(Usuario.class, tfLogin.getText());
        if(usuario == null) {
            usuario = new Usuario();
            usuario.setLogin(tfLogin.getText());
            usuario.setNome(tfNome.getText());
            usuario.setSenha(Util.toMD5(new String(tfSenha.getPassword())));

            if(rbInventario.isSelected()) {
                usuario.setPerfil(Perfil.INVENTARIO);
            }

            if(rbAdministrador.isSelected()) {
                usuario.setPerfil(Perfil.ADMINISTRADOR);
            }

            if(rbGerente.isSelected()) {
                usuario.setPerfil(Perfil.GERENTE);
            }

            usuarioDAO.salvar(usuario);
        } else {
            usuario.setNome(tfNome.getText());
            usuario.setSenha(Util.toMD5(new String(tfSenha.getPassword())));

            if(rbInventario.isSelected()) {
                usuario.setPerfil(Perfil.INVENTARIO);
            }

            if(rbAdministrador.isSelected()) {
                usuario.setPerfil(Perfil.ADMINISTRADOR);
            }

            if(rbGerente.isSelected()) {
                usuario.setPerfil(Perfil.GERENTE);
            }

            usuarioDAO.atualizar(usuario);
        }

        limpar();
        carregarUsuarios();
    }

    private void limpar() {
        tfLogin.setText("");
        tfNome.setText("");
        tfSenha.setText("");
        rbInventario.setSelected(true);
    }

    private void carregarUsuarios() {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Login");
        columns.add("Nome");
        columns.add("Perfil");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listar();
        for (Usuario usuario : usuarios) {
            values.add(new String[]{
                usuario.getLogin(),
                usuario.getNome(),
                usuario.getPerfil().toString()
            });
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tableUsuarios.setModel(tableModel);
    }
}
