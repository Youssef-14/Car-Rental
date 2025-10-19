# Car Rental

Projet full-stack pour une application de location de voitures composée de :

- Backend : `car-rental-backend` (Spring Boot, Maven, Java)
- Frontend : `car-rental-frontend` (Angular)

Ce README fournit les instructions pour lancer, tester et développer localement les deux parties du projet.

---

## Table des matières

- [Prérequis](#prérequis)
- [Structure du dépôt](#structure-du-dépôt)
- [Backend - développement et build](#backend---développement-et-build)
- [Frontend - développement et build](#frontend---développement-et-build)
- [Exécuter l'application](#exécuter-lapplication)
- [Tests](#tests)
- [Configuration et profils](#configuration-et-profils)
- [Dépannage](#dépannage)
- [Contribuer](#contribuer)
- [Licence](#licence)

---

## Prérequis

- Java 17 (ou la version requise par le projet) installée et disponible dans le PATH
- Maven (optionnel : le projet embarque `mvnw`/`mvnw.cmd`)
- Node.js (>= 14) et npm ou pnpm/yarn
- Angular CLI (optionnel pour le développement local global) : `npm i -g @angular/cli`

Remarque : les commandes ci-dessous sont données pour l'invite de commandes Windows (`cmd.exe`).

---

## Structure du dépôt

- `car-rental-backend/` : application Spring Boot (Java). Contient `pom.xml` et le code source sous `src/main/java`.
- `car-rental-frontend/` : application Angular. Contient `package.json`, `angular.json` et le code sous `src/`.

---

## Backend - développement et build

Se placer dans le dossier du backend :

```cmd
cd car-rental-backend
```

Lancer l'application en développement (utilise le wrapper Maven inclus) :

```cmd
mvnw.cmd spring-boot:run
```

Construire le JAR (package) :

```cmd
mvnw.cmd clean package -DskipTests
```

Exécuter le JAR généré :

```cmd
java -jar target\car-rental-backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

Remarques :
- Pour exécuter les tests du backend : `mvnw.cmd test`.
- Le projet contient des fichiers de configuration YAML (`application.yml`, `application-dev.yml`, `application-prod.yml`) sous `src/main/resources`.

---

## Frontend - développement et build

Se placer dans le dossier frontend :

```cmd
cd car-rental-frontend
```

Installer les dépendances :

```cmd
npm install
```

Lancer l'application Angular en mode développement (port par défaut 4200) :

```cmd
npm start
```

ou si vous utilisez l'Angular CLI :

```cmd
ng serve --open
```

Construire une version de production :

```cmd
npm run build -- --prod
```

Tests frontend :

```cmd
npm test
```

---

## Exécuter l'application complète

1. Lancer le backend (par défaut écoute probablement sur le port 8080) :

```cmd
cd car-rental-backend
mvnw.cmd spring-boot:run
```

2. Lancer le frontend :

```cmd
cd ..\car-rental-frontend
npm install
npm start
```

3. Ouvrir le navigateur sur `http://localhost:4200` (frontend) et vérifier que les appels API se font vers `http://localhost:8080`.

---

## Tests

- Backend : `cd car-rental-backend && mvnw.cmd test`
- Frontend : `cd car-rental-frontend && npm test`

Ajoutez des tests unitaires et d'intégration au besoin.

---

## Configuration et profils

Le backend utilise des profils Spring Boot. Les fichiers de configuration se trouvent dans :

- `car-rental-backend/src/main/resources/application.yml`
- `car-rental-backend/src/main/resources/application-dev.yml`
- `car-rental-backend/src/main/resources/application-prod.yml`

Pour définir le profil actif :

```cmd
--spring.profiles.active=dev
```

Vous pouvez aussi configurer les variables d'environnement et la connexion à la base de données selon ces fichiers.

---

## Dépannage rapide

- Maven wrapper non exécutable : sous Windows utilisez `mvnw.cmd` au lieu de `./mvnw`.
- Port occupé (backend 8080, frontend 4200) : changer le port dans les fichiers de configuration ou via la ligne de commande.
- Erreurs CORS : si le frontend ne peut pas appeler le backend, activer la configuration CORS côté backend ou utiliser un proxy Angular (`proxy.conf.json`).

---

## Contribuer

1. Fork ou cloner le dépôt
2. Créer une branche feature/bugfix
3. Ajouter des tests couvrant les modifications
4. Ouvrir une pull request avec une description claire des changements

---

## Points d'amélioration / prochaines étapes

- Ajouter un guide d'API (Swagger/OpenAPI) côté backend.
- Ajouter CI (GitHub Actions) pour build/testlint pour backend et frontend.
- Fournir un script Docker Compose pour lancer la stack (backend, frontend et base de données).

---

## Licence

Indiquer la licence du projet ici (ex : MIT). Si aucune licence n'est prévue, mentionner "All rights reserved".

---

Si vous souhaitez que j'ajoute un fichier `docker-compose.yml`, une configuration de CI, ou un guide de contribution plus complet, dites-moi ce que vous préférez et je l'ajouterai.

