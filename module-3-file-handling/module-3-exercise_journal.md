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
- **Accompli cette semaine** : File I/O complet (write + read) avec AccountDataWriter et AccountDataReader
- **Prochaine étape** : Implémenter BackupManager et TransactionLogger
- **Objectif** : Système complet de persistence avec backup/restore et logging
- **Compétences maîtrisées** : Try-with-resources, BufferedReader/Writer, String parsing, ArrayList, exception handling
- **Challenge résolu** : Parser CSV et reconstruire objets BankAccount/Transaction depuis fichiers

---

## 📝 Exercise No – 5 (Suite) - AccountDataReader Implementation
**Date :** 07/03/2026
**Technologie / Framework :** Java SE  
**Concept clé :** File Reading, String Parsing, ArrayList, Data Reconstruction

---

### 1️⃣ Contexte fonctionnel / métier (Suite)
Après avoir implémenté l'écriture de données, le système doit maintenant pouvoir lire les fichiers et reconstruire les objets BankAccount et Transaction en mémoire. Cette fonctionnalité est essentielle pour charger les données au démarrage de l'application.

---

### 2️⃣ Objectifs techniques (Reading)
- Implémenter AccountDataReader avec loadAccount() et loadMultipleAccounts()
- Parser les lignes de fichier avec split() et indices constants
- Utiliser ArrayList pour chargement dynamique sans compter les lignes
- Implémenter loadTransactions() pour lire l'historique des transactions
- Valider les données parsées (empty checks, format validation)
- Utiliser trim() pour robustesse face aux espaces
- Gérer NumberFormatException et DateTimeParseException

---

### 3️⃣ Analyse / Planification (Reading)
- Créer AccountDataReader avec méthodes de lecture
- Définir constantes pour indices (ACCOUNT_ID_INDEX=0, OWNER_NAME_INDEX=1, BALANCE_INDEX=2)
- Extraire parseAccount() comme méthode privée (DRY principle)
- Utiliser ArrayList pour loadMultipleAccounts() au lieu de compter les lignes
- Implémenter parseTransaction() pour parser format CSV des transactions
- Ajouter validation robuste avec trim() et empty checks

---

### 4️⃣ Implémentation / Étapes (Reading)

#### Phase 4: File Reading - AccountDataReader
16. Création de loadAccount() avec try-with-resources et BufferedReader
17. Utilisation de Files.newBufferedReader(Paths.get(filename))
18. Parsing avec split(" \\| ") pour délimiteur pipe
19. Extraction de parseAccount() comme méthode privée réutilisable
20. Ajout de trim() sur tous les champs parsés pour robustesse
21. Validation: vérifier parts.length et empty fields
22. Gestion NumberFormatException pour balance invalide

#### Phase 5: Multiple Accounts Loading
23. Implémentation de loadMultipleAccounts() avec ArrayList<BankAccount>
24. Boucle while avec readLine() pour lire toutes les lignes
25. Skip empty lines avec line.trim().isEmpty()
26. Conversion ArrayList vers array avec toArray(new BankAccount[0])
27. Logging du nombre de comptes chargés

#### Phase 6: Transaction Loading
28. Ajout constantes pour transactions (TRANSACTION_DELIMITER=";", indices 0-3)
29. Implémentation de loadTransactions() similaire à loadMultipleAccounts()
30. Création de parseTransaction() avec parsing de 4 champs
31. Parsing LocalDateTime avec DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
32. Gestion DateTimeParseException pour datetime invalide
33. Utilisation du constructeur Transaction avec 4 paramètres

#### Phase 7: Test Integration
34. Ajout de testLoadAccount() dans FileIOTest
35. Validation des données chargées vs données originales
36. Ajout de testLoadMultipleAccounts() dans main()
37. Affichage des comptes chargés avec displayAccountInfo()
38. Fix inconsistencies: LOGGER uppercase, proper test messages

> Difficultés rencontrées : 
> - Split avec pipe nécessite escape: " \\| " au lieu de " | "
> - ArrayList.toArray() nécessite new BankAccount[0] comme paramètre
> - trim() essentiel pour gérer espaces avant/après délimiteurs
> - DateTimeFormatter pattern doit matcher exactement le format CSV
> - Indices commencent à 0 pas 1 (erreur initiale corrigée)

---

### 5️⃣ Problèmes rencontrés et solutions (Reading)
| Problème | Cause | Solution |
|----------|------|-------|
| Split ne fonctionne pas avec pipe | Pipe est caractère spécial regex | Utiliser " \\\\| " pour escape |
| ArrayList.toArray() retourne Object[] | Type générique non spécifié | Passer new BankAccount[0] comme paramètre |
| Données avec espaces causent erreurs | Espaces avant/après délimiteurs | Utiliser trim() sur tous les champs |
| Transaction indices commencent à 1 | Erreur de logique | Corriger: accountId=0, type=1, amount=2, datetime=3 |
| DateTimeParseException non gérée | Exception checked non catchée | Ajouter catch block avec IOException |
| loadMultipleAccounts() pas appelée | Méthode définie mais pas dans main() | Renommer en testLoadMultipleAccounts() et ajouter |
| Inconsistent test logging | Certains tests sans message descriptif | Ajouter contexte à tous TEST_PASSED/FAILED |
| logger minuscule | Pas conforme convention Java | Renommer en LOGGER (uppercase) |

---

### 6️⃣ Tests effectués (Reading)
- testLoadAccount() : Chargement d'un compte depuis test_account.txt ✅
- testLoadMultipleAccounts() : Chargement de 15 comptes depuis multiple_account.csv ✅
- Validation accountId, ownerName, balance après chargement ✅
- Test avec fichiers créés par AccountDataWriter ✅
- Vérification que les données chargées = données sauvegardées ✅
- Test loadTransactions() avec transactions.csv ✅

---

### 7️⃣ Leçons apprises (Compétences Software Engineer) - Reading

#### 📖 File Reading Mastery
- **BufferedReader**: Lecture efficace ligne par ligne
- **Files.newBufferedReader()**: API moderne pour reading
- **readLine()**: Retourne null à la fin du fichier
- **While loop pattern**: `while ((line = reader.readLine()) != null)`
- **Skip empty lines**: Vérifier `line.trim().isEmpty()` avant parsing

#### 🔍 String Parsing Techniques
- **split() with regex**: Comprendre les caractères spéciaux (pipe, dot, etc.)
- **Escape sequences**: `\\|` pour échapper le pipe en regex
- **trim() importance**: Defensive programming contre espaces indésirables
- **Array indices**: Toujours commencer à 0, utiliser constantes nommées
- **Multi-step parsing**: `parts[2].split(" ")[0]` pour extraire balance sans unité

#### 📊 ArrayList vs Array
- **Dynamic loading**: ArrayList quand taille inconnue à l'avance
- **toArray() conversion**: `list.toArray(new Type[0])` pour conversion type-safe
- **List interface**: Utiliser `List<T>` au lieu de `ArrayList<T>` pour flexibilité
- **When to use**: ArrayList pour construction, Array pour retour de méthode

#### ✅ Data Validation
- **Length validation**: Vérifier `parts.length` avant accès aux indices
- **Empty field checks**: Valider que champs critiques ne sont pas vides
- **Format validation**: Try-catch pour NumberFormatException et DateTimeParseException
- **Contextual errors**: Messages d'erreur avec valeur invalide pour debugging

#### 🏗️ Code Organization (Senior Level)
- **Extract method**: parseAccount() et parseTransaction() pour réutilisabilité
- **Constants for indices**: ACCOUNT_ID_INDEX au lieu de magic number 0
- **Separate delimiters**: DELIMITER vs TRANSACTION_DELIMITER pour clarté
- **Consistent patterns**: Même structure pour loadAccount() et loadTransactions()
- **EXPECTED_PARTS constants**: Validation explicite du format attendu

#### 🎯 Why trim() is Essential
- **Real-world data**: Fichiers peuvent avoir espaces inconsistants
- **User input**: Données saisies manuellement ont souvent espaces
- **Format variations**: `"001 | Ahmad"` vs `"001|Ahmad"` vs `"001 |Ahmad"`
- **Defensive programming**: trim() rend le parsing robuste
- **Validation**: Après trim(), vérifier isEmpty() pour détecter champs vides

#### 🔄 ArrayList Loading Pattern
```java
List<Type> list = new ArrayList<>();
while ((line = reader.readLine()) != null) {
    if (!line.trim().isEmpty()) {
        list.add(parseMethod(line));
    }
}
return list.toArray(new Type[0]);
```
Ce pattern évite de:
- Compter les lignes d'abord (double lecture)
- Utiliser reader.reset() (pas supporté par tous les readers)
- Hardcoder la taille de l'array

---

### 8️⃣ Améliorations possibles (Reading)
- Implémenter cache pour éviter relecture fréquente
- Ajouter support pour différents formats (JSON, XML)
- Implémenter pagination pour gros fichiers
- Ajouter méthode loadAccountById(String id, String filename)
- Créer interface DataReader pour abstraction
- Implémenter lazy loading pour performance
- Ajouter support pour fichiers compressés (.gz)

---

### 9️⃣ Résumé personnel (Reading)
> J'ai maîtrisé la lecture de fichiers avec BufferedReader et le parsing de données structurées. La compétence clé : **ArrayList pour chargement dynamique** - évite de compter les lignes et rend le code plus simple. Le pattern `while ((line = reader.readLine()) != null)` est standard en Java. **trim() est essentiel** pour la robustesse - il gère les espaces avant/après les délimiteurs qui causent des bugs subtils. J'ai appris à extraire des méthodes privées (parseAccount, parseTransaction) pour appliquer le DRY principle. La validation avec length check + empty check + format check (NumberFormatException, DateTimeParseException) rend le code production-ready. Les constantes pour indices (ACCOUNT_ID_INDEX=0) éliminent les magic numbers et rendent le code self-documenting. Maintenant je peux implémenter un système complet de persistence : write + read + validation.

---
