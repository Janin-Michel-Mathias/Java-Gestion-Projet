# Utilisation du format JSON dans une API REST avec Javalin (Java)

## Introduction

Ce guide vous aidera à comprendre comment utiliser le format JSON dans le cadre du développement d'une API REST avec Javalin en Java. Javalin offre des fonctionnalités pour simplifier la manipulation des données au format JSON lors du traitement des requêtes et des réponses.

## Avantages de l'utilisation de JSON avec Javalin

- **Structure de données flexible :** JSON (JavaScript Object Notation) permet une structuration flexible des données, idéale pour les échanges de données dans une API REST.
- **Facilité d'utilisation :** Javalin simplifie la manipulation de JSON en permettant de transformer facilement les objets Java en JSON et vice versa.
- **Standardisé et largement adopté :** JSON est un format de données standard largement utilisé dans le développement web, ce qui facilite son intégration avec d'autres systèmes.

## Utilisation de JSON avec Javalin

### Lecture de données JSON

Javalin facilite la récupération des données JSON envoyées via les requêtes HTTP. Voici un exemple pour obtenir des données JSON envoyées via une requête POST :

```java
app.post("/api/data", ctx -> {
    String jsonString = ctx.body(); // Récupération du corps de la requête en tant que String JSON
    // Traitement du JSON...
});
```

### Envoi de données JSON en réponse

Vous pouvez renvoyer des données JSON en utilisant Javalin pour les transformer en réponse à une requête. Voici un exemple :

```java
app.get("/api/data", ctx -> {
    YourDataObject data = // Récupération des données à envoyer en JSON
    ctx.json(data); // Envoi des données sous forme de réponse JSON
});
```
### Transformation d'objets Java en JSON

Javalin facilite la conversion d'objets Java en JSON pour les réponses aux requêtes. Voici un exemple avec une classe d'objet `YourDataObject` :

```java
YourDataObject dataObject = // Instanciation de votre objet de données
String jsonOutput = YourDataObjectMapper.convertToJson(dataObject); // Convertit l'objet Java en JSON
ctx.result(jsonOutput); // Envoi du JSON en réponse
```
### Conversion de JSON en objets Java

Pour convertir des données JSON en objets Java, utilisez des bibliothèques comme Gson ou Jackson pour désérialiser les données. Voici un exemple avec Gson :

```java
String jsonString = ctx.body(); // Récupération du JSON depuis la requête
YourDataObject dataObject = YourDataObjectMapper.convertFromJson(jsonString); // Convertit le JSON en objet Java
// Utilisation de l'objet Java...
```