package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class IncreaseProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private ProductDAO dao = new ProductDAO();

    public IncreaseProductFrame() {
        setTitle("Stok Artır");
        setSize(500, 350);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
                new String[]{"ID", "Ürün", "Miktar"}, 0);

        table = new JTable(model);
        refreshTable();

        JButton btnIncrease = new JButton("Artır");

        btnIncrease.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Ürün seçiniz!");
                return;
            }

            int id = Integer.parseInt(model.getValueAt(row, 0).toString());

            String input = JOptionPane.showInputDialog("Artırılacak miktar:");
            if (input == null) return;

            try {
                int qty = Integer.parseInt(input);

                if (qty <= 0) {
                    JOptionPane.showMessageDialog(this, "Pozitif bir değer giriniz!");
                    return;
                }

                dao.increaseStock(id, qty);
                JOptionPane.showMessageDialog(this, "Stok artırıldı.");
                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Geçerli bir sayı giriniz!");
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnIncrease, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        model.setRowCount(0);
        dao.getAllProducts().forEach(p ->
                model.addRow(new Object[]{
                        p.getId(), p.getName(), p.getQuantity()
                })
        );
    }
}
