import java.sql.*;
import java.util.Scanner;

public class CustomerDAO {

    Connection con;
    Scanner sc = new Scanner(System.in);

    // Constructor → establish connection
    public CustomerDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. Add Customer
    public void addCustomer() {
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            String query = "INSERT INTO customers (name, email, phone, address) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Customer added successfully!");
            else
                System.out.println("Failed to add customer.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. View All Customers
    public void viewAllCustomers() {
        try {
            String query = "SELECT * FROM customers";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Customer List ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("customer_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("phone") + " | " +
                    rs.getString("address")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Search Customer
    public void searchCustomer() {
        try {
            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            String query = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Found:");
                System.out.println(
                    rs.getInt("customer_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("phone") + " | " +
                    rs.getString("address")
                );
            } else {
                System.out.println("Customer not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Update Customer
    public void updateCustomer() {
        try {
            System.out.print("Enter Customer ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Email: ");
            String email = sc.nextLine();

            String query = "UPDATE customers SET email = ? WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Customer updated successfully!");
            else
                System.out.println("Customer not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Delete Customer
    public void deleteCustomer() {
        try {
            System.out.print("Enter Customer ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String query = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Customer deleted successfully!");
            else
                System.out.println("Customer not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}