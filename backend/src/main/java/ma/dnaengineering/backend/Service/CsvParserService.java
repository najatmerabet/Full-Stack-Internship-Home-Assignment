import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class CsvParserService {
    public List<Employee> processCsv() throws IOException, CsvException {
        ClassPathResource resource = new ClassPathResource("data/employees.csv");

        try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()))) {
            List<String[]> csvData = reader.readAll();
            return processCsvData(csvData);
        }
    }
    private List<Employee> processCsvData(List<String[]> csvData) {
        Map<String, List<Double>> jobTitleToSalaries = new HashMap<>();
        List<Employee> employees = new ArrayList<>();

        for (String[] row : csvData) {
            String id = row[0];
            String name = row[1];
            double salary = Double.parseDouble(row[2]);
            String jobTitle = row[3];
            // Create Employee object
            Employee employee = new Employee(id, name, salary, jobTitle);
            employees.add(employee);
            // Update job title to salary mapping
            jobTitleToSalaries.computeIfAbsent(jobTitle, k -> new ArrayList<>()).add(salary);
        }
        // Calculate average salary for each job title
        for (Employee employee : employees) {
            String jobTitle = employee.getJobTitle();
            List<Double> salaries = jobTitleToSalaries.get(jobTitle);
            double averageSalary = calculateAverage(salaries);
            employee.setAverageSalary(averageSalary);
        }
        return employees;
    }
    private double calculateAverage(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }
}