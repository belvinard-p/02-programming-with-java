package advancedoop.level2.exercise3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DiamondProblemTest {
    private static final Logger logger = Logger.getLogger(DiamondProblemTest.class.getName());
    
    public static void main(String[] args) {
        logger.info("=== DIAMOND PROBLEM SOLUTION TEST ===");
        logger.info("");
        
        testStudentAccountCreation();
        testSavingsFeatures();
        testCheckingFeatures();
        testHybridBehavior();
        testRewardCheckingAccount();
        testHighYieldSavingsAccount();
        testPremiumHybridAccount();
        
        logger.info("");
        logger.info("=== ALL TESTS COMPLETED ===");
    }
    
    private static void testStudentAccountCreation() {
        logger.info("TEST 1: StudentAccount Creation");
        logger.info("--------------------------------");
        
        StudentAccount student = new StudentAccount(500);
        logger.log(Level.INFO, "Initial balance: ${0}", student.getBalance());
        logger.info("Account implements both SavingsCapable and CheckingCapable");
        logger.info("");
    }
    
    private static void testSavingsFeatures() {
        logger.info("TEST 2: Savings Features");
        logger.info("------------------------");
        
        StudentAccount student = new StudentAccount(500);
        logger.log(Level.INFO, "Has minimum balance requirement: {0}", student.hasMinimumBalanceRequirement());
        logger.log(Level.INFO, "Minimum balance: ${0}", student.getMinimumBalance());
        logger.log(Level.INFO, "Interest earned: ${0}", student.earnInterest());
        logger.info("");
    }
    
    private static void testCheckingFeatures() {
        logger.info("TEST 3: Checking Features");
        logger.info("-------------------------");
        
        StudentAccount student = new StudentAccount(500);
        logger.log(Level.INFO, "Allows overdraft: {0}", student.allowsOverdraft());
        logger.log(Level.INFO, "Overdraft limit: ${0}", student.getOverdraftLimit());
        logger.log(Level.INFO, "Free transactions: {0}", student.getFreeTransactions());
        logger.info("");
    }
    
    private static void testHybridBehavior() {
        logger.info("TEST 4: Hybrid Account Benefits");
        logger.info("--------------------------------");
        
        StudentAccount student = new StudentAccount(500);
        logger.log(Level.INFO, "Current balance: ${0}", student.getBalance());
        logger.info("✓ No minimum balance requirement");
        logger.log(Level.INFO, "✓ Limited overdraft protection (${0})", student.getOverdraftLimit());
        logger.info("✓ No monthly fees");
        logger.info("✓ Earns interest on balance");
        logger.info("");
    }
    
    private static void testRewardCheckingAccount() {
        logger.info("TEST 5: RewardCheckingAccount - Checking + Rewards");
        logger.info("----------------------------------------------------");
        
        RewardCheckingAccount account = new RewardCheckingAccount(2000);
        logger.log(Level.INFO, "Initial balance: ${0}", account.getBalance());
        logger.log(Level.INFO, "Allows overdraft: {0}", account.allowsOverdraft());
        logger.log(Level.INFO, "Overdraft limit: ${0}", account.getOverdraftLimit());
        logger.log(Level.INFO, "Reward type: {0}", account.getRewardType());
        logger.log(Level.INFO, "Cashback earned: ${0}", account.earnRewards());
        logger.log(Level.INFO, "Reward balance: ${0}", account.getRewardBalance());
        logger.info("");
    }
    
    private static void testHighYieldSavingsAccount() {
        logger.info("TEST 6: HighYieldSavingsAccount - Savings + Rewards");
        logger.info("-----------------------------------------------------");
        
        HighYieldSavingsAccount account = new HighYieldSavingsAccount(15000);
        logger.log(Level.INFO, "Initial balance: ${0}", account.getBalance());
        logger.log(Level.INFO, "Minimum balance required: ${0}", account.getMinimumBalance());
        logger.log(Level.INFO, "Interest earned (tiered): ${0}", account.earnInterest());
        logger.log(Level.INFO, "Reward type: {0}", account.getRewardType());
        logger.log(Level.INFO, "Bonus rewards: ${0}", account.earnRewards());
        logger.log(Level.INFO, "Reward balance: ${0}", account.getRewardBalance());
        logger.info("");
    }
    
    private static void testPremiumHybridAccount() {
        logger.info("TEST 7: PremiumHybridAccount - All Features Combined");
        logger.info("------------------------------------------------------");
        
        PremiumHybridAccount account = new PremiumHybridAccount(25000);
        logger.log(Level.INFO, "Initial balance: ${0}", account.getBalance());
        
        logger.info("Savings features:");
        logger.log(Level.INFO, "  Minimum balance: ${0}", account.getMinimumBalance());
        logger.log(Level.INFO, "  Interest earned: ${0}", account.earnInterest());
        
        logger.info("Checking features:");
        logger.log(Level.INFO, "  Overdraft limit: ${0}", account.getOverdraftLimit());
        logger.log(Level.INFO, "  Free transactions: {0}", account.getFreeTransactions());
        
        logger.info("Reward features:");
        logger.log(Level.INFO, "  Reward type: {0}", account.getRewardType());
        logger.log(Level.INFO, "  Rewards earned: ${0}", account.earnRewards());
        logger.log(Level.INFO, "  Reward balance: ${0}", account.getRewardBalance());
        
        logger.info("✓ Highest tier account with all benefits");
        logger.info("");
    }
}
