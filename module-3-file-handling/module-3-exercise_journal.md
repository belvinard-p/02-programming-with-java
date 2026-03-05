## 📝 Exercise No – 5
**Date :** 2024  
**Technologie / Framework :** Java SE  
**Concept clé :** File I/O, Clean Code, Testing, Try-With-Resources

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
- Implémenter File I/O avec try-with-resources
- Gérer les exceptions IOException correctement
- Différencier write mode vs append mode

---

### 3️⃣ Analyse / Planification
- Créer BankAccount avec encapsulation (final fields, getters)
- Créer Transaction avec toCSV() pour format structuré
- Créer AccountDataWriter avec 3 méthodes (saveAccount, saveMultipleAccounts, appendTransaction)
- Créer FileIOTest avec méthodes de test séparées
- Définir des constantes pour éviter la duplication de code
- Utiliser des helper methods pour la réutilisabilité

---

### 4️⃣ Implémentation / Étapes

#### Phase 1: Classes de base
1. Création de BankAccount avec attributs final (accountId, ownerName, balance)
2. Implémentation de toString() et displayAccountInfo() avec Logger
3. Création de Transaction avec toCSV() pour export CSV
4. Ajout de CURRENCY constant dans BankAccount et Transaction

#### Phase 2: File I/O - AccountDataWriter
5. Création de saveAccount() avec try-with-resources
6. Utilisation de Files.newBufferedWriter() pour écriture
7. Gestion IOException avec contextual information lors du rethrow
8. Création de saveMultipleAccounts() avec boucle for
9. Implémentation de appendTransaction() avec StandardOpenOption.APPEND

#### Phase 3: Tests
10. Création de FileIOTest avec constantes (TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE, TEST_FILE, CSV_FILE, TRANSACTION_FILE)
11. Implémentation de testSaveAccountToFile() avec try-catch
12. Implémentation de testSaveMultipleAccountToFile()
13. Implémentation de testAppendTransactionToFile() avec boucle
14. Création de helper methods: createTestAccount(), createTestAccounts(), createTestTransaction(), createTestTransactions()
15. Ajout de constantes TEST_PASSED et TEST_FAILED pour éviter duplication

> Difficultés rencontrées : 
> - Try-with-resources syntax avec Files.newBufferedWriter()
> - Différence entre write mode et append mode
> - StandardOpenOption.APPEND au lieu de boolean true
> - Resource leak si BufferedWriter non fermé
> - Exception handling avec rethrow et contextual information

---

### 5️⃣ Problèmes rencontrés et solutions
| Problème | Cause | Solution |
|----------|------|-------|
| Resource leak avec BufferedWriter | Writer créé mais jamais fermé | Utiliser try-with-resources |
| Files.newBufferedWriter(path, true) ne compile pas | Méthode n'accepte pas boolean | Utiliser StandardOpenOption.APPEND |
| Exception swallowed dans catch | Catch IOException mais ne rethrow pas | Rethrow avec new IOException(message, cause) |
| Duplication de code pour créer accounts | Même array dans 2 méthodes | Créer helper method createTestAccounts() |
| appendTransaction reçoit array au lieu de single | Type mismatch Transaction[] vs Transaction | Boucler sur array et appeler pour chaque transaction |
| Logger pas final | Oubli du mot-clé final | Ajouter final à tous les loggers statiques |
| CURRENCY constant définie mais non utilisée | Hardcoded "xaf" dans toString() | Remplacer par CURRENCY constant |
| Duplication literal "❌ Test failed: {0}" | Répété 3 fois | Créer constantes TEST_PASSED et TEST_FAILED |

---

### 6️⃣ Tests effectués
- testCreateSingleAccount() : Création et validation d'un compte unique ✅
- testCreateMultipleAccounts() : Création de 7 comptes différents ✅
- testSaveAccountToFile() : Sauvegarde d'un compte dans test_account.txt ✅
- testSaveMultipleAccountToFile() : Sauvegarde de 7 comptes dans csv_account.csv ✅
- testAppendTransactionToFile() : Ajout de 4 transactions dans transactions.csv ✅
- Validation des données (accountId, ownerName, balance) ✅
- Vérification des fichiers créés dans D:\java\ ✅

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
- **Log Levels**: INFO pour succès, SEVERE pour erreurs
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

#### 📁 File I/O Mastery (NOUVEAU)
- **Try-With-Resources**: Automatic resource management pour éviter memory leaks
- **BufferedWriter**: Écriture efficace dans les fichiers
- **Files.newBufferedWriter()**: API moderne Java NIO pour file operations
- **StandardOpenOption**: CREATE, APPEND pour contrôler le mode d'écriture
- **Write vs Append**: Différence entre écraser et ajouter au fichier
- **IOException Handling**: Proper exception propagation avec contextual information
- **CSV Format**: Utiliser toCSV() pour format structuré et parsable

#### 🔐 Exception Handling Best Practices (NOUVEAU)
- **Try-With-Resources Syntax**: `try (Resource r = ...) { }` pour auto-close
- **Exception Propagation**: Déclarer `throws IOException` dans signature
- **Contextual Rethrow**: `throw new IOException("context", originalException)`
- **Resource Cleanup**: Garantir fermeture même en cas d'erreur
- **Multiple Resources**: Possibilité de déclarer plusieurs resources séparées par ;

---

### 8️⃣ Améliorations possibles
- Ajouter JUnit pour tests unitaires professionnels
- Implémenter AccountDataReader pour lire les fichiers
- Créer BackupManager pour backup/restore
- Ajouter tests négatifs (fichier inexistant, permissions)
- Implémenter test coverage reporting
- Ajouter validation dans le constructeur BankAccount
- Utiliser Path au lieu de String pour les chemins de fichiers
- Implémenter file locking pour concurrent access

---

### 9️⃣ Résumé personnel
> J'ai appris à écrire du code "senior level" en appliquant les principes de Clean Code ET en maîtrisant File I/O avec Java. La compétence clé : **try-with-resources** pour automatic resource management - c'est ESSENTIEL pour éviter les memory leaks. J'ai compris la différence entre write mode (écrase) et append mode (ajoute), et comment utiliser StandardOpenOption.APPEND. L'exception handling avec contextual information lors du rethrow est crucial pour le debugging en production. Le pattern est clair : catch IOException, log l'erreur, puis rethrow avec plus de contexte (accountId, filename). J'ai aussi appliqué DRY principle en créant des helper methods pour éviter la duplication de code. La combinaison Clean Code + File I/O + Exception Handling = compétences de base d'un software engineer professionnel.

---

### 🔹 Section hebdomadaire (facultative)
- **Accompli cette semaine** : File I/O writing (saveAccount, saveMultipleAccounts, appendTransaction)
- **Prochaine étape** : Implémenter AccountDataReader pour lire les fichiers
- **Objectif** : Charger les BankAccount depuis des fichiers texte et reconstruire les objets
- **Compétences à développer** : FileReader, BufferedReader, String parsing, data reconstruction
- **Challenge** : Parser le CSV et créer des objets BankAccount/Transaction à partir des lignes

---
