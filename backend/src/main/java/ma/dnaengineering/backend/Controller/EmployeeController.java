@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/summary")
    public List<Employee> getEmployeeListAndAverageSalary() throws IOException, CsvException {
        String filePath = "data/employees.csv";
        return employeeService.getEmployeeListAndAverageSalary(filePath);
    }
}
