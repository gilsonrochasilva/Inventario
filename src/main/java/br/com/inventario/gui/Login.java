package br.com.inventario.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel contentPane;
    private JButton btLogin;
    private JButton btSair;
    private JTextField tfLogin;
    private JPasswordField tfSenha;

    public Login() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btLogin);
        btSair.addActionListener(new SairActionListener());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btLogin.addActionListener(new EntrarActionListener(this));
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
            Main dialog = new Main();
            dialog.setVisible(true);

            login.setVisible(false);
        }
    }
}
