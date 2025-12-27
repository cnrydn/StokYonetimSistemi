package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UpdateProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtName, txtQty, txtPrice;
    private int selectedId = -1;
    private ProductDAO dao = new ProductDAO();

    public UpdateProductFrame() {
        setTitle("Ürün Güncelle");
        setSize(650, 450);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
                new String[]{"ID", "Ürün", "Miktar", "Fiyat"}, 0);

        table = new JTable(model);
        refreshTable();

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtQty.setText(model.getValueAt(row, 2).toString());
                txtPrice.setText(model.getValueAt(row, 3).toString());
            }
        });

        JPanel form = new JPanel(new GridLayout(4, 2));
        txtName = new JTextField();
        txtQty = new JTextField();
        txtPrice = new JTextField();

        form.add(new JLabel("  Ürün Adı:"));
        form.add(txtName);
        form.add(new JLabel("  Miktar:"));
        form.add(txtQty);
        form.add(new JLabel("  Fiyat:"));
        form.add(txtPrice);

        JButton btnUpdate = new JButton("Güncelle");
        form.add(new JLabel());
        form.add(btnUpdate);

        btnUpdate.addActionListener(e -> {

            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this,
                        "Lütfen güncellenecek ürünü seçiniz!");
                return;
            }

            if (txtName.getText().trim().isEmpty()
                    || txtQty.getText().trim().isEmpty()
                    || txtPrice.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Boş alan bırakmayınız!");
                return;
            }

            try {
                int qty = Integer.parseInt(txtQty.getText());
                double price = Double.parseDouble(txtPrice.getText());

                if (qty < 0 || price < 0) {
                    JOptionPane.showMessageDialog(this,
                            "Miktar ve fiyat negatif olamaz!");
                    return;
                }

                Product p = new Product(
                        selectedId,
                        txtName.getText(),
                        qty,
                        price
                );

                dao.updateProduct(p);
                JOptionPane.showMessageDialog(this,
                        "Ürün başarıyla güncellendi.");
                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Miktar ve fiyat sayısal olmalıdır!");
            }
        });


        add(new JScrollPane(table), BorderLayout.CENTER);
        add(form, BorderLayout.SOUTH);
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
