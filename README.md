# ClinicaXZ Backend

Sistema de Gestão de Clínica Médica desenvolvido com Spring Boot, MySQL e Docker.

## 🚀 Tecnologias Utilizadas

### Infraestrutura e Orquestração
- **Docker** - Containerização das aplicações
- **Docker Compose** - Orquestração de containers
- **Kubernetes** - Suporte para orquestração (arquitetura preparada)

### Backend e Serviços
- **Spring Boot 3.2.0** - Framework Java para desenvolvimento backend
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM (Mapeamento Objeto-Relacional)
- **Maven** - Gerenciamento de dependências
- **Swagger/OpenAPI** - Documentação de APIs

### Banco de Dados
- **MySQL 8.0** - Banco de dados relacional
- **phpMyAdmin** - Interface web para gerenciamento do MySQL

### Comunicação e Integração
- **HTTP/REST** - Protocolo de comunicação entre serviços
- **JSON** - Formato de dados para APIs

### Arquitetura
- **MVC (Model-View-Controller)** - Padrão de projeto
- **Arquitetura em Camadas** - Separação de responsabilidades

## 📋 Funcionalidades

### 🎯 Perfis da Aplicação

#### Secretária
- Cadastra médicos, pacientes, convênios e especialidades
- Agenda, remarca e cancela consultas
- Visualiza agenda completa da clínica

#### Médico
- Visualiza apenas sua própria agenda
- Realiza atendimentos
- Registra prontuários
- Consulta histórico clínico dos pacientes

### 🧩 Épicos Implementados

#### ÉPICO 1 — Autenticação
- Login no sistema com validação de usuário, senha, status e funcionalidades
- Gestão de usuários do sistema (criar, editar, inativar)

#### ÉPICO 2 — Cadastros Administrativos
- CRUD de Médicos
- CRUD de Pacientes
- CRUD de Convênios
- CRUD de Especialidades

**Regras principais:**
- Não excluir médico/paciente se houver histórico → apenas inativar
- Validar dados obrigatórios
- Especialidade precisa existir para vincular médico

#### ÉPICO 3 — Agendamento
- Agendar consulta
- Remarcar consulta
- Cancelar consulta
- Visualizar agenda da clínica (Secretária)
- Visualizar agenda do médico (Médico)

**Regras importantes:**
- Impedir conflito de horários
- Uso de convênio ativo
- Consultas possuem status: AGENDADA, REALIZADA, CANCELADA

#### ÉPICO 4 — Atendimento e Prontuário
- Registrar prontuário durante o atendimento
- Marcar consulta como Realizada
- Acessar histórico clínico completo do paciente

**Regras essenciais:**
- Prontuário é 1:1 com consulta
- Médico só acessa suas consultas
- Histórico completo por paciente

## 🏗️ Estrutura do Projeto

```
clinicaxz/
├── src/
│   ├── main/
│   │   ├── java/com/clinicaxz/
│   │   │   ├── config/          # Configurações (CORS, OpenAPI)
│   │   │   ├── controller/      # Controllers REST
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── enums/           # Enumerações
│   │   │   ├── exception/       # Tratamento de exceções
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositories JPA
│   │   │   └── service/         # Camada de serviços
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Testes unitários
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── init.sql
└── README.md
```

## 🔧 Pré-requisitos

- Docker 20.10+
- Docker Compose 2.0+
- Java 17+ (apenas para desenvolvimento local sem Docker)
- Maven 3.8+ (apenas para desenvolvimento local sem Docker)

## 🚀 Como Executar

### 1. Clonar o repositório (ou descompactar o ZIP)

```bash
cd clinicaxz
```

### 2. Executar com Docker Compose

```bash
docker-compose up --build
```

Este comando irá:
- Criar e iniciar o container MySQL
- Criar e iniciar o container phpMyAdmin
- Compilar e iniciar a aplicação Spring Boot

### 3. Aguardar a inicialização

Aguarde alguns minutos até que todos os serviços estejam prontos. Você verá mensagens indicando que a aplicação foi iniciada com sucesso.

### 4. Acessar os serviços

- **API Backend**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **phpMyAdmin**: http://localhost:8081
  - Usuário: `root`
  - Senha: `root123`

## 📡 Testando com Postman

### Importar Collection

Você pode testar todas as APIs usando o Postman. Aqui estão alguns exemplos de endpoints:

### Endpoints Principais

#### Especialidades
- `GET /api/especialidades` - Listar todas
- `GET /api/especialidades/ativas` - Listar ativas
- `GET /api/especialidades/{id}` - Buscar por ID
- `POST /api/especialidades` - Criar nova
- `PUT /api/especialidades/{id}` - Atualizar
- `PATCH /api/especialidades/{id}/inativar` - Inativar

#### Médicos
- `GET /api/medicos` - Listar todos
- `GET /api/medicos/ativos` - Listar ativos
- `GET /api/medicos/{id}` - Buscar por ID
- `GET /api/medicos/especialidade/{especialidadeId}` - Buscar por especialidade
- `POST /api/medicos` - Criar novo
- `PUT /api/medicos/{id}` - Atualizar
- `PATCH /api/medicos/{id}/inativar` - Inativar

#### Pacientes
- `GET /api/pacientes` - Listar todos
- `GET /api/pacientes/ativos` - Listar ativos
- `GET /api/pacientes/{id}` - Buscar por ID
- `POST /api/pacientes` - Criar novo
- `PUT /api/pacientes/{id}` - Atualizar
- `PATCH /api/pacientes/{id}/inativar` - Inativar

#### Convênios
- `GET /api/convenios` - Listar todos
- `GET /api/convenios/ativos` - Listar ativos
- `GET /api/convenios/{id}` - Buscar por ID
- `POST /api/convenios` - Criar novo
- `PUT /api/convenios/{id}` - Atualizar
- `PATCH /api/convenios/{id}/inativar` - Inativar

#### Consultas
- `GET /api/consultas` - Listar todas
- `GET /api/consultas/{id}` - Buscar por ID
- `GET /api/consultas/medico/{medicoId}` - Listar por médico
- `GET /api/consultas/paciente/{pacienteId}` - Listar por paciente
- `GET /api/consultas/agenda/medico/{medicoId}?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59` - Agenda do médico
- `GET /api/consultas/agenda/completa?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59` - Agenda completa
- `POST /api/consultas` - Agendar consulta
- `PATCH /api/consultas/{id}/remarcar` - Remarcar consulta
- `PATCH /api/consultas/{id}/cancelar` - Cancelar consulta
- `PATCH /api/consultas/{id}/realizar` - Marcar como realizada

#### Prontuários
- `GET /api/prontuarios/{id}` - Buscar por ID
- `GET /api/prontuarios/consulta/{consultaId}` - Buscar por consulta
- `GET /api/prontuarios/historico/paciente/{pacienteId}` - Histórico do paciente
- `POST /api/prontuarios` - Registrar prontuário
- `PUT /api/prontuarios/{id}` - Atualizar prontuário

### Exemplos de Requisições

#### Criar Especialidade
```json
POST http://localhost:8080/api/especialidades
Content-Type: application/json

{
  "nome": "Cardiologia",
  "descricao": "Especialidade médica que cuida do coração",
  "ativo": true
}
```

#### Criar Médico
```json
POST http://localhost:8080/api/medicos
Content-Type: application/json

{
  "nome": "Dr. João Silva",
  "cpf": "12345678901",
  "telefone": "11999998888",
  "email": "joao.silva@clinicaxz.com",
  "endereco": "Rua das Flores, 123",
  "crm": "CRM/SP 123456",
  "especialidade": {
    "id": 1
  },
  "ativo": true
}
```

#### Criar Paciente
```json
POST http://localhost:8080/api/pacientes
Content-Type: application/json

{
  "nome": "Maria Santos",
  "cpf": "98765432100",
  "telefone": "11988887777",
  "email": "maria.santos@email.com",
  "endereco": "Av. Paulista, 1000",
  "dataNascimento": "1985-05-15",
  "sexo": "Feminino",
  "tipoSanguineo": "O+",
  "observacoes": "Alergia a penicilina",
  "ativo": true
}
```

#### Agendar Consulta
```json
POST http://localhost:8080/api/consultas
Content-Type: application/json

{
  "paciente": {
    "id": 1
  },
  "medico": {
    "id": 1
  },
  "convenio": {
    "id": 1
  },
  "dataHora": "2024-12-01T14:00:00",
  "isRetorno": false,
  "carteiraConvenio": "123456789",
  "observacoes": "Primeira consulta"
}
```

#### Registrar Prontuário
```json
POST http://localhost:8080/api/prontuarios
Content-Type: application/json

{
  "consulta": {
    "id": 1
  },
  "queixaPrincipal": "Dor no peito",
  "historiaDoenca": "Paciente relata dor no peito há 2 dias",
  "exameFisico": "PA: 120/80, FC: 72bpm",
  "hipoteseDiagnostica": "Possível angina",
  "conduta": "Solicitar exames complementares",
  "prescricao": "AAS 100mg 1x ao dia",
  "observacoes": "Retornar em 7 dias com exames"
}
```

## 🗄️ Modelo de Dados

### Entidades Principais

- **Pessoa** (abstrata) → Base para Médico, Paciente e Secretária
- **Médico** → Herda de Pessoa, possui CRM e Especialidade
- **Paciente** → Herda de Pessoa, possui dados clínicos
- **Secretária** → Herda de Pessoa, possui PIS
- **Especialidade** → Especialidades médicas
- **Convênio** → Convênios médicos
- **Consulta** → Agendamentos e consultas realizadas
- **Prontuário** → Registro médico da consulta (1:1 com Consulta)
- **Usuário** → Usuários do sistema
- **Perfil** → Perfis de acesso (Médico, Secretária)

### Relacionamentos

- Médico → Especialidade (Many-to-One)
- Médico → Usuário (One-to-One)
- Secretária → Usuário (One-to-One)
- Consulta → Paciente (Many-to-One)
- Consulta → Médico (Many-to-One)
- Consulta → Convênio (Many-to-One)
- Consulta → Prontuário (One-to-One)
- Usuário → Perfil (Many-to-One)

## 🛠️ Comandos Úteis

### Parar os containers
```bash
docker-compose down
```

### Parar e remover volumes (limpar banco de dados)
```bash
docker-compose down -v
```

### Ver logs da aplicação
```bash
docker-compose logs -f backend
```

### Ver logs do MySQL
```bash
docker-compose logs -f mysql
```

### Reconstruir apenas o backend
```bash
docker-compose up --build backend
```

## 📝 Notas Importantes

1. **Dados Iniciais**: O sistema cria automaticamente algumas especialidades e convênios ao iniciar
2. **Validações**: Todas as regras de negócio estão implementadas nos services
3. **Conflito de Horários**: O sistema valida automaticamente conflitos ao agendar/remarcar consultas
4. **Inativação**: Médicos e pacientes com histórico não podem ser excluídos, apenas inativados
5. **Prontuário**: Ao registrar um prontuário, a consulta é automaticamente marcada como REALIZADA

## 🎓 Apresentação para Faculdade

Este projeto está pronto para apresentação acadêmica, contendo:

✅ Arquitetura em camadas (MVC)  
✅ Padrões de projeto (Repository, Service, DTO)  
✅ Boas práticas de desenvolvimento  
✅ Documentação completa com Swagger  
✅ Containerização com Docker  
✅ Banco de dados relacional com relacionamentos complexos  
✅ API REST completa e funcional  
✅ Validações e regras de negócio  
✅ Tratamento de exceções  

## 📞 Suporte

Para dúvidas ou problemas, consulte a documentação do Swagger em http://localhost:8080/swagger-ui.html

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.
