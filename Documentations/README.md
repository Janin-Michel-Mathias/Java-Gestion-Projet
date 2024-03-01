
# Routes

Voici les routes API disponibles pour le projet:

http://localhost:8343

## Récupérer tous les developeurs disponible pour un futur projet

/allocation/team/{id}

où {id} est l'id d'un projet (2 par exemple)

## Pour Récupérer la liste de tout les projets, leurs stacks et leurs teams associé.

/fullprojects

Pour les routes du CRUD, rendez vous dans /Documentations/REST.md

En ce qui concerne l'application, nous avons le CRUD complet pour toutes les tables de la bdd dont vous trouverez le schéma dans /Documentations/BDD.drawio

Tout les endpoints sont configuré avec leurs controllers respectifs

Pour les fonctionnalités avancés de l'application, nous pouvons récupérer un projet avec toutes les informations le concernant,
sa stack ainsi que les membres de son équipe.

Nous pouvons également récupérer les develeppeurs disponibles pour un projet en particulier, en fonction des dates du projet
