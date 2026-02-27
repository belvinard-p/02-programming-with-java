# Exercise No. 5 - Exception Handling in Banking System
**Date:** 2026-02-27  
**Technologie / Framework:** Java SE  
**Concept clé:** Exception Handling, Custom Exceptions, Defensive Programming

---

## 1. Contexte fonctionnel / métier
Ce projet simule un système bancaire robuste qui gère différents types d'exceptions lors des opérations bancaires. Le système permet de :
- Effectuer des retraits et des transferts d'argent
- Gérer des comptes bancaires avec validation des opérations
- Traiter des transactions par lot avec gestion d'erreurs
- Gérer des erreurs réseau avec mécanisme de retry

Le système doit être capable de gérer les situations suivantes :
- Fonds insuffisants pour une transaction
- Compte fermé
- Numéro de compte invalide
- Dépassement de limite de transaction (max 10,000$)
- Erreurs réseau lors de transferts

---

## 2. Objectifs techniques
- Créer une hiérarchie d'exceptions personnalisées héritant de `BankingException`
- Implémenter des exceptions vérifiées (checked exceptions) pour les erreurs métier
- Utiliser des blocs try-catch-finally pour gérer les exceptions de manière appropriée
- Implémenter un système de retry pour les erreurs réseau
- Créer des méthodes "safe" qui retournent des booléens au lieu de lancer des exceptions
- Traiter des opérations par lot avec collecte des erreurs
- Utiliser le logging pour tracer les opérations et erreurs

---

## 3. Analyse / Planification
- **Architecture d'exceptions:** Créer une classe de base `BankingException` et des exceptions spécifiques pour chaque type d'erreur
- **Classes principales:**
  - `RobustBankAccount`: Classe de compte avec validation stricte
  - `BankingService`: Service avec méthodes sécurisées et traitement par lot
  - `ExceptionHandlingTest`: Tests complets de tous les scénarios
- **Hiérarchie d'exceptions:**
  ```
  Exception
    └── BankingException (base)
        ├── InsufficientFundsException
        ├── AccountClosedException
        ├── InvalidAccountException
        ├── TransactionLimitExceededException
        └── NetworkException
  ```
- **Stratégies de gestion:**
  - Exceptions métier: logged avec niveau WARNING
  - Erreurs critiques (compte fermé): logged avec niveau SEVERE
  - Erreurs réseau: retry jusqu'à 3 fois avant échec

---

## 4. Implémentation / Étapes
1. **Création de la hiérarchie d'exceptions:**
   - `BankingException` avec attribut `errorCode`
   - Exceptions spécifiques avec attributs contextuels (montant, date de fermeture, etc.)

2. **Implémentation de `RobustBankAccount`:**
   - Méthodes `withdraw()`, `transfer()`, `deposit()` avec validation
   - Vérification de compte fermé avant chaque opération
   - Lancement d'exceptions appropriées selon le contexte

3. **Implémentation de `BankingService`:**
   - `safeWithdraw()`: catch des exceptions et retour de boolean
   - `safeTransfer()`: retry automatique pour erreurs réseau (max 3 tentatives)
   - `processBatchWithErrorHandling()`: traitement de tableau avec collecte d'erreurs

4. **Tests complets:**
   - 10 scénarios de test couvrant tous les types d'exceptions
   - Validation des codes d'erreur et messages
   - Test des opérations réussies et échouées

> Difficulté rencontrée : Gestion correcte de la hiérarchie d'exceptions - il y avait initialement une erreur où `InsufficientFundsException` héritait de `AccountClosedException.BankingException` au lieu de `BankingException` directement.

---

## 5. Problèmes rencontrés et solutions
| Problème | Cause | Solution |
|----------|-------|----------|
| `InsufficientFundsException` hérite de classe interne | Erreur de référence dans la déclaration de classe | Corriger pour hériter de `BankingException` au lieu de `AccountClosedException.BankingException` |
| Test 8 échoue - withdrawal de 50,000$ devrait réussir | Logique de test incorrecte - confond limite de transaction (transfer) avec withdrawal | Le withdrawal n'a pas de limite, seul le transfer est limité à 10,000$ |
| Pattern matching avec instanceof nécessite variable | Syntaxe Java moderne | Utiliser pattern variable: `e instanceof TransactionLimitExceededException transactionLimitExceededException` |

---

## 6. Tests effectués
- **Test 1:** InsufficientFundsException - Retrait avec fonds insuffisants ✅
- **Test 2:** AccountClosedException - Opération sur compte fermé ✅
- **Test 3:** InvalidAccountException - Transfert vers compte vide ✅
- **Test 4:** TransactionLimitExceededException - Transfert > 10,000$ ✅
- **Test 5:** Opérations réussies - Dépôt, retrait, transfert normaux ✅
- **Test 6:** ProcessTransaction - Méthode générique de traitement ✅
- **Test 7:** NetworkException - Exception avec advice de retry ✅
- **Test 8:** SafeWithdraw - Méthode sécurisée sans exception ⚠️ (1 assertion échoue)
- **Test 9:** SafeTransfer - Transfert sécurisé avec retry ✅
- **Test 10:** ProcessBatch - Traitement par lot avec rapport d'erreurs ✅

**Résultat:** 9/10 tests passent complètement, 1 test a une assertion incorrecte

---

## 7. Leçons apprises
- **Hiérarchie d'exceptions:** Une bonne organisation des exceptions facilite leur gestion et permet du code plus maintenable
- **Exceptions vérifiées vs non-vérifiées:** Les exceptions métier (fonds insuffisants, compte fermé) sont vérifiées car l'appelant doit les gérer explicitement
- **Pattern de retry:** Essentiel pour les opérations réseau - permet de gérer les erreurs transitoires automatiquement
- **Logging approprié:** Différents niveaux de log (INFO, WARNING, SEVERE) selon la gravité de l'erreur
- **Méthodes "safe":** Retourner boolean au lieu de lancer exception simplifie le code appelant dans certains cas
- **Traitement par lot:** Collecter les erreurs au lieu d'arrêter au premier échec permet de traiter le maximum d'opérations
- **Enrichissement des exceptions:** Ajouter des attributs contextuels (montant, dates, codes d'erreur) rend le debugging plus facile
- **Pattern matching moderne:** Java permet d'utiliser instanceof avec assignation de variable directement

---

## 8. Améliorations possibles
- Corriger le bug dans `InsufficientFundsException` (mauvaise classe parente)
- Ajouter un système de transaction avec rollback en cas d'erreur
- Implémenter un pattern Circuit Breaker pour les erreurs réseau répétées
- Ajouter des métriques pour tracker le taux d'erreur par type
- Créer des exceptions plus spécifiques (ex: `NegativeAmountException`, `AccountNotActiveException`)
- Implémenter un système d'audit trail pour toutes les opérations (réussies et échouées)
- Ajouter des validations avec Bean Validation (JSR-303)
- Créer un ErrorHandler centralisé pour uniformiser la gestion des exceptions
- Ajouter des tests unitaires avec JUnit et Mockito
- Implémenter un système de notification pour les erreurs critiques

---

## 9. Résumé personnel
> Cet exercice m'a permis de comprendre l'importance d'une gestion d'exceptions bien structurée dans un système réel. La création d'une hiérarchie d'exceptions personnalisées permet de gérer chaque cas d'erreur de manière spécifique tout en gardant un code propre et maintenable. Le pattern de retry pour les erreurs réseau et le traitement par lot avec collecte d'erreurs sont des techniques essentielles en production. J'ai aussi appris l'importance de bien logger les erreurs avec des niveaux appropriés pour faciliter le debugging et la maintenance.

---

## 10. Section hebdomadaire (facultative)
- **Points forts:** Excellente couverture des cas d'erreur, bonne organisation du code
- **À améliorer:** Corriger le bug d'héritage, améliorer la cohérence des tests
- **Temps passé:** ~4 heures (conception, implémentation, tests)
- **Concepts maîtrisés:** Exception handling, custom exceptions, retry pattern, batch processing
- **Prochaines étapes:** Approfondir les patterns de resilience (Circuit Breaker, Bulkhead)

---
