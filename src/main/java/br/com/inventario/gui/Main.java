package br.com.inventario.gui;

import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JPanel contentPane;
    private JButton btRegistroInventario;
    private JButton btTodosMovimentos;

    public Main() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
    }
}
