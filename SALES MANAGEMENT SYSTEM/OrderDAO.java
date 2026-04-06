import java.sql.*;
import java.util.Scanner;

public class OrderDAO {

    Connection con;
    Scanner sc = new Scanner(System.in);

    public OrderDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 10. Place Order (FIXED FOR ORACLE 🔥)
    public void placeOrder() {
        try {
            System.out.print("Enter Customer ID: ");
            int custId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Shipping Address: ");
            String address = sc.nextLine();

            // 🔥 Step 1: Get order_id using SEQUENCE
            String seqQuery = "SELECT orders_seq.NEXTVAL FROM dual";
            Statement st = con.createStatement();
            ResultSet seqRs = st.executeQuery(seqQuery);

            int orderId = 0;
            if (seqRs.next()) {
                orderId = seqRs.getInt(1);
            }

            // 🔥 Step 2: Insert into orders
            String orderQuery = "INSERT INTO orders (order_id, customer_id, shipping_address) VALUES (?, ?, ?)";
            PreparedStatement psOrder = con.prepareStatement(orderQuery);

            psOrder.setInt(1, orderId);
            psOrder.setInt(2, custId);
            psOrder.setString(3, address);
            psOrder.executeUpdate();

            double total = 0;

            // 🔁 Add multiple products
            while (true) {
                System.out.print("Enter Product ID: ");
                int pid = sc.nextInt();

                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();

                // 🔍 Get product price
                String priceQuery = "SELECT unit_price FROM products WHERE product_id=?";
                PreparedStatement psPrice = con.prepareStatement(priceQuery);
                psPrice.setInt(1, pid);

                ResultSet rsPrice = psPrice.executeQuery();
                double price = 0;

                if (rsPrice.next()) {
                    price = rsPrice.getDouble("unit_price");
                } else {
                    System.out.println("Product not found!");
                    continue;
                }

                double subtotal = price * qty;
                total += subtotal;

                // 🔥 Insert into order_items
                String itemQuery = "INSERT INTO order_items (order_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psItem = con.prepareStatement(itemQuery);

                psItem.setInt(1, orderId);
                psItem.setInt(2, pid);
                psItem.setInt(3, qty);
                psItem.setDouble(4, price);
                psItem.setDouble(5, subtotal);

                psItem.executeUpdate();

                System.out.print("Add more products? (y/n): ");
                String choice = sc.nextLine();

                if (!choice.equalsIgnoreCase("y")) break;
            }

            // 🔥 Update total amount
            String updateQuery = "UPDATE orders SET total_amount=? WHERE order_id=?";
            PreparedStatement psUpdate = con.prepareStatement(updateQuery);
            psUpdate.setDouble(1, total);
            psUpdate.setInt(2, orderId);
            psUpdate.executeUpdate();

            System.out.println("Order placed successfully! Order ID: " + orderId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 11. View All Orders
    public void viewAllOrders() {
        try {
            String query = "SELECT * FROM orders";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Orders ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("order_id") + " | " +
                    rs.getInt("customer_id") + " | " +
                    rs.getDate("order_date") + " | " +
                    rs.getDouble("total_amount")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 12. View Order Details (JOIN 🔥)
    public void viewOrderDetails() {
        try {
            System.out.print("Enter Order ID: ");
            int oid = sc.nextInt();
            sc.nextLine();

            String query =
                "SELECT o.order_id, c.name, p.product_name, oi.quantity, oi.subtotal " +
                "FROM orders o " +
                "JOIN customers c ON o.customer_id = c.customer_id " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE o.order_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, oid);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Order Details ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("order_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("product_name") + " | " +
                    rs.getInt("quantity") + " | " +
                    rs.getDouble("subtotal")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 13. Delete Order
    public void deleteOrder() {
        try {
            System.out.print("Enter Order ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String query = "DELETE FROM orders WHERE order_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Order deleted successfully!");
            else
                System.out.println("Order not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 14. Sales Report
    public void salesReport() {
        try {
            String query =
                "SELECT c.name, SUM(o.total_amount) AS total_sales " +
                "FROM customers c " +
                "JOIN orders o ON c.customer_id = o.customer_id " +
                "GROUP BY c.name";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Sales Report ---");
            while (rs.next()) {
                System.out.println(
                    rs.getString("name") + " | Total Sales: " +
                    rs.getDouble("total_sales")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}