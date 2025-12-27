package main;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtUser;
    private JPasswordField txtPass;

    public LoginFrame() {
        setTitle("Giriş");
        setSize(500, 225);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtUser = new JTextField();
        txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Giriş");

        btnLogin.addActionListener(e -> login());

        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("  Kullanıcı Adı:"));
        add(txtUser);
        add(new JLabel("  Şifre:"));
        add(txtPass);
        add(new JLabel());
        add(btnLogin);
    }

    private void login() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if (user.equals("admin") && pass.equals("1234")) {
            dispose();
            new MenuFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Hatalı giriş!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new LoginFrame().setVisible(true)
        );
    }
}
