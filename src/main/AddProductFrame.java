package main;

import javax.swing.*;
import java.awt.*;

public class AddProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtName, txtQuantity, txtPrice;
    private ProductDAO dao = new ProductDAO();

    public AddProductFrame() {
        setTitle("Ürün Ekle");
        setSize(350, 330);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, 5, 5));

        txtName = new JTextField();
        txtQuantity = new JTextField();
        txtPrice = new JTextField();

        add(new JLabel("  Ürün Adı:"));
        add(txtName);
        add(new JLabel("  Miktar:"));
        add(txtQuantity);
        add(new JLabel("  Fiyat:"));
        add(txtPrice);

        JButton btnSave = new JButton("Kaydet");
        add(new JLabel());
        add(btnSave);

        btnSave.addActionListener(e -> {

            if (txtName.getText().trim().isEmpty()
                    || txtQuantity.getText().trim().isEmpty()
                    || txtPrice.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Boş alan bırakmayın!");
                return;
            }

            try {
                int qty = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                if (qty < 0 || price < 0) {
                    JOptionPane.showMessageDialog(this,
                            "Miktar ve fiyat negatif olamaz!");
                    return;
                }

                Product p = new Product(
                        txtName.getText(),
                        qty,
                        price
                );

                dao.addProduct(p);
                JOptionPane.showMessageDialog(this, "Ürün eklendi.");
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Miktar ve fiyat sayısal olmalıdır!");
            }
        });

    }
}

