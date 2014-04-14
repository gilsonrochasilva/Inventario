package br.com.inventario.gui;

import br.com.inventario.dao.ParametroDAO;
import br.com.inventario.dao.UsuarioDAO;
import br.com.inventario.model.Parametro;
import br.com.inventario.model.Usuario;
import br.com.inventario.model.emuns.Local;
import br.com.inventario.model.emuns.Perfil;
import br.com.inventario.util.Session;
import br.com.inventario.util.Util;

import javax.swing.*;
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
    private JComboBox cbLocal;

    public Login() {
        setContentPane(contentPane);
        setTitle("Ypsilon Consulting - Sistema de Inventário");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        cbLocal.addItem(Local.DEPOSITO);
        cbLocal.addItem(Local.LOJA);
        cbLocal.addItem(Local.IT);

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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario admin = usuarioDAO.getUm(Usuario.class, "admin");
            if(admin == null) {
                admin = new Usuario();
                admin.setLogin("admin");
                admin.setPerfil(Perfil.ADMINISTRADOR);
                admin.setSenha(Util.toMD5("g00gl3"));
                admin.setNome("Administrador Padrão do Sistema");

                usuarioDAO.salvar(admin);
            }
        } catch (Exception e) {
        }

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
                Usuario usuario = usuarioDAO.getPor(_login, _senha);
                //202CB962AC59075B964B07152D234B70
                if(usuario == null) {
                    JOptionPane.showMessageDialog(login, "Login ou senha inválidos.");
                } else {
                    ParametroDAO parametroDAO = new ParametroDAO();
                    Parametro parametro = parametroDAO.getUm(Parametro.class, "estacao");

                    if(parametro == null) {
                        String estacao = JOptionPane.showInputDialog(null, "Informe o identificador da estação:");

                        if(estacao == null) {
                            return;
                        }

                        parametro = new Parametro();
                        parametro.setId("estacao");
                        parametro.setValor(estacao);

                        parametroDAO.salvar(parametro);

                        Session.put("estacao", parametro);
                    } else {
                        Session.put("estacao", parametro);
                    }

                    Session.put("usuario", usuario);
                    Session.put("local", (Local) login.cbLocal.getSelectedItem());

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
