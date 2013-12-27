package br.com.inventario.gui;

import br.com.inventario.dao.UsuarioDAO;
import br.com.inventario.model.Usuario;
import br.com.inventario.util.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Login extends JFrame {
    private JPanel contentPane;
    private JButton btLogin;
    private JButton btSair;
    private JTextField tfLogin;

    private JPasswordField tfSenha;

    public Login() {
        setContentPane(contentPane);
        setTitle("Ypsilon Consulting - Sistema de Inventário");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        getRootPane().setDefaultButton(btLogin);

        btSair.addActionListener(new SairActionListener());
        btLogin.addActionListener(new EntrarActionListener(this));
    }

    public JButton getBtLogin() {
        return btLogin;
    }

    public void setBtLogin(JButton btLogin) {
        this.btLogin = btLogin;
    }

    public JButton getBtSair() {
        return btSair;
    }

    public void setBtSair(JButton btSair) {
        this.btSair = btSair;
    }

    public JTextField getTfLogin() {
        return tfLogin;
    }

    public void setTfLogin(JTextField tfLogin) {
        this.tfLogin = tfLogin;
    }

    public JPasswordField getTfSenha() {
        return tfSenha;
    }

    public void setTfSenha(JPasswordField tfSenha) {
        this.tfSenha = tfSenha;
    }

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
    }

    private static class SairActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private static class EntrarActionListener implements ActionListener {

        private Login login;

        private EntrarActionListener(Login login) {
            this.login = login;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String _login = login.getTfLogin().getText();
            String _senha = new String(login.getTfSenha().getPassword());

            if(_login.isEmpty()) {
                JOptionPane.showMessageDialog(login, "Login ou senha inválidos.");
            }

            if(_senha.isEmpty()) {
                JOptionPane.showMessageDialog(login, "Login ou senha inválidos.");
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try {
                Usuario usuario = usuarioDAO.getBy(_login, _senha);
                //202CB962AC59075B964B07152D234B70
                if(usuario == null) {
                    JOptionPane.showMessageDialog(login, "Login ou senha inválidos.");
                } else {
                    Session.put("usuario", usuario);

                    Main dialog = new Main();
                    dialog.setVisible(true);

                    login.setVisible(false);
                }
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        }
    }
}
