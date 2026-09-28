# AutoLoc

Plateforme de gestion de location de véhicules multi-agences, développée dans le cadre de l'UP **Architecture des Systèmes d'Information (ASI)** — ESPRIT.

## Contexte métier

**AutoLoc** est une entreprise de location de véhicules disposant de plusieurs agences réparties dans différentes villes. Chaque agence gère une flotte de véhicules de catégories variées (citadine, berline, SUV, utilitaire). Les clients réservent un véhicule pour une période donnée ; à la signature, un contrat est établi et des paiements y sont rattachés.

L'objectif est de numériser l'ensemble du processus — réservation, contractualisation, facturation, suivi de flotte et relances automatiques — au travers d'une application back-end Spring Boot exposant une API REST.

## Acteurs du système

| Rôle | Description | Droits principaux |
|---|---|---|
| **Client** | Particulier ou professionnel souhaitant louer un véhicule | Consulter les véhicules disponibles, créer/annuler une réservation, consulter ses contrats |
| **Agent d'agence** | Employé en charge de la gestion opérationnelle d'une agence | Gérer les véhicules, valider une réservation, établir un contrat, enregistrer un paiement |
| **Responsable d'agence (Manager)** | Supervise une agence et son personnel | Droits Agent + gestion des employés, consultation des statistiques de l'agence |
| **Administrateur** | Administre la plateforme | Gestion des agences, des catégories de véhicules, statistiques globales, configuration |

## Modules fonctionnels

- **Gestion des agences & de la flotte** — CRUD des agences, des véhicules et de leurs catégories ; suivi de la disponibilité et du statut.
- **Gestion des clients** — Inscription, mise à jour du profil, historique des réservations et des contrats.
- **Réservation** — Recherche de véhicules disponibles par ville, catégorie et période ; création, modification et annulation.
- **Contractualisation & paiement** — Génération d'un contrat à la validation d'une réservation, enregistrement des paiements.
- **Tarification** — Calcul du tarif selon la catégorie, la durée, la période et les équipements optionnels.
- **Tâches planifiées** — Libération automatique des véhicules, alertes de contrats arrivant à échéance, statistiques.
- **Reporting & qualité** — Statistiques d'occupation et de chiffre d'affaires, indicateurs de qualité du code.

## Modèle de données

| Entité | Attributs principaux | Association |
|---|---|---|
| **Agence** | id, nom, ville, adresse, telephone | 1 Agence → N Vehicule ; 1 Agence → N Employe |
| **Vehicule** | id, immatriculation, marque, modele, categorie, tarifJournalier, statut | N Vehicule → 1 Agence ; N Vehicule ↔ N Equipement |
| **Equipement** | id, libelle | N Equipement ↔ N Vehicule |
| **Client** | id, nom, prenom, email, telephone, numPermis, dateInscription | 1 Client → N Reservation |
| **Employe** | id, nom, prenom, role | N Employe → 1 Agence |
| **Reservation** | id, dateDebut, dateFin, statut | N Reservation → 1 Client ; N Reservation → 1 Vehicule ; 1 Reservation → 1 Contrat |
| **Contrat** | id, dateSignature, montantTotal, valide | 1 Contrat → 1 Reservation ; 1 Contrat → N Paiement |
| **Paiement** | id, montant, datePaiement, modePaiement | N Paiement → 1 Contrat |
| **Maintenance** | id, dateDebut, dateFin, description | N Maintenance → 1 Vehicule |

### Enums

- `CategorieVehicule` : CITADINE, BERLINE, SUV, UTILITAIRE
- `StatutVehicule` : DISPONIBLE, LOUE, MAINTENANCE
- `StatutReservation` : EN_ATTENTE, CONFIRMEE, ANNULEE, TERMINEE
- `ModePaiement` : CARTE, ESPECES, VIREMENT
- `Role` : AGENT, MANAGER

## Architecture

L'application adopte un style **monolithique**, structuré selon le patron **Layered Architecture** (architecture en couches) :

```
Couche présentation (web.controller)
        ↓
Couche transfert (web.dto)
        ↓
Couche métier (service)
        ↓
Couche transversale (aspect / scheduler)
        ↓
Couche domaine (domain)
        ↓
Couche persistance (repository)
        ↓
Base de données relationnelle
```

| Couche | Package | Contenu |
|---|---|---|
| Presentation (API) | `web.controller` | VehiculeController, ClientController, ReservationController, ContratController, StatistiqueController, OpenApiConfig |
| Transfert (DTO) | `web.dto` | VehiculeDTO, ReservationRequestDTO/ResponseDTO, VehiculeMapper |
| Service (métier) | `service` | IReservationService/Impl, ITarificationService + strategies |
| Persistance | `repository` | IVehiculeRepository, IClientRepository, IReservationRepository |
| Domaine | `domain` | Vehicule, Client, Reservation, CategorieVehicule, StatutVehicule |
| Transverse | `aspect` / `scheduler` | LoggingAspect, PerformanceAspect, ContratEcheanceScheduler |

## Stack technique

| Catégorie | Outils / Bibliothèques |
|---|---|
| Langage / Build | Java 17+, Maven |
| Framework | Spring Boot, Spring Data JPA, Spring MVC, Spring AOP, Spring Scheduler |
| Base de données | MySQL ou PostgreSQL en développement, H2 en mémoire pour les tests |
| Productivité | Lombok, SLF4J/Logback, MapStruct (mapping DTO, optionnel) |
| Documentation API | springdoc-openapi (Swagger UI) |
| Qualité | SonarLint (IDE), SonarQube (optionnel) |
| Outillage | Git/GitHub, Postman, IntelliJ IDEA |

## Avancement du projet

- [x] **Atelier 1 (Séance 2)** — Génération du projet Spring Boot / Maven, création des premières entités JPA avec Lombok
- [ ] **Atelier 2 (Séance 3)** — Mise en place des associations entre entités (`@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany`) et justification des stratégies de cascade et de fetch
- [ ] **Séance 4** — Application des patrons de conception GoF (Strategy, Factory, Observer, Singleton)
- [ ] **Séances 5 & 6** — Mise en place des couches Repository et Service
- [ ] **Séances 7 & 9** — API REST (DTO, Bean Validation, SpringDoc/OpenAPI, refactoring Clean Code)
- [ ] **Séance 8** — Opérations d'affectation/désaffectation, cohérence des associations bidirectionnelles, `@Transactional`
- [ ] **Séances 10 & 11** — Requêtes dérivées, JPQL, pagination, Spring Scheduler
- [ ] **Séance 12** — Instrumentation avec Spring AOP (logging, performance, contrôle de rôle)
- [ ] **Séance 13** — Évaluation de la qualité (SonarQube) et analyse de la dette technique

## Lancer le projet

```bash
git clone https://github.com/asma7898/autoloc.git
cd autoloc
./mvnw spring-boot:run
```

L'application démarre par défaut sur le port configuré dans `application.properties`, avec une base MySQL (`autoloc_db`, créée automatiquement si elle n'existe pas).

---
*Projet réalisé dans le cadre de l'UP ASI — ESPRIT (Honoris United Universities), année 2026-2027.*
