CREATE DATABASE clinic CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'clinic'@'localhost' IDENTIFIED BY 'clinicpass';
GRANT ALL PRIVILEGES ON clinic.* TO 'clinic'@'localhost';
FLUSH PRIVILEGES;

