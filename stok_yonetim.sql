CREATE DATABASE IF NOT EXISTS stock_db; 
USE stock_db; 

CREATE TABLE IF NOT EXISTS products ( 
id INT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(100) NOT NULL, 
quantity INT NOT NULL, 
price DOUBLE NOT NULL ); 

INSERT INTO products (name, quantity, price) VALUES
('SSD 1TB (Samsung)', 150, 4800.00), 
('SSD 2TB (Samsung)', 180, 8500.00), 
('SSD 512GB (Samsung)', 20, 3200.00), 
('RAM 16GB (Vengeance)', 10, 7500.00), 
('RAM 32GB (Vengeance)', 100, 18900.00), 
('RAM 8GB (Vengeance)', 80, 5000.00), 
('RTX 5070 GPU (NVIDIA)', 150, 38000.00), 
('RTX 5060 GPU (NVIDIA)', 5, 18000.00), 
('RTX 5080 GPU (NVIDIA)', 25, 72000.00), 
('24" Monitör (ASUS)', 100, 4000.00), 
('27" Monitör (ASUS)', 75, 6000.00), 
('27" Monitör (MSI)', 150, 5000.00), 
('i5 CPU (Intel)', 120, 7000.00),
('i7 CPU (Intel)', 50, 12000.00), 
('i9 CPU (Intel)', 120, 18000.00),
('Klavye (Logitech)', 10, 2000.00), 
('Klavye (ASUS)', 100, 1200.00),
('Mouse (Logitech)', 75, 750.00), 
('Mouse (Xiaomi)', 80, 600.00),
('Mouse (Philips)', 120, 500.00);
