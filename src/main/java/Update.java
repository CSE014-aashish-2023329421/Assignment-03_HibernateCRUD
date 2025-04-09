import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Update {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to update details: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new Name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new Salary: ");
        float newSalary = scanner.nextFloat();

        Employee employee = session.get(Employee.class, id);
        if (employee != null) {
            employee.setName(newName);
            employee.setSalary(newSalary);
            session.update(employee);
            transaction.commit();
            System.out.println("Employee details updated successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
            transaction.rollback();
        }

        scanner.close();
        session.close();
        factory.close();
    }
}
