# Étape 1: Utiliser une image de base Maven pour construire l'application
FROM maven:3.8.4-openjdk:21-jdk-slim AS build
WORKDIR /app

# Copier les fichiers de configuration Maven (pom.xml) pour télécharger les dépendances
COPY pom.xml .

# Copier le reste du code source
COPY src ./src

# Construire l'application avec Maven (ici, il utilise le Maven Wrapper mvnw)
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Étape 2: Utiliser une image de base OpenJDK pour exécuter l'application
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copier le JAR construit à partir de l'étape précédente
COPY --from=build /app/target/themesoflegends-0.0.1-SNAPSHOT.jar app.jar

# Définir le point d'entrée pour l'application Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]

# Exposer le port 8080 (ou tout autre port nécessaire)
EXPOSE 8080
