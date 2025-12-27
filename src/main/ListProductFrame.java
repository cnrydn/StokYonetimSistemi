package main;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;

    private ProductDAO dao = new ProductDAO();

    public ListProductFrame() {

        setTitle("Ürün Listele");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Ürün", "Miktar", "Fiyat"}, 0
        );
        table = new JTable(model);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                int qty = Integer.parseInt(table.getValueAt(row, 2).toString());

                if (qty <= 10) {
                    c.setBackground(Color.PINK);
                } else if (qty <= 50) {
                    c.setBackground(new Color(255, 220, 180));
                } else {
                    c.setBackground(Color.WHITE);
                }

                if (isSelected) {
                    c.setBackground(Color.LIGHT_GRAY);
                }

                return c;
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();

        txtSearch = new JTextField(15);
        JButton btnSearch = new JButton("Ara");
        JButton btnClear = new JButton("Temizle");

        searchPanel.add(new JLabel("Ürün Ara:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnClear);

        add(searchPanel, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();

            if (keyword.isEmpty()) {
                refreshTable();
            } else {
                fillTable(dao.searchProduct(keyword));
            }
        });

        btnClear.addActionListener(e -> {
            txtSearch.setText("");
            refreshTable();
        });

        refreshTable();
    }

    private void refreshTable() {
        fillTable(dao.getAllProducts());
    }

    private void fillTable(List<Product> list) {
        model.setRowCount(0);
        for (Product p : list) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getQuantity(),
                    p.getPrice()
            });
        }
    }
}
