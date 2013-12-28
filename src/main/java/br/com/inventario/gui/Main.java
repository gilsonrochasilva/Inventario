package br.com.inventario.gui;

import br.com.inventario.dao.ProdutoDAO;
import br.com.inventario.model.Produto;
import br.com.inventario.xml.Produtos;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JPanel contentPane;
    private JButton btRegistroInventario;
    private JButton btTodosMovimentos;
    private JDesktopPane jDesktopPane;
    private JButton btImportarProdutos;
    private JButton btImportarInventario;
    private JPanel statusBar;
    private JLabel statusLabel;

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Ypsilon Consulting - Sistema de Inventário");
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
            try {
                onImportarProdutos(e);
            } catch (JAXBException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
            }
        });
        btTodosMovimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMovimentos(e);
            }
        });
    }

    private void onRegistroInventario(ActionEvent e) {
        RegistroDeInventario registroDeInventario = new RegistroDeInventario(jDesktopPane);
        jDesktopPane.add("RegistroDeInventario", registroDeInventario);
        jDesktopPane.setSelectedFrame(registroDeInventario);
    }

    private void onImportarProdutos(ActionEvent e) throws JAXBException {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos XML", "xml");

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File fileArquivoXML = chooser.getSelectedFile();
            if(!fileArquivoXML.exists()) {
                System.err.println(String.format("Arquivo %s não encontrado", fileArquivoXML.getPath()));
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(Produtos.class);

            StreamSource streamSource = new StreamSource(fileArquivoXML);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Produtos produtos = (Produtos) unmarshaller.unmarshal(streamSource, Produtos.class).getValue();

            ProdutoDAO produtoDAO = new ProdutoDAO();

            int contador = 0;
            for(Produtos.Produto produto : produtos.getProduto()) {
                statusLabel.setText(String.format("Importando %d de %d.", ++contador, produtos.getProduto().size()));
                produtoDAO.importarProduto(produto);
            }

            statusLabel.setText(String.format("Finalizado com sucesso.", ++contador, produtos.getProduto().size()));
        } else {
            chooser.setVisible(false);
        }
    }

    private void onMovimentos(ActionEvent e) {
        TodosMovimentos todosMovimentos = new TodosMovimentos(jDesktopPane);
        jDesktopPane.add("TodosMovimentos", todosMovimentos);
        jDesktopPane.setSelectedFrame(todosMovimentos);
    }
}