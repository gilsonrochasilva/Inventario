package br.com.inventario.gui;

import br.com.inventario.dao.ProdutoDAO;
import br.com.inventario.dao.RegistroInventarioDAO;
import br.com.inventario.model.Produto;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class Main extends JFrame {
    private JPanel contentPane;
    private JButton btRegistroInventario;
    private JButton btTodosMovimentos;
    private JDesktopPane jDesktopPane;
    private JButton btImportarProdutos;
    private JButton btImportarInventario;
    private JPanel statusBar;
    private JLabel statusLabel;
    private JButton btDemostrativoResultado;
    private JButton btLocalEstoque;
    private JButton btUsuarios;
    private JButton btInventarios;

    private Login login;

    public Main(Login login) {
        this.login = login;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Y2G - Sistema de Inventário");
        setContentPane(contentPane);

        btRegistroInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegistroInventario(e);
            }
        });
        btImportarProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onImportarProdutos(e);
            }
        });
        btTodosMovimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMovimentos(e);
            }
        });
        btDemostrativoResultado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDemostrativoResultado(e);
            }
        });
        btUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onUsuarios(actionEvent);
            }
        });
        btInventarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onInventarios(actionEvent);
            }
        });
        btLocalEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onMudarLocalEstoque();
            }
        });
    }

    private void onMudarLocalEstoque() {
        this.setVisible(false);
        this.login.setVisible(true);
    }

    private void onInventarios(ActionEvent e) {
        FormInventario formInventario = new FormInventario(jDesktopPane);
        addInternalFrame("FormInventario", formInventario);
    }

    private void onRegistroInventario(ActionEvent e) {
        RegistroDeInventario registroDeInventario = new RegistroDeInventario(jDesktopPane);
        addInternalFrame("RegistroDeInventario", registroDeInventario);
    }

    private void onUsuarios(ActionEvent e) {
        FormUsuario formUsuario = new FormUsuario(jDesktopPane);
        addInternalFrame("FormUsuario", formUsuario);
    }

    private void onImportarProdutos(ActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos TXT", "txt");

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File fileArquivoTXT = chooser.getSelectedFile();
            if(!fileArquivoTXT.exists()) {
                System.err.println(String.format("Arquivo %s não encontrado", fileArquivoTXT.getPath()));
            }

            try (BufferedReader in = new BufferedReader(new FileReader(fileArquivoTXT))) {

                while (in.ready()) {
                    String s = in.readLine();
                    int contador = 0;

                    Produto produto = Produto.toProduto(s);
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    statusLabel.setText(String.format("Produtos importados %d .", ++contador));
                    produtoDAO.importarProduto(produto);

                    statusLabel.setText("Finalizado com sucesso.");
                }

                in.close();
            } catch (FileNotFoundException e1) {
                statusLabel.setText("Erro");
                JOptionPane.showMessageDialog(null, e1.getMessage());
            } catch (IOException e1) {
                statusLabel.setText("Erro");
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        } else {
            chooser.setVisible(false);
        }
    }

    private void onMovimentos(ActionEvent e) {
        TodosMovimentos todosMovimentos = new TodosMovimentos(jDesktopPane);
        addInternalFrame("TodosMovimentos", todosMovimentos);
    }

    private void addInternalFrame(String name, JInternalFrame jInternalFrame) {
        int position = jDesktopPane.getAllFrames().length * 20;
        jDesktopPane.add(name, jInternalFrame);

        jInternalFrame.setLocation(position, position);
        jInternalFrame.setVisible(true);
        try {
            jInternalFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void onDemostrativoResultado(ActionEvent e) {
        try {
            RegistroInventarioDAO registroInventarioDAO = new RegistroInventarioDAO();
            HashMap<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/report/demostrativo.jasper"), parameters, registroInventarioDAO.getConnection());
            JasperViewer.viewReport(jasperPrint, false, new Locale("pt-BR"));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}