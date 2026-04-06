import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerDAO cust  = new CustomerDAO();
        ProductDAO  prod  = new ProductDAO();
        OrderDAO    order = new OrderDAO();

        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║   SALES MANAGEMENT SYSTEM            ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  CUSTOMERS                           ║");
            System.out.println("║  1. Add Customer                     ║");
            System.out.println("║  2. View All Customers               ║");
            System.out.println("║  3. Search Customer by ID            ║");
            System.out.println("║  4. Update Customer                  ║");
            System.out.println("║  5. Delete Customer                  ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  PRODUCTS                            ║");
            System.out.println("║  6. Add Product                      ║");
            System.out.println("║  7. View All Products                ║");
            System.out.println("║  8. Update Product Stock             ║");
            System.out.println("║  9. Delete Product                   ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  ORDERS                              ║");
            System.out.println("║  10. Place New Order                 ║");
            System.out.println("║  11. View All Orders                 ║");
            System.out.println("║  12. View Order Details              ║");
            System.out.println("║  13. Delete Order                    ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  14. Sales Report (by Customer)      ║");
            System.out.println("║   0. Exit                            ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt(); sc.nextLine();
            switch (ch) {
                case  1 -> cust.addCustomer();
                case  2 -> cust.viewAllCustomers();
                case  3 -> cust.searchCustomer();
                case  4 -> cust.updateCustomer();
                case  5 -> cust.deleteCustomer();
                case  6 -> prod.addProduct();
                case  7 -> prod.viewAllProducts();
                case  8 -> prod.updateProductStock();
                case  9 -> prod.deleteProduct();
                case 10 -> order.placeOrder();
                case 11 -> order.viewAllOrders();
                case 12 -> order.viewOrderDetails();
                case 13 -> order.deleteOrder();
                case 14 -> order.salesReport();
                case  0 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}