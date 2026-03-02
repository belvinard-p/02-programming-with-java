## 📝 Exercise No – 5
**Date :** 2024  
**Technologie / Framework :** Java SE  
**Concept clé :** File I/O, Clean Code, Testing

---

### 1️⃣ Contexte fonctionnel / métier
Le système doit permettre de conserver durablement les données des clients et leurs transactions.
Les informations doivent être enregistrées dans des fichiers afin qu'elles soient disponibles après l'arrêt du programme.
Le système doit pouvoir relire ces fichiers pour reconstruire les objets en mémoire.
Chaque transaction doit être ajoutée à un journal sans supprimer l'historique existant.
Enfin, un mécanisme de sauvegarde doit permettre de copier et restaurer les données en cas d'erreur.

---

### 2️⃣ Objectifs techniques
- Créer des classes BankAccount et FileIOTest avec approche senior
- Appliquer les principes de Clean Code (constants, logging, DRY)
- Implémenter des tests structurés avec validation
- Utiliser Java Logger au lieu de System.out.println
- Respecter les standards SonarQube (pas de duplication, pas de magic values)

---

### 3️⃣ Analyse / Planification
- Créer BankAccount avec encapsulation (final fields, getters)
- Créer FileIOTest avec méthodes de test séparées
- Définir des constantes pour éviter la duplication de code
- Utiliser des helper methods pour la réutilisabilité

---

### 4️⃣ Implémentation / Étapes
1. Création de BankAccount avec attributs final (accountId, ownerName, balance)
2. Implémentation de toString() et displayAccountInfo() avec Logger
3. Création de FileIOTest avec constantes TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE
4. Implémentation de testCreateSingleAccount() avec validation
5. Implémentation de testCreateMultipleAccounts() avec array d'accounts
6. Création de createTestAccount() helper method pour réutilisabilité
7. Correction des warnings SonarQube (duplication de literals, constants)

> Difficulté rencontrée : Warnings SonarQube sur duplication de literals et paramètres inutilisés

---

### 5️⃣ Problèmes rencontrés et solutions
| Problème | Cause | Solution |
|----------|------|-------|
| Getters sur une ligne non standard | Formatage compact | Reformater chaque getter sur plusieurs lignes |
| Duplication literal "Ahmad Hassan" | Valeur répétée 3 fois | Créer constante TEST_OWNER_NAME |
| Méthode validateAccount() inutilisée | Appelée une seule fois avec mêmes valeurs | Supprimer et inliner la validation |
| Code commenté dans BankAccount | Ancien code logger.log | Supprimer le code commenté |

---

### 6️⃣ Tests effectués
- testCreateSingleAccount() : Création et validation d'un compte unique ✅
- testCreateMultipleAccounts() : Création de 5 comptes différents ✅
- Validation des données (accountId, ownerName, balance) ✅
- Affichage avec Logger au lieu de System.out.println ✅

---

### 7️⃣ Leçons apprises (Compétences Software Engineer)

#### 🎯 Clean Code Principles
- **Constants over Magic Values**: Définir des constantes pour toutes les valeurs répétées
- **DRY (Don't Repeat Yourself)**: Utiliser helper methods pour éviter la duplication
- **Meaningful Names**: Noms de variables/méthodes explicites (TEST_ACCOUNT_ID vs "001")
- **Single Responsibility**: Chaque méthode a un seul objectif clair

#### 🔧 Code Quality
- **SonarQube Compliance**: Respecter les règles de qualité de code
- **Immutability**: Utiliser `final` pour les champs non modifiables
- **Encapsulation**: Attributs private avec getters publics
- **No Dead Code**: Supprimer code commenté et méthodes inutilisées

#### 📊 Professional Logging
- **Logger over System.out**: Utiliser java.util.logging.Logger
- **Log Levels**: INFO pour succès, WARNING pour échecs
- **Parameterized Logging**: logger.log(Level.INFO, "{0}", value)
- **Method References**: logger.log(Level.INFO, this::toString)

#### 🧪 Testing Best Practices
- **Test Structure**: Arrange-Act-Assert pattern
- **Test Isolation**: Chaque test est indépendant
- **Test Data Management**: Constants et helper methods pour données de test
- **Validation**: Vérifier les résultats attendus vs réels

#### 🏗️ Design Patterns
- **Factory Method Pattern**: createTestAccount() comme factory
- **Builder Pattern Concept**: Méthodes helper pour construction d'objets
- **Test Data Builder**: Approche scalable pour créer données de test

#### 💼 Professional Development Skills
- **Code Review Mindset**: Anticiper les warnings SonarQube
- **Refactoring**: Améliorer le code existant sans changer le comportement
- **Scalability Thinking**: Code qui fonctionne pour 1 ou 100 comptes
- **Maintainability**: Code facile à lire et modifier par d'autres développeurs

---

### 8️⃣ Améliorations possibles
- Ajouter JUnit pour tests unitaires professionnels
- Implémenter assertions avec assertEquals() au lieu de if/else
- Créer une classe TestDataBuilder dédiée
- Ajouter tests négatifs (valeurs null, négatives)
- Implémenter test coverage reporting
- Ajouter validation dans le constructeur BankAccount

---

### 9️⃣ Résumé personnel
> J'ai appris à écrire du code "senior level" en appliquant les principes de Clean Code. La différence principale : éviter les "magic values", utiliser des constants, des helper methods, et Logger au lieu de System.out.println. J'ai compris que la qualité du code ne se mesure pas seulement au fait qu'il fonctionne, mais aussi à sa lisibilité, maintenabilité et respect des standards (SonarQube). Les compétences clés acquises : constants, DRY principle, encapsulation, professional logging, et test structure. C'est la base pour devenir un software engineer professionnel.

---

### 🔹 Section hebdomadaire (facultative)
- Prochaine étape : Implémenter la persistence des comptes dans des fichiers (File I/O)
- Objectif : Sauvegarder et charger les BankAccount depuis des fichiers texte
- Compétences à développer : FileWriter, FileReader, BufferedWriter, BufferedReader

---
