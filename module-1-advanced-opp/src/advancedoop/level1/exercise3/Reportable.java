package advancedoop.level1.exercise3;

/**
 * Interface for generating reports
 */
public interface Reportable {
    /**
     * Generate a summary report
     * @return String containing the summary report
     */
    String generateReport();
    
    /**
     * Export report data
     * @param format The format to export (CSV, JSON, XML)
     * @return Exported data as String
     */
    String exportData(String format);
}
