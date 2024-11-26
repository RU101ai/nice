import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        // Task #2: Early termination if no arguments are passed
        if (args.length == 0) {
            System.out.println(Constants.ERROR_NO_ARGUMENTS);
            return;
        }

        String command = args[0];

        switch (command.charAt(0)) {
            case 'l':
                loadAndPrintEmployees();
                break;
            case 's':
                printRandomEmployee();
                break;
            case '+':
                addEmployee(command.substring(1));
                break;
            case '?':
                searchEmployee(command.substring(1));
                break;
            case 'c':
                countWordsAndCharacters();
                break;
            case 'u':
                updateEmployee(command.substring(1));
                break;
            case 'd':
                deleteEmployee(command.substring(1));
                break;
            default:
                System.out.println(Constants.ERROR_INVALID_ARGUMENT);
        }
    }

    // Task #4: Refactor file read and write logic into methods
    private static String readFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            return reader.readLine();
        }
    }

    private static void writeToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH))) {
            writer.write(content);
        }
    }

    // Task #1: Update Code Style
    private static void loadAndPrintEmployees() {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            if (employeeData != null) {
                String[] employees = employeeData.split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_READING_FILE + e.getMessage());
        }
        System.out.println(Constants.DATA_LOADED);
    }

    private static void printRandomEmployee() {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            if (employeeData != null) {
                String[] employees = employeeData.split(",");
                Random random = new Random();
                System.out.println(employees[random.nextInt(employees.length)].trim());
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_READING_FILE + e.getMessage());
        }
        System.out.println(Constants.DATA_LOADED);
    }

    private static void addEmployee(String employeeName) {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            String updatedData = employeeData + ", " + employeeName;
            writeToFile(updatedData);
        } catch (IOException e) {
            System.err.println(Constants.ERROR_WRITING_FILE + e.getMessage());
        }
        System.out.println(Constants.DATA_LOADED);
    }

    private static void searchEmployee(String employeeName) {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            if (employeeData != null && Arrays.asList(employeeData.split(",")).contains(employeeName)) {
                System.out.println(Constants.EMPLOYEE_FOUND);
            } else {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_READING_FILE + e.getMessage());
        }
        System.out.println(Constants.DATA_LOADED);
    }

    private static void countWordsAndCharacters() {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            if (employeeData != null) {
                String[] employees = employeeData.split(",");
                int wordCount = employees.length;
                int characterCount = employeeData.replaceAll("\\s", "").length();
                System.out.printf("%d word(s) found, %d character(s) in total.%n", wordCount, characterCount);
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_READING_FILE + e.getMessage());
        }
        System.out.println(Constants.DATA_LOADED);
    }

    private static void updateEmployee(String employeeName) {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            if (employeeData != null) {
                String[] employees = employeeData.split(",");
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(employeeName)) {
                        employees[i] = "Updated";
                    }
                }
                writeToFile(String.join(",", employees));
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_WRITING_FILE + e.getMessage());
        }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String employeeName) {
        System.out.println("Loading data...");
        try {
            String employeeData = readFromFile();
            if (employeeData != null) {
                List<String> employees = new ArrayList<>(Arrays.asList(employeeData.split(",")));
                employees.removeIf(employee -> employee.trim().equals(employeeName));
                writeToFile(String.join(",", employees));
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_WRITING_FILE + e.getMessage());
        }
        System.out.println("Data Deleted.");
    }
}
