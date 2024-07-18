import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Purchase {
    String purchaserName;
    String dateOfPurchase;
    String productName;
    int quantity;
    double pricePerUnit;

    public Purchase(String purchaserName, String dateOfPurchase, String productName, int quantity, double pricePerUnit) {
        this.purchaserName = purchaserName;
        this.dateOfPurchase = dateOfPurchase;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }
}

public class Main {

    private static final String CSV_FILE_NAME = "purchase__data.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Purchase> purchases = new ArrayList<>();

        System.out.println("Welcome to the LEGEND store's Purchase detail forum");

        while (true) {
            System.out.println("1. Add Purchase");
            System.out.println("2. Display All Purchases");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPurchase(scanner, purchases);
                    break;
                case 2:
                    displayAllPurchases();
                    break;
                case 3:
                    System.out.println("Exiting program. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addPurchase(Scanner scanner, ArrayList<Purchase> purchases) {
        scanner.nextLine(); // Consume the newline character left by previous nextInt()

        System.out.print("Enter purchaser name: ");
        String purchaserName = scanner.nextLine();
        System.out.print("Enter date of purchase: ");
        String dateOfPurchase = scanner.nextLine();
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price per unit: ");
        double pricePerUnit = scanner.nextDouble();

        Purchase purchase = new Purchase(purchaserName, dateOfPurchase, productName, quantity, pricePerUnit);
        purchases.add(purchase);
        saveToFile(purchases);
        System.out.println("Purchase added successfully!");
    }

    private static void displayAllPurchases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading data from file: " + e.getMessage());
        }
    }

   /* private static void saveToFile(ArrayList<Purchase> purchases) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_NAME, true))) {
            // Check if the file is empty, then write column headers
            if (writer.readLine() == null) {
                writer.write("Purchaser Name,Date of Purchase,Product Name,Quantity,Price Per Unit,Total Price\n");
            }

            // Append data
            for (Purchase purchase : purchases) {
                double totalPrice = purchase.quantity * purchase.pricePerUnit;
                writer.write(String.format("%s,%s,%s,%d,%.2f,%.2f%n",
                        purchase.purchaserName,
                        purchase.dateOfPurchase,
                        purchase.productName,
                        purchase.quantity,
                        purchase.pricePerUnit,
                        totalPrice));
            }
            System.out.println("Data appended to file: " + CSV_FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error appending data to file: " + e.getMessage());
        }
    }*/
   private static void saveToFile(ArrayList<Purchase> purchases) {
       try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME));
            BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_NAME, true))) {

           // Check if the file is empty, then write column headers
           if (reader.readLine() == null) {
               writer.write("Purchaser Name,Date of Purchase,Product Name,Quantity,Price Per Unit,Total Price\n");
           }

           // Append data
           for (Purchase purchase : purchases) {
               double totalPrice = purchase.quantity * purchase.pricePerUnit;
               writer.write(String.format("%s,%s,%s,%d,%.2f,%.2f%n",
                       purchase.purchaserName,
                       purchase.dateOfPurchase,
                       purchase.productName,
                       purchase.quantity,
                       purchase.pricePerUnit,
                       totalPrice));
           }
           System.out.println("Data appended to file: " + CSV_FILE_NAME);
       } catch (IOException e) {
           System.out.println("Error appending data to file: " + e.getMessage());
       }
   }

}
