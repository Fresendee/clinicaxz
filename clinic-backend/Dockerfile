# -------------------------------
# Etapa 1: Build do Maven
# -------------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Copia os módulos do projeto
COPY clinic-parent ./clinic-parent
COPY clinic-backend ./clinic-backend

# Build do parent e backend
RUN mvn -f clinic-parent/pom.xml clean install -DskipTests
RUN mvn -f clinic-backend/pom.xml clean package -DskipTests

# -------------------------------
# Etapa 2: Imagem final leve
# -------------------------------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/clinic-backend/target/clinic-backend-1.0.0.jar ./clinic-backend-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","clinic-backend-1.0.0.jar"]
