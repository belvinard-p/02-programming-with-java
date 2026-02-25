## 📝 Exercise 2.3 – Solving the Diamond Problem with Interfaces
**Date :**  
**Technologie / Framework :** Java SE  
**Concept clé :** Interfaces, Diamond Problem, Multiple Inheritance, Composition over Inheritance

---

### 1️⃣ Contexte fonctionnel / métier
> La banque souhaite créer des types de comptes hybrides combinant des fonctionnalités de plusieurs sources (ex: Student Account avec features Savings et Checking).  
> Comme Java n’autorise pas l’héritage multiple, on utilise des interfaces pour résoudre le problème du “diamond problem”.

---

### 2️⃣ Objectifs techniques
- Comprendre le diamond problem en héritage
- Résoudre les conflits d’héritage multiple avec les interfaces
- Implémenter des classes avec plusieurs interfaces
- Concevoir des combinaisons de fonctionnalités flexibles

---

### 3️⃣ Analyse / Planification
- Interfaces principales : `SavingsCapable`, `CheckingCapable`, `RewardCapable`
- Classes hybrides : `StudentAccount`, `RewardCheckingAccount`, `HighYieldSavingsAccount`, `PremiumHybridAccount`
- Méthodes à implémenter pour chaque interface (ex: `earnInterest()`, `allowsOverdraft()`, `earnRewards()`)
- Contraintes : toutes les classes doivent implémenter toutes les méthodes des interfaces respectives

---

### 4️⃣ Implémentation / Étapes
1. Identifier le problème du diamond problem : `BankAccount` → `SavingsFeature` & `CheckingFeature` → `StudentAccount`
2. Créer les interfaces pour chaque capability
3. Créer les classes hybrides et implémenter toutes les méthodes des interfaces
4. Tester chaque classe dans `DiamondProblemTest` pour vérifier la flexibilité et les règles métiers

> Difficulté rencontrée : S’assurer que chaque classe implémente **toutes** les méthodes requises par ses interfaces.

---

### 5️⃣ Problèmes rencontrés et solutions
| Problème | Cause | Solution |
|----------|-------|---------|
| Conflit de méthodes si héritage multiple | Java n’autorise pas multiple inheritance | Utilisation d’interfaces pour séparer les fonctionnalités |
| Gestion de fonctionnalités combinées | Complexité de composition | Créer une interface `FeatureCombinable` pour composition dynamique |

---

### 6️⃣ Tests effectués
- `StudentAccount`: vérification de l’authentification et des fonctionnalités combinées Savings+Checking
- `RewardCheckingAccount`: cashback sur transactions
- `HighYieldSavingsAccount`: intérêt et récompenses
- `PremiumHybridAccount`: tous les features combinés
- Vérification des méthodes implémentées pour chaque interface

---

### 7️⃣ Leçons apprises
- Compréhension pratique du diamond problem
- Interfaces comme solution à l’héritage multiple
- Importance de la composition sur l’héritage pour la flexibilité
- Meilleure organisation des fonctionnalités métier dans des comptes hybrides

---

### 8️⃣ Améliorations possibles
- Ajouter `FeatureCombinable` pour permettre d’ajouter ou retirer dynamiquement des fonctionnalités
- Implémenter des tests unitaires plus détaillés pour chaque méthode
- Ajouter logs et validations pour chaque action bancaire

---

### 9️⃣ Résumé personnel
> Les interfaces permettent de combiner des comportements sans les limites de l’héritage multiple, offrant flexibilité et maintenabilité dans les systèmes complexes de comptes bancaires hybrides.

---

### 🔹 Section hebdomadaire (facultative)
- Concepts maîtrisés : Interfaces multiples, Diamond Problem, Composition over Inheritance
- Difficultés majeures : Implémentation de toutes les méthodes pour chaque classe hybride
- Victoire : Création de classes flexibles combinant plusieurs fonctionnalités
- Ce que je dois améliorer : Tests unitaires et conception plus modulaire

---