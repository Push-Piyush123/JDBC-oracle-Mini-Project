import java.sql.*;
import java.util.Scanner;

public class ProductDAO {

    Connection con;
    Scanner sc = new Scanner(System.in);

    // Constructor → DB connection
    public ProductDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 6. Add Product
    public void addProduct() {
        try {
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            System.out.print("Enter Stock Quantity: ");
            int stock = sc.nextInt();
            sc.nextLine(); // 🔥 important

            String query = "INSERT INTO products (product_name, category, unit_price, stock_quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, stock);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Product added successfully!");
            else
                System.out.println("Failed to add product.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 7. View All Products
    public void viewAllProducts() {
        try {
            String query = "SELECT * FROM products";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Product List ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("product_id") + " | " +
                    rs.getString("product_name") + " | " +
                    rs.getString("category") + " | " +
                    rs.getDouble("unit_price") + " | " +
                    rs.getInt("stock_quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 8. Update Product Stock
    public void updateProductStock() {
        try {
            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Stock Quantity: ");
            int stock = sc.nextInt();
            sc.nextLine(); // 🔥 important

            String query = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, stock);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Stock updated successfully!");
            else
                System.out.println("Product not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 9. Delete Product
    public void deleteProduct() {
        try {
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine(); // 🔥 important

            String query = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Product deleted successfully!");
            else
                System.out.println("Product not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}