package br.com.inventario.gui;

import br.com.inventario.dao.RegistroInventarioDAO;
import br.com.inventario.model.RegistroInventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GilsonRocha on 28/12/13.
 */
public class TodosMovimentos extends JInternalFrame {

    private JDesktopPane jDesktopPane;
    private JPanel painelPrincipal;
    private JTable tableMovimentos;

    public TodosMovimentos(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;

        getContentPane().add(painelPrincipal);

        setTitle("Últimos Movimentos");
        setClosable(true);
        setBounds(0, 0, jDesktopPane.getWidth(), jDesktopPane.getHeight());
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        carregarMovimentos();
    }

    private void carregarMovimentos() {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Produto");
        columns.add("Referência");
        columns.add("Categoria");
        columns.add("Subcategoria");
        columns.add("Fabricante");
        columns.add("Data/Hora");
        columns.add("Local");

        RegistroInventarioDAO registroInventarioDAO = new RegistroInventarioDAO();
        List<RegistroInventario> registroInventarios = registroInventarioDAO.ultimosMovimentos();
        for (RegistroInventario registroInventario : registroInventarios) {
            values.add(new String[] {
                registroInventario.getProduto().getDescricao(),
                registroInventario.getProduto().getReferencia(),
                    registroInventario.getProduto().getCategoria(),
                    registroInventario.getProduto().getSubcategoria(),
                    registroInventario.getProduto().getFabricante(),
                    registroInventario.getDataFormatada(),
                    registroInventario.getLocal().toString()
            });
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tableMovimentos.setModel(tableModel);
    }
}
