package br.com.inventario.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main extends JFrame {
    private JPanel contentPane;
    private JButton btRegistroInventario;
    private JButton btTodosMovimentos;
    private JDesktopPane jDesktopPane;
    private JButton btImportarProdutos;
    private JButton btImportarInventario;

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Ypsilon Consulting - Sistema de Inventário");
        setContentPane(contentPane);

        btRegistroInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroDeInventario registroDeInventario = new RegistroDeInventario(jDesktopPane);
                jDesktopPane.add("RegistroDeInventario", registroDeInventario);
            }
        });
    }

    private void createUIComponents() throws IOException {
        Image img = ImageIO.read(this.getClass().getResource("/br/com/inventario/gui/img/search-add.png"));

        btRegistroInventario = new JButton();
        btRegistroInventario.setToolTipText("Registro de Inventário");
        btRegistroInventario.setIcon(new ImageIcon(img));

        btTodosMovimentos = new JButton();
        btTodosMovimentos.setToolTipText("Registro de Inventário");
        //btTodosMovimentos.setIcon(new ImageIcon((getClass().getResource("/br/com/inventario/gui/img/search-add.png"))));

        btImportarProdutos = new JButton();
        btImportarProdutos.setToolTipText("Registro de Inventário");
        //btImportarProdutos.setIcon(new ImageIcon((getClass().getResource("/br/com/inventario/gui/img/search-add.png"))));

        btImportarInventario = new JButton();
        btImportarInventario.setToolTipText("Registro de Inventário");
        //btImportarInventario.setIcon(new ImageIcon((getClass().getResource("/br/com/inventario/gui/img/search-add.png"))));
    }
}