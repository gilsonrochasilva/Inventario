package br.com.inventario.gui;

import br.com.inventario.dao.InventarioDAO;
import br.com.inventario.dao.UsuarioDAO;
import br.com.inventario.model.Inventario;
import br.com.inventario.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilson on 4/14/14.
 */
public class FormInventario extends JInternalFrame {

    private JDesktopPane jDesktopPane;

    private JPanel mainContent;
    private JTextField tfIndentificador;
    private JTextField tfDescricao;
    private JButton btSalvar;
    private JTable tableInventarios;

    public FormInventario(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;

        getContentPane().add(mainContent);

        setTitle("Inventários");
        setClosable(true);
        setBounds(0, 0, 500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        carregarInventarios();
    }

    private void limpar() {
        tfIndentificador.setText("");
        tfDescricao.setText("");
    }

    private void carregarInventarios() {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Identificador");
        columns.add("Descrição");
        columns.add("Finalizado?");

        InventarioDAO inventarioDAO = new InventarioDAO();
        List<Inventario> inventarios = inventarioDAO.listar();
        for (Inventario inventario : inventarios) {
            values.add(new String[]{
                inventario.getId(),
                inventario.getNome(),
                inventario.getFinalizado() ? "SIM" : "NÃO"
            });
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tableInventarios.setModel(tableModel);
    }
}
