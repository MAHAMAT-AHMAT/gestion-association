# Implémentation de la Partie Web pour CasaScolarisation

## Introduction

Ce document explique les modifications apportées au projet CasaScolarisation pour ajouter la partie web (API REST) qui était manquante. Il fournit également des recommandations pour compléter l'implémentation.

## Modifications Apportées

### 1. Contrôleurs REST

Les contrôleurs REST suivants ont été ajoutés pour exposer les services existants via des API REST :

- **AnneeScolaireController** : Expose toutes les fonctionnalités du `AnneeScolaireService` via des endpoints REST.
- **BourseController** : Expose toutes les fonctionnalités du `BourseService` via des endpoints REST.
- **CommunicationController** : Expose toutes les fonctionnalités du `CommunicationService` via des endpoints REST.
- **DepenseController** : Expose toutes les fonctionnalités du `DepenseService` via des endpoints REST.
- **DocumentController** : Expose toutes les fonctionnalités du `DocumentService` via des endpoints REST.
- **DonController** : Expose toutes les fonctionnalités du `DonService` via des endpoints REST.
- **DonateurController** : Expose toutes les fonctionnalités du `DonateurService` via des endpoints REST.
- **EleveController** : Expose toutes les fonctionnalités du `EleveService` via des endpoints REST.
- **FactureController** : Expose toutes les fonctionnalités du `FactureService` via des endpoints REST.
- **FournisseurController** : Expose toutes les fonctionnalités du `FournisseurService` via des endpoints REST.
- **NiveauScolaireController** : Expose toutes les fonctionnalités du `NiveauScolaireService` via des endpoints REST.
- **ParentController** : Expose toutes les fonctionnalités du `ParentService` via des endpoints REST.
- **TransactionController** : Expose toutes les fonctionnalités du `TransactionService` via des endpoints REST.
- **TypeFournisseurController** : Expose toutes les fonctionnalités du `TypeFournisseurService` via des endpoints REST.
- **UtilisateurController** : Expose toutes les fonctionnalités du `UtilisateurService` via des endpoints REST.

### 2. Gestion Globale des Exceptions

Un gestionnaire global d'exceptions a été ajouté pour améliorer la gestion des erreurs dans la couche web :

- **GlobalExceptionHandler** : Intercepte les exceptions et les transforme en réponses HTTP appropriées avec des messages d'erreur structurés.

## Endpoints API Disponibles

### Bourses

- `GET /api/bourses` : Récupérer toutes les bourses
- `GET /api/bourses/{id}` : Récupérer une bourse par son ID
- `POST /api/bourses` : Créer une nouvelle bourse
- `PUT /api/bourses/{id}` : Mettre à jour une bourse existante
- `DELETE /api/bourses/{id}` : Supprimer une bourse
- `GET /api/bourses/eleve/{eleveId}` : Récupérer les bourses d'un élève
- `GET /api/bourses/annee/{anneeId}` : Récupérer les bourses d'une année scolaire
- `GET /api/bourses/statut/{statut}` : Récupérer les bourses par statut
- `GET /api/bourses/frequence/{frequence}` : Récupérer les bourses par fréquence

### Années Scolaires

- `GET /api/annees-scolaires` : Récupérer toutes les années scolaires
- `GET /api/annees-scolaires/{id}` : Récupérer une année scolaire par son ID
- `POST /api/annees-scolaires` : Créer une nouvelle année scolaire
- `PUT /api/annees-scolaires/{id}` : Mettre à jour une année scolaire existante
- `DELETE /api/annees-scolaires/{id}` : Supprimer une année scolaire
- `GET /api/annees-scolaires/actives` : Récupérer les années scolaires actives
- `GET /api/annees-scolaires/inactives` : Récupérer les années scolaires inactives
- `PUT /api/annees-scolaires/{id}/activer` : Activer une année scolaire

### Communications

- `GET /api/communications` : Récupérer toutes les communications
- `GET /api/communications/{id}` : Récupérer une communication par son ID
- `POST /api/communications` : Créer une nouvelle communication
- `PUT /api/communications/{id}` : Mettre à jour une communication existante
- `DELETE /api/communications/{id}` : Supprimer une communication

### Dépenses

- `GET /api/depenses` : Récupérer toutes les dépenses
- `GET /api/depenses/{id}` : Récupérer une dépense par son ID
- `POST /api/depenses` : Créer une nouvelle dépense
- `PUT /api/depenses/{id}` : Mettre à jour une dépense existante
- `DELETE /api/depenses/{id}` : Supprimer une dépense

### Documents

- `GET /api/documents` : Récupérer tous les documents
- `GET /api/documents/{id}` : Récupérer un document par son ID
- `POST /api/documents` : Créer un nouveau document
- `PUT /api/documents/{id}` : Mettre à jour un document existant
- `DELETE /api/documents/{id}` : Supprimer un document

### Dons

- `GET /api/dons` : Récupérer tous les dons
- `GET /api/dons/{id}` : Récupérer un don par son ID
- `POST /api/dons` : Créer un nouveau don
- `PUT /api/dons/{id}` : Mettre à jour un don existant
- `DELETE /api/dons/{id}` : Supprimer un don

### Donateurs

- `GET /api/donateurs` : Récupérer tous les donateurs
- `GET /api/donateurs/{id}` : Récupérer un donateur par son ID
- `POST /api/donateurs` : Créer un nouveau donateur
- `PUT /api/donateurs/{id}` : Mettre à jour un donateur existant
- `DELETE /api/donateurs/{id}` : Supprimer un donateur

### Élèves

- `GET /api/eleves` : Récupérer tous les élèves
- `GET /api/eleves/{id}` : Récupérer un élève par son ID
- `POST /api/eleves` : Créer un nouvel élève
- `PUT /api/eleves/{id}` : Mettre à jour un élève existant
- `DELETE /api/eleves/{id}` : Supprimer un élève

### Factures

- `GET /api/factures` : Récupérer toutes les factures
- `GET /api/factures/{id}` : Récupérer une facture par son ID
- `POST /api/factures` : Créer une nouvelle facture
- `PUT /api/factures/{id}` : Mettre à jour une facture existante
- `DELETE /api/factures/{id}` : Supprimer une facture

### Fournisseurs

- `GET /api/fournisseurs` : Récupérer tous les fournisseurs
- `GET /api/fournisseurs/{id}` : Récupérer un fournisseur par son ID
- `POST /api/fournisseurs` : Créer un nouveau fournisseur
- `PUT /api/fournisseurs/{id}` : Mettre à jour un fournisseur existant
- `DELETE /api/fournisseurs/{id}` : Supprimer un fournisseur

### Niveaux Scolaires

- `GET /api/niveaux-scolaires` : Récupérer tous les niveaux scolaires
- `GET /api/niveaux-scolaires/{id}` : Récupérer un niveau scolaire par son ID
- `POST /api/niveaux-scolaires` : Créer un nouveau niveau scolaire
- `PUT /api/niveaux-scolaires/{id}` : Mettre à jour un niveau scolaire existant
- `DELETE /api/niveaux-scolaires/{id}` : Supprimer un niveau scolaire

### Parents

- `GET /api/parents` : Récupérer tous les parents
- `GET /api/parents/{id}` : Récupérer un parent par son ID
- `POST /api/parents` : Créer un nouveau parent
- `PUT /api/parents/{id}` : Mettre à jour un parent existant
- `DELETE /api/parents/{id}` : Supprimer un parent

### Transactions

- `GET /api/transactions` : Récupérer toutes les transactions
- `GET /api/transactions/{id}` : Récupérer une transaction par son ID
- `POST /api/transactions` : Créer une nouvelle transaction
- `PUT /api/transactions/{id}` : Mettre à jour une transaction existante
- `DELETE /api/transactions/{id}` : Supprimer une transaction

### Types de Fournisseurs

- `GET /api/types-fournisseurs` : Récupérer tous les types de fournisseurs
- `GET /api/types-fournisseurs/{id}` : Récupérer un type de fournisseur par son ID
- `POST /api/types-fournisseurs` : Créer un nouveau type de fournisseur
- `PUT /api/types-fournisseurs/{id}` : Mettre à jour un type de fournisseur existant
- `DELETE /api/types-fournisseurs/{id}` : Supprimer un type de fournisseur

### Utilisateurs

- `GET /api/utilisateurs` : Récupérer tous les utilisateurs
- `GET /api/utilisateurs/{id}` : Récupérer un utilisateur par son ID
- `POST /api/utilisateurs` : Créer un nouvel utilisateur
- `PUT /api/utilisateurs/{id}` : Mettre à jour un utilisateur existant
- `DELETE /api/utilisateurs/{id}` : Supprimer un utilisateur

## À Faire

Pour compléter l'implémentation de la partie web, les tâches suivantes sont recommandées :

1. **Ajouter la validation des données** :
   - Utiliser les annotations de validation (`@Valid`, `@NotNull`, etc.)
   - Créer des DTOs (Data Transfer Objects) pour séparer les modèles d'API des entités

2. **Améliorer la sécurité** :
   - Configurer Spring Security pour l'authentification et l'autorisation
   - Implémenter JWT ou OAuth2 pour l'authentification

3. **Documentation de l'API** :
   - Configurer Swagger/OpenAPI pour documenter automatiquement l'API
   - Le projet inclut déjà la dépendance springdoc-openapi-starter-webmvc-ui

4. **Tests d'intégration** :
   - Écrire des tests pour les contrôleurs REST
   - Tester les scénarios d'erreur et les cas limites

5. **Interface utilisateur (Frontend)** :
   - Développer une interface utilisateur (Angular, React, Vue.js, etc.)
   - Ou utiliser Thymeleaf pour des vues côté serveur (la dépendance est déjà incluse)

## Guide pour les Développeurs Frontend (React avec Vite)

Ce guide est destiné aux développeurs frontend qui souhaitent créer une interface utilisateur pour CasaScolarisation en utilisant React avec Vite.

### Configuration du Projet React avec Vite

1. **Créer un nouveau projet React avec Vite** :
   ```bash
   npm create vite@latest frontend -- --template react
   cd frontend
   npm install
   ```

2. **Installer les dépendances nécessaires** :
   ```bash
   npm install axios react-router-dom
   ```

### Configuration des Appels API

Pour communiquer avec le backend, vous pouvez créer un service API centralisé en utilisant Axios :

```javascript
// src/services/api.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
```

### Exemples d'Appels API

Voici comment appeler les différentes routes API depuis votre application React :

#### Exemple 1: Récupérer tous les élèves (GET)

```javascript
// src/services/eleveService.js
import api from './api';

export const getAllEleves = async () => {
  try {
    const response = await api.get('/eleves');
    return response.data;
  } catch (error) {
    console.error('Erreur lors de la récupération des élèves:', error);
    throw error;
  }
};
```

Utilisation dans un composant React :

```javascript
// src/components/ElevesList.jsx
import { useState, useEffect } from 'react';
import { getAllEleves } from '../services/eleveService';

function ElevesList() {
  const [eleves, setEleves] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchEleves = async () => {
      try {
        const data = await getAllEleves();
        setEleves(data);
        setLoading(false);
      } catch (err) {
        setError('Erreur lors du chargement des élèves');
        setLoading(false);
      }
    };

    fetchEleves();
  }, []);

  if (loading) return <div>Chargement...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h2>Liste des Élèves</h2>
      <ul>
        {eleves.map((eleve) => (
          <li key={eleve.id}>{eleve.nom} {eleve.prenom}</li>
        ))}
      </ul>
    </div>
  );
}

export default ElevesList;
```

#### Exemple 2: Créer un nouvel élève (POST)

```javascript
// src/services/eleveService.js
export const createEleve = async (eleveData) => {
  try {
    const response = await api.post('/eleves', eleveData);
    return response.data;
  } catch (error) {
    console.error('Erreur lors de la création de l\'élève:', error);
    throw error;
  }
};
```

Utilisation dans un formulaire React :

```javascript
// src/components/EleveForm.jsx
import { useState } from 'react';
import { createEleve } from '../services/eleveService';

function EleveForm() {
  const [formData, setFormData] = useState({
    nom: '',
    prenom: '',
    dateNaissance: '',
    // Ajoutez d'autres champs selon votre modèle d'élève
  });
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createEleve(formData);
      setMessage('Élève créé avec succès!');
      // Réinitialiser le formulaire
      setFormData({
        nom: '',
        prenom: '',
        dateNaissance: '',
      });
    } catch (error) {
      setMessage('Erreur lors de la création de l\'élève');
    }
  };

  return (
    <div>
      <h2>Ajouter un Élève</h2>
      {message && <div className="message">{message}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nom:</label>
          <input
            type="text"
            name="nom"
            value={formData.nom}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Prénom:</label>
          <input
            type="text"
            name="prenom"
            value={formData.prenom}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Date de Naissance:</label>
          <input
            type="date"
            name="dateNaissance"
            value={formData.dateNaissance}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Ajouter</button>
      </form>
    </div>
  );
}

export default EleveForm;
```

#### Exemple 3: Mettre à jour un élève (PUT)

```javascript
// src/services/eleveService.js
export const updateEleve = async (id, eleveData) => {
  try {
    const response = await api.put(`/eleves/${id}`, eleveData);
    return response.data;
  } catch (error) {
    console.error('Erreur lors de la mise à jour de l\'élève:', error);
    throw error;
  }
};
```

#### Exemple 4: Supprimer un élève (DELETE)

```javascript
// src/services/eleveService.js
export const deleteEleve = async (id) => {
  try {
    await api.delete(`/eleves/${id}`);
    return true;
  } catch (error) {
    console.error('Erreur lors de la suppression de l\'élève:', error);
    throw error;
  }
};
```

### Gestion des Erreurs

Pour une meilleure expérience utilisateur, il est recommandé de gérer les erreurs de manière appropriée :

```javascript
// Intercepteur pour gérer les erreurs globalement
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      // La requête a été faite et le serveur a répondu avec un code d'état
      // qui n'est pas dans la plage 2xx
      console.error('Erreur de réponse:', error.response.data);
      console.error('Statut:', error.response.status);
    } else if (error.request) {
      // La requête a été faite mais aucune réponse n'a été reçue
      console.error('Erreur de requête:', error.request);
    } else {
      // Une erreur s'est produite lors de la configuration de la requête
      console.error('Erreur:', error.message);
    }
    return Promise.reject(error);
  }
);
```

### Bonnes Pratiques

1. **Organisation du Code** : Créez des services séparés pour chaque entité (élèves, bourses, etc.)
2. **État Global** : Utilisez Context API ou Redux pour gérer l'état global de l'application
3. **Chargement et Erreurs** : Affichez toujours des indicateurs de chargement et des messages d'erreur
4. **Validation** : Validez les données côté client avant de les envoyer au serveur
5. **Sécurité** : Préparez-vous à implémenter l'authentification lorsqu'elle sera ajoutée au backend

## Conclusion

Les services existants sont bien structurés et fournissent une base solide pour l'application. L'ajout de la couche web (contrôleurs REST) permet maintenant d'exposer ces services via des API REST, ce qui est essentiel pour une application web moderne.

Tous les contrôleurs REST ont été implémentés pour exposer les services existants via des API REST. Chaque entité du système dispose désormais d'endpoints pour effectuer les opérations CRUD (Create, Read, Update, Delete). En suivant les recommandations ci-dessus pour compléter les fonctionnalités manquantes (validation, sécurité, documentation, tests, interface utilisateur), l'application peut devenir une application web complète et fonctionnelle.
