-- Script de Inicialização do Banco de Dados ClinicaXZ

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS clinicaxz;
USE clinicaxz;

-- Dados iniciais para Perfis
INSERT INTO perfis (nome) VALUES ('MEDICO'), ('SECRETARIA') ON DUPLICATE KEY UPDATE nome=nome;

-- Dados iniciais para Especialidades
INSERT INTO especialidades (nome, descricao, ativo) VALUES
('Cardiologia', 'Especialidade médica que se dedica ao estudo, diagnóstico e tratamento das doenças do coração', TRUE),
('Dermatologia', 'Especialidade médica que trata das doenças da pele, cabelos e unhas', TRUE),
('Ortopedia', 'Especialidade médica que cuida do sistema locomotor', TRUE),
('Pediatria', 'Especialidade médica dedicada à assistência à criança e ao adolescente', TRUE),
('Ginecologia', 'Especialidade médica que trata da saúde da mulher', TRUE)
ON DUPLICATE KEY UPDATE nome=nome;

-- Dados iniciais para Convênios
INSERT INTO convenios (nome, cnpj, telefone, email, ativo) VALUES
('Unimed', '12345678000199', '1133334444', 'contato@unimed.com.br', TRUE),
('Bradesco Saúde', '98765432000188', '1144445555', 'contato@bradescosaude.com.br', TRUE),
('SulAmérica', '11223344000177', '1155556666', 'contato@sulamerica.com.br', TRUE)
ON DUPLICATE KEY UPDATE nome=nome;
