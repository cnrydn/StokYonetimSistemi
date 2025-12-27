package main;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public MenuFrame() {
        setTitle("Stok Yönetim Sistemi - Menü");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnAdd = new JButton("Ürün Ekle");
        JButton btnList = new JButton("Ürün Listele");
        JButton btnUpdate = new JButton("Ürün Güncelle");
        JButton btnDelete = new JButton("Ürün Sil");
        JButton btnIncrease = new JButton("Stok Artır");
        JButton btnOrder = new JButton("Stok Eksilt");
        

        btnAdd.addActionListener(e -> new AddProductFrame().setVisible(true));
        btnList.addActionListener(e -> new ListProductFrame().setVisible(true));
        btnUpdate.addActionListener(e -> new UpdateProductFrame().setVisible(true));
        btnDelete.addActionListener(e -> new DeleteProductFrame().setVisible(true));
        btnIncrease.addActionListener(e -> new IncreaseProductFrame().setVisible(true));
        btnOrder.addActionListener(e -> new OrderProductFrame().setVisible(true));
        

        panel.add(btnAdd);
        panel.add(btnList);        
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnIncrease);
        panel.add(btnOrder);
        

        add(panel);
    }
}
