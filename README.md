# Sport Tracker — Application de suivi d'entraînements en salle de sport
> 🚧 Projet en cours de développement — front-end Angular en construction

Application de suivi d'entraînements sportifs permettant de gérer ses séances, 
exercices et séries. Projet personnel développé pour approfondir mes compétences 
en Java Spring Boot et Angular.

## Stack technique

**Back-end**
- Java 21 / Spring Boot 3
- Spring Security + OAuth2 Resource Server (JWT RSA)
- Spring Data JPA / Hibernate
- PostgreSQL
- MapStruct / Lombok
- JUnit 5 / Mockito

**Front-end**
- Angular 21 (Standalone Components)
- TypeScript
- SCSS

**Infrastructure**
- Docker Compose

## Prérequis

- Java 21
- Node.js 22+
- Docker

## Lancer le projet

# 1. Démarrer la base de données
docker compose up -d

# 2. Lancer le back-end
cd backend
./gradlew bootRun

# 3. Lancer le front-end
cd frontend
npm install
ng serve

L'API est disponible sur `http://localhost:8080/api`
Le front-end est disponible sur `http://localhost:4200`

## Fonctionnalités

- Authentification JWT (inscription / connexion)
- Gestion des séances d'entraînement (CRUD)
- Bibliothèque d'exercices (globaux + personnels)
- Ajout d'exercices et de séries à une séance
- Suivi de la progression par séance

## Architecture back-end
REST API → Controller → Service → Repository → PostgreSQL

- Sécurité IDOR sur toutes les ressources
- Gestion d'erreurs centralisée (@RestControllerAdvice)
- DTOs pour ne jamais exposer les entités JPA

## Endpoints principaux

| Méthode | Route | Description |
|---------|-------|-------------|
| POST | /api/auth/signup | Inscription |
| POST | /api/auth/signin | Connexion |
| GET | /api/workouts | Liste des séances |
| GET | /api/workouts/{id} | Détail d'une séance |
| POST | /api/workouts | Créer une séance |
| GET | /api/exercises | Liste des exercices |
| POST | /api/workouts/{id}/exercises | Ajouter un exercice |
| POST | /api/workouts/{id}/exercises/{weId}/sets | Ajouter une série |

## Améliorations futures

- Refresh token
- Pagination sur les listes
- Filtres de recherche (Spring Specifications)
- Couverture de tests plus complète
- Gestion de programmes d'entraînement