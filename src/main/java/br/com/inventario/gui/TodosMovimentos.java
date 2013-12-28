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
        columns.add("Marca");
        columns.add("Cor");
        columns.add("Tamanho");
        columns.add("Data/Hora");

        RegistroInventarioDAO registroInventarioDAO = new RegistroInventarioDAO();
        List<RegistroInventario> registroInventarios = registroInventarioDAO.ultimosMovimentos();
        for (RegistroInventario registroInventario : registroInventarios) {
            values.add(new String[] {
                registroInventario.getProduto().getProduto(),
                registroInventario.getProduto().getReferencia(),
                    registroInventario.getProduto().getMarca(),
                    registroInventario.getProduto().getCor(),
                    registroInventario.getProduto().getTamanho(),
                    registroInventario.getDataFormatada()
            });
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tableMovimentos.setModel(tableModel);
    }
}
