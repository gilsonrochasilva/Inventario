package br.com.inventario.gui;

import br.com.inventario.dao.ProdutoDAO;
import br.com.inventario.dao.RegistroInventarioDAO;
import br.com.inventario.model.Produto;
import br.com.inventario.model.RegistroInventario;
import br.com.inventario.model.Usuario;
import br.com.inventario.util.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public class RegistroDeInventario extends JInternalFrame {

    private JDesktopPane jDesktopPane;
    private JPanel panelPrincipal;
    private JTextField tfCodigoBarras;
    private JPanel painelLeitura;
    private JPanel painelValidacao;
    private JTextField tfCodigo;
    private JTextField tfDescricao;
    private JTextField tfReferencia;
    private JTextField tfMarca;
    private JTextField tfTamanho;
    private JTextField tfCor;
    private JButton btConfirmar;
    private JLabel labelProduto;

    private Produto produto;

    public RegistroDeInventario(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;

        getContentPane().add(panelPrincipal);

        setTitle("Registro de Inventário");
        setClosable(true);
        setBounds(0, 0, 620, 470);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tfCodigoBarras.requestFocus();
        tfCodigoBarras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPesquisarProduto(e);
            }
        });
        tfCodigoBarras.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                limpar();
            }
        });
        btConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirmar(e);
            }
        });
    }

    private void onPesquisarProduto(ActionEvent e) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto = produtoDAO.getPor(tfCodigoBarras.getText());

        if(produto == null) {
            labelProduto.setText("Produto não encontrado.");
            limpar();
        } else {
            labelProduto.setText("");

            tfCodigo.setText(produto.getCodigoBarras());
            tfDescricao.setText(produto.getProduto());
            tfReferencia.setText(produto.getReferencia());
            tfMarca.setText(produto.getMarca());
            tfCor.setText(produto.getCor());
            tfTamanho.setText(produto.getTamanho());

            btConfirmar.requestFocus();
        }
    }

    private void onConfirmar(ActionEvent e) {
        RegistroInventario registroInventario = new RegistroInventario();
        registroInventario.setProduto(produto);
        registroInventario.setUsuario((Usuario) Session.get("usuario"));

        RegistroInventarioDAO registroInventarioDAO = new RegistroInventarioDAO();
        registroInventarioDAO.salvar(registroInventario);

        produto = null;
        limpar();
        tfCodigoBarras.setText("");
        tfCodigoBarras.requestFocus();
    }

    public void limpar() {
        tfCodigo.setText("");
        tfDescricao.setText("");
        tfReferencia.setText("");
        tfMarca.setText("");
        tfCor.setText("");
        tfTamanho.setText("");
    }
}