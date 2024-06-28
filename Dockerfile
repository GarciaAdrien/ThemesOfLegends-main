# Étape 1: Utiliser une image de base Java
FROM openjdk:21-jdk-slim

# Étape 2: Créer un répertoire de travail
WORKDIR /app

# Étape 3: Copier le fichier jar de votre application dans le répertoire de travail
COPY target/themesoflegends-0.0.1-SNAPSHOT.jar app.jar

# Étape 4: Exposer le port sur lequel l'application va tourner
EXPOSE 8080

# Étape 5: Définir le point d'entrée pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
