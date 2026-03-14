package filehandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackupManager {
    private static final Logger LOGGER = Logger.getLogger(BackupManager.class.getName());

    public void createBackup(String sourceFile, String backupFile) throws IOException {
        try {
            Files.copy(Paths.get(sourceFile), Paths.get(backupFile), StandardCopyOption.REPLACE_EXISTING);
            LOGGER.log(Level.INFO, "Backup created: {0} -> {1}", new Object[]{sourceFile, backupFile});
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create backup from {0}", sourceFile);
            throw new IOException("Backup creation failed: " + sourceFile, e);
        }
    }

    public void restoreFromBackup(String backupFile, String targetFile) throws IOException {
        try {
            Files.copy(Paths.get(backupFile), Paths.get(targetFile), StandardCopyOption.REPLACE_EXISTING);
            LOGGER.log(Level.INFO, "Restored from backup: {0} -> {1}", new Object[]{backupFile, targetFile});
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to restore from backup {0}", backupFile);
            throw new IOException("Restore failed: " + backupFile, e);
        }
    }

    public boolean verifyBackupIntegrity(String sourceFile, String backupFile) throws IOException {
        try {
            String sourceContent = readFileContent(sourceFile);
            String backupContent = readFileContent(backupFile);
            boolean isValid = sourceContent.equals(backupContent);
            
            if (isValid) {
                LOGGER.log(Level.INFO, "Backup integrity verified: {0}", backupFile);
            } else {
                LOGGER.log(Level.WARNING, "Backup integrity check failed: {0}", backupFile);
            }
            
            return isValid;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error verifying backup integrity");
            throw new IOException("Integrity verification failed", e);
        }
    }

    private String readFileContent(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
