import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExchangeAssetSystem {

    // Simple Asset model
    static class Asset {
        int id;
        String name;
        String category;
        int quantity;
        double distributionFee;

        Asset(int id, String name, String category, int quantity, double distributionFee) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.quantity = quantity;
            this.distributionFee = distributionFee;
        }

        public String toString() {
            return "ID: " + id + " | Name: " + name + " | Category: " + category
                    + " | Quantity: " + quantity
                    + String.format(" | Distribution Fee: ₱%.2f/unit", distributionFee);
        }
    }

    private List<Asset> assets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void startSystem() {
        // sample starting data (fixed duplicate IDs and typos)
        assets.add(new Asset(1, "Dell Laptop", "Laptop", 15, 250.0));
        assets.add(new Asset(2, "Asus Celeron Processor", "Laptop", 30, 300.0));
        assets.add(new Asset(3, "Motorola", "Phone", 40, 100.0));
        assets.add(new Asset(4, "Samsung Galaxy S26", "Phone", 40, 300.0));
        assets.add(new Asset(5, "Tulfone", "Phone", 40, 800.0));

        boolean running = true;
        while (running) {
            System.out.println("\n=== ASSET SYSTEM ===");
            System.out.println("1. Admin Menu");
            System.out.println("2. User Menu");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1: adminMenu(); break;
                case 2: userMenu(); break;
                case 3:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Create Asset");
            System.out.println("2. View Assets");
            System.out.println("3. Update Asset");
            System.out.println("4. Delete Asset");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1: createAsset(); break;
                case 2: viewAssets(); break;
                case 3: updateAsset(); break;
                case 4: deleteAsset(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void userMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Assets");
            System.out.println("2. Deliver Asset");
            System.out.println("3. Back");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1: viewAssets(); break;
                case 2: requestAsset(); break;
                case 3: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ---------- CRUD ----------

    private void createAsset() {
        System.out.print("Enter ID: ");
        int id = readInt();
        System.out.print("Enter Name: ");
        String name = readLine();
        System.out.print("Enter Category: ");
        String category = readLine();
        System.out.print("Enter Quantity: ");
        int qty = readInt();
        System.out.print("Enter Distribution Fee (per unit): ");
        double fee = readDouble();

        assets.add(new Asset(id, name, category, qty, fee));
        System.out.println("Asset created.");
    }

    private void viewAssets() {
        System.out.println("\n--- Asset List ---");
        if (assets.isEmpty()) {
            System.out.println("No assets found.");
            return;
        }
        for (Asset a : assets) {
            System.out.println(a);
        }
    }

    private void updateAsset() {
        viewAssets();
        System.out.print("Enter ID to update: ");
        int id = readInt();
        Asset asset = findAssetById(id);

        if (asset == null) {
            System.out.println("Asset not found.");
            return;
        }
        System.out.print("New Name (" + asset.name + "): ");
        asset.name = readLine();
        System.out.print("New Category (" + asset.category + "): ");
        asset.category = readLine();
        System.out.print("New Quantity (" + asset.quantity + "): ");
        asset.quantity = readInt();
        System.out.print("New Distribution Fee (" + asset.distributionFee + "): ");
        asset.distributionFee = readDouble();

        System.out.println("Asset updated.");
    }

    private void deleteAsset() {
        viewAssets();
        System.out.print("Enter ID to delete: ");
        int id = readInt();
        Asset asset = findAssetById(id);

        if (asset == null) {
            System.out.println("Asset not found.");
            return;
        }
        assets.remove(asset);
        System.out.println("Asset deleted.");
    }

    private void requestAsset() {
        viewAssets();
        System.out.print("Enter ID: ");
        int id = readInt();
        Asset asset = findAssetById(id);

        if (asset == null) {
            System.out.println("Asset not found.");
            return;
        }
        System.out.print("Enter quantity: ");
        int qty = readInt();

        if (qty <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        if (qty > asset.quantity) {
            System.out.println("Not enough stock available.");
            return;
        }

        System.out.print("Enter drop-off store/branch: ");
        String store = readLine();

        double totalFee = qty * asset.distributionFee;
        asset.quantity -= qty;

        System.out.println("Request approved: " + qty + " x " + asset.name);
        printReceipt(asset, qty, store, totalFee);
    }

    private void printReceipt(Asset asset, int qty, String store, double totalFee) {
        System.out.println("\n========== TRANSACTION RECEIPT ==========");
        System.out.println("Asset ID:        " + asset.id);
        System.out.println("Asset Name:      " + asset.name);
        System.out.println("Category:        " + asset.category);
        System.out.println("Quantity:        " + qty);
        System.out.println("Drop-off Store:  " + store);
        System.out.printf("Distribution Fee:  ₱%.2f (per unit:  ₱%.2f)%n", totalFee, asset.distributionFee);
        System.out.println("Remaining Stock: " + asset.quantity);
        System.out.println("==========================================\n");
    }

    // ---------- Helpers ----------

    private Asset findAssetById(int id) {
        for (Asset a : assets) {
            if (a.id == id) return a;
        }
        return null;
    }

    // Reads an int safely; keeps asking until valid
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clear leftover newline
        return value;
    }

    private String readLine() {
        return scanner.nextLine();
    }

    // Reads a double safely; keeps asking until valid
    private double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // clear leftover newline
        return value;
    }
}