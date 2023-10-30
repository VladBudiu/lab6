package exercitiu1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainApp {
    public static void writeFile(List<Employee> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("src/main/resources/angajati.json");
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> readFile() {
        try {
            File file = new File("src/main/resources/angajati.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.readValue(file, new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printEmployees() {
        employeeList.forEach(System.out::println);
    }

    public static void printEmployeesWithWageGreaterThan2500() {
        employeeList.stream().filter((employee -> employee.getWage() > 2500f)).forEach(System.out::println);
    }

    public static List<Employee> printDirectorsEmployedLastYear() {
        List<Employee> employees = employeeList.stream().filter(employee -> (LocalDate.now().getYear() - employee.getDateOfEmployment().getYear()) == 1)
                .filter(employee -> employee.getDateOfEmployment().getMonthValue() == 4)
                .filter(employee -> employee.getJob().contains("Director")).toList();
        return employees;
    }

    public static void printNormalEmployees() {
        employeeList.stream().filter(employee -> !employee.getJob().contains("Director"))
                .sorted(Comparator.comparing(Employee::getWage).reversed())
                .forEach(System.out::println);
    }

    public static List<String> getEmployeesNames() {
        return employeeList.stream().map(employee -> employee.getName().toUpperCase()).collect(Collectors.toList());
    }

    public static void printWagesSmallerThan3000() {
        employeeList.stream().filter((employee -> employee.getWage() < 3000f)).forEach(employee -> System.out.println(employee.getWage()));
    }

    public static void printFirstEmployeeEver() {
        Optional<Employee> employee = employeeList.stream().min(Comparator.comparing(Employee::getDateOfEmployment));
        System.out.println(employee.isPresent() ? employee.get().toString() : "Nu exista angajati!");
    }

    public static void printStatisticsAboutWages() {
        System.out.println(employeeList.stream().collect(Collectors.summarizingDouble(Employee::getWage)));
    }

    public static void printIfIonInList() {
        Optional optional = employeeList.stream().filter((employee -> employee.getName().contains("Ion"))).findAny();
        System.out.println(optional.isPresent() ? "Exista Ion" : "Nu exista Ion bruh :(");
    }

    public static void printEmployeesFromLastSummer() {
        employeeList.stream().filter(employee -> (LocalDate.now().getYear() - employee.getDateOfEmployment().getYear()) == 1)
                .filter(employee -> employee.getDateOfEmployment().getMonthValue() == 7 || employee.getDateOfEmployment().getMonthValue() == 8
                        || employee.getDateOfEmployment().getMonthValue() == 9).forEach(System.out::println);
    }

    public static void main(String[] args) {
        employeeList = readFile();
        System.out.println("1. Afisarea listei de angajati folosind referinte la metode");
        printEmployees();
        System.out.println("2. Afișarea angajaților care au salariul peste 2500 RON");
        printEmployeesWithWageGreaterThan2500();
        System.out.println();
        System.out.println("3. Crearea unei liste cu angajații din luna aprilie, a anului trecut, care au funcție de conducere");
        printDirectorsEmployedLastYear().forEach(System.out::println);
        System.out.println("4. Afisarea angajatilor care nu au functie de conducere");
        printNormalEmployees();
        System.out.println("5. Extragerea din lista de angajati a unei liste de String-uri care contine numele angajatilor scrise cu majuscule");
        getEmployeesNames().forEach(System.out::println);
        System.out.println("6. Afisarea salariilor mai mici de 3000 de RON");
        printWagesSmallerThan3000();
        System.out.println();
        System.out.println("7. Afisarea datelor primului angajat al firmei");
        printFirstEmployeeEver();
        System.out.println("8. Afisarea de statistici referitoare la salariul angajatilor");
        printStatisticsAboutWages();
        System.out.println("9. Afisarea unor mesaje care indica daca printre angajati exista cel putin un Ion");
        printIfIonInList();
        System.out.println();
        System.out.println("10. Afisarea numarului de persoane care s-au angajat in vara anului precedent");
        printEmployeesFromLastSummer();
    }

    public static List<Employee> employeeList = new ArrayList<>();
}