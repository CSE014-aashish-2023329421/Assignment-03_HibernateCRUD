import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query; // Import Query
import java.util.List; // Import List
import java.util.Scanner;

public class DisplayData {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();

        Employee employee = session.get(Employee.class, empId);
        if (employee != null) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Employee Salary: " + employee.getSalary());
        } else {
            System.out.println("Employee not found!");
        }

        // Print message before displaying all data
        System.out.println("\nDisplaying all employees from the database:");

        // Correct HQL Query
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> list = query.list();
        for (Employee e : list) {
            System.out.println("ID: " + e.getId() + ", Name: " + e.getName() + ", Salary: " + e.getSalary());
        }

        scanner.close();
        session.close();
        factory.close();
    }
}
