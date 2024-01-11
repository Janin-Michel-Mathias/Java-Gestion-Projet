# Documentation de Javalin

## Introduction

Javalin est un framework web léger conçu pour simplifier le développement d'applications web et d'API REST avec Java et Kotlin. Il offre une approche simple et intuitive pour la création d'applications web robustes.

## Fonctionnalités principales

- **Léger et simple à utiliser :** Javalin offre une syntaxe concise pour la création d'API RESTful et d'applications web.
- **Compatibilité avec Java et Kotlin :** Vous pouvez utiliser Javalin avec Java et Kotlin, ce qui offre une grande flexibilité pour les développeurs.
- **Gestion des routes :** Javalin facilite la définition des points de terminaison (endpoints) pour vos API et vos pages web.
- **Gestion des requêtes et des réponses HTTP :** Le framework permet de manipuler facilement les requêtes HTTP entrantes et les réponses sortantes.
- **Gestion des middlewares :** Vous pouvez ajouter des middlewares pour gérer l'exécution des requêtes, par exemple pour la sécurité, la journalisation, etc.
- **Intégration avec Jetty et d'autres serveurs HTTP :** Javalin peut être utilisé avec le serveur Jetty par défaut, mais il est également compatible avec d'autres serveurs HTTP.

## Comment démarrer avec Javalin pour une web app avec API REST

### Prérequis

Assurez-vous d'avoir Java ou Kotlin installé sur votre système.

### Étapes pour démarrer un projet avec Javalin

#### Étape 1 : Configuration du projet

1. **Créez un nouveau projet Maven ou Gradle :** Commencez par initialiser un nouveau projet en utilisant Maven ou Gradle.
2. **Ajoutez la dépendance Javalin :** Dans votre fichier de configuration (pom.xml pour Maven ou build.gradle pour Gradle), ajoutez la dépendance à Javalin.

    - Pour Maven :
      ```xml
      <dependencies>
          <dependency>
              <groupId>io.javalin</groupId>
              <artifactId>javalin</artifactId>
              <version>5.6.3</version> <!-- Assurez-vous de spécifier la dernière version -->
          </dependency>
          <!-- Autres dépendances -->
      </dependencies>
      ```

    - Pour Gradle :
      ```groovy
      dependencies {
          implementation 'io.javalin:javalin:5.6.3' // Assurez-vous de spécifier la dernière version
          // Autres dépendances
      }
      ```

#### Étape 2 : Écriture du code

1. **Initialisez Javalin dans votre application :** Créez une classe principale et initialisez Javalin.

    - Exemple (en Java) :
      ```java
        import io.javalin.Javalin;
        
        public class HelloWorld {
        public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
        .get("/", ctx -> ctx.result("Hello World"))
        .start(7070);
        }
        }
      ```

2. **Définissez vos endpoints :** Utilisez Javalin pour définir vos endpoints pour les différentes routes de votre application.

    - Exemple (en Java) :
      ```java
      app.get("/", ctx -> ctx.result("Bonjour, monde!")); // Exemple de route GET
 
      app.post("/api/users", ctx -> {
          // Traitement pour créer un nouvel utilisateur
          ctx.status(201); // Réponse de création réussie
      });
      // Ajoutez d'autres routes selon vos besoins
      ```

#### Étape 3 : Exécution de l'application

- Compilez et exécutez votre application :
    - Pour Maven : Utilisez la commande `mvn clean package` pour compiler votre projet, puis exécutez-le avec `java -jar votre-fichier.jar`.
    - Pour Gradle : Utilisez `gradle build` pour compiler votre projet, puis exécutez-le de manière similaire à Maven avec `java -jar votre-fichier.jar`.

#### Étape 4 : Test de votre API REST

- Utilisez un outil comme Postman ou cURL pour tester vos endpoints REST en envoyant des requêtes HTTP (GET, POST, PUT, DELETE) aux URL que vous avez définies dans votre application Javalin.
