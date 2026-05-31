import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Burger> currentOrder;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        currentOrder = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        int choice;
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            choice = getIntInput("Enter your choice: ", 1, 4);
            
            switch (choice) {
                case 1:
                    addToOrder();
                    break;
                case 2:
                    showCurrentOrder();
                    break;
                case 3:
                    newOrder();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nThank you for your order!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\nMenu");
        System.out.println("1) Add to Order");
        System.out.println("2) Show Current Order");
        System.out.println("3) New Order");
        System.out.println("4) Exit");
    }
    
    private static void addToOrder() {
        System.out.println("\n--- Add to Order ---");
        
        // Select burger type with validation
        System.out.println("Select burger type:");
        System.out.println("1) Single - $3.50");
        System.out.println("2) Double - $4.75");
        int burgerChoice = getIntInput("Enter choice (1 or 2): ", 1, 2);
        
        String burgerType = (burgerChoice == 1) ? "single" : "double";
        
        // Select toppings with clear prompts
        boolean hasCheese = getYesNoInput("Add cheese? (+$0.50) (y/n): ");
        boolean hasBacon = getYesNoInput("Add bacon? (+$1.25) (y/n): ");
        boolean isMeal = getYesNoInput("Make it a meal? (+$4.00) (y/n): ");
        
        // Get quantity with validation
        int quantity = getPositiveIntInput("How many? ");
        
        // Create burger object with all parameters passed correctly
        Burger burger = new Burger(burgerType, hasCheese, hasBacon, isMeal, quantity);
        currentOrder.add(burger);
        
        // Show updated order
        showCurrentOrder();
    }
    
    private static void showCurrentOrder() {
        System.out.println("\nCurrent Order");
        
        if (currentOrder.isEmpty()) {
            System.out.println();
        } else {
            for (Burger burger : currentOrder) {
                System.out.println(burger.toString());
            }
            System.out.println();
        }
        
        System.out.println("Order Total: $" + String.format("%.2f", calculateOrderTotal()));
    }
    
    private static void newOrder() {
        currentOrder.clear();
        showCurrentOrder();
    }
    
    private static double calculateOrderTotal() {
        double total = 0;
        for (Burger burger : currentOrder) {
            total += burger.calculateTotalCost();
        }
        return total;
    }
    
    // Get integer input with range validation
    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            
            if (!scanner.hasNextLine()) {
                return min;
            }
            
            String input = scanner.nextLine().trim();
            
            // Check for empty input
            if (input.isEmpty()) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
                continue;
            }
            
            try {
                int value = Integer.parseInt(input);
                
                // Validate range
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    // Get positive integer input (for quantity)
    private static int getPositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            
            if (!scanner.hasNextLine()) {
                return 1;
            }
            
            String input = scanner.nextLine().trim();
            
            // Check for empty input
            if (input.isEmpty()) {
                System.out.println("Please enter a positive number.");
                continue;
            }
            
            try {
                int value = Integer.parseInt(input);
                
                // Validate positive number
                if (value <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                    continue;
                }
                
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive number.");
            }
        }
    }
    
    // Get yes/no input with clear validation
    private static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            
            if (!scanner.hasNextLine()) {
                return false;
            }
            
            String input = scanner.nextLine().trim().toLowerCase();
            
            // Check for empty input
            if (input.isEmpty()) {
                System.out.println("Please enter 'y' or 'n'.");
                continue;
            }
            
            // Accept full words or single letters
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Please enter 'y' or 'n'.");
            }
        }
    }
}
