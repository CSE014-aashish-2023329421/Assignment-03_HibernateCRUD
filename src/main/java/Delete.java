import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Delete {
    public static void main(String[] args) {
        // Hibernate configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Build SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        // User input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Fetch employee from DB
        Employee employee = session.get(Employee.class, id);
        if (employee != null) {
            session.delete(employee);
            transaction.commit();
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
            transaction.rollback();
        }

        // Close resources
        scanner.close();
        session.close();
        factory.close();
    }
}
