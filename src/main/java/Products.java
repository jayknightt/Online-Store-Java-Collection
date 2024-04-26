import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Products {
    static Map<String, Product> inventory = new HashMap<>();

    public static void main(String[] args) {
        loadInventory("src/main/java/Inventory");

        // View all products
        System.out.println("All Products:");
        for (Product product : inventory.values()) {
            System.out.println(product);
        }

        // Search by Product Name
        System.out.println("\nSearch by Product Name:");
        searchByName("Mini Projector");

        // Search by Price
        System.out.println("\nSearch by Price:");
        searchByPrice(89.95);

        // Search by Department
        System.out.println("\nSearch by Department:");
        searchByDepartment("Audio Video");
    }

    static void loadInventory(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String sku = parts[0];
                    String productName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String department = parts[3];
                    Product product = new Product(sku, productName, price, department);
                    inventory.put(sku, product);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void searchByName(String productName) {
        for (Product product : inventory.values()) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                System.out.println(product);
            }
        }
    }

    static void searchByPrice(double price) {
        for (Product product : inventory.values()) {
            if (product.getPrice() == price) {
                System.out.println(product);
            }
        }
    }

    static void searchByDepartment(String department) {
        for (Product product : inventory.values()) {
            if (product.getDepartment().equalsIgnoreCase(department)) {
                System.out.println(product);
            }
        }
    }

    static class Product {
        private String SKU;
        private String productName;
        private double price;
        private String department;

        public Product(String SKU, String productName, double price, String department) {
            this.SKU = SKU;
            this.productName = productName;
            this.price = price;
            this.department = department;
        }

        public String getSKU() {
            return SKU;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

        public String getDepartment() {
            return department;
        }

        @Override
        public String toString() {
            return "SKU: " + SKU + ", Product Name: " + productName + ", Price: $" + price + ", Department: " + department;
        }
    }
}

