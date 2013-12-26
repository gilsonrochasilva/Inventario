package br.com.inventario.gui;

import javax.swing.*;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class RegistroDeInventario extends JInternalFrame {

    private JDesktopPane jDesktopPane;
    private JPanel panelPrincipal;
    private JTextField tfCodigoBarras;
    private JButton btBuscar;
    private JPanel painelLeitura;
    private JPanel painelValidacao;
    private JTextField tfCodigo;
    private JTextField tfDescricao;
    private JTextField tfReferencia;
    private JTextField tfMarca;
    private JTextField tfTamanho;
    private JTextField tfCor;
    private JButton btConfirmar;

    public RegistroDeInventario(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;

        getContentPane().add(panelPrincipal);

        setTitle("Registro de Inventário");
        setClosable(true);
        setBounds(0, 0, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tfCodigoBarras.requestFocus();
    }
}