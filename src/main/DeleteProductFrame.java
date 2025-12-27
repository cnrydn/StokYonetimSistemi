package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeleteProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private ProductDAO dao = new ProductDAO();

    public DeleteProductFrame() {
        setTitle("Ürün Sil");
        setSize(650, 450);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
                new String[]{"ID", "Ürün", "Miktar", "Fiyat"}, 0);

        table = new JTable(model);
        refreshTable();

        JButton btnDelete = new JButton("Sil");

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(this,
                        "Lütfen silinecek ürünü seçiniz!");
                return;
            }

            int id = Integer.parseInt(model.getValueAt(row, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Seçili ürünü silmek istiyor musunuz?",
                    "Onay",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dao.deleteProduct(id);
                JOptionPane.showMessageDialog(this, "Ürün silindi.");
                refreshTable();
            }
        });


        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnDelete, BorderLayout.SOUTH);
    }
    
    

    private void refreshTable() {
        model.setRowCount(0);
        dao.getAllProducts().forEach(p ->
                model.addRow(new Object[]{
                        p.getId(), p.getName(), p.getQuantity(), p.getPrice()
                })
        );
    }
}
