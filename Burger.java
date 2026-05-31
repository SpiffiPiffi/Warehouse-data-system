public class Burger {
    // Constants for pricing
    private final double SINGLE_COST = 3.50;
    private final double DOUBLE_COST = 4.75;
    private final double CHEESE_COST = 0.50;
    private final double BACON_COST = 1.25;
    private final double MEAL_COST = 4.00;
    
    // Private instance variables
    private String type;           // "single" or "double"
    private boolean cheese;
    private boolean bacon;
    private boolean meal;
    private int quantity;
    
    // Constructor
    public Burger(String type, boolean cheese, boolean bacon, boolean meal, int quantity) {
        this.type = type;
        this.cheese = cheese;
        this.bacon = bacon;
        this.meal = meal;
        this.quantity = quantity;
    }
    
    // Calculate cost for a single burger item
    public double calculateItemCost() {
        double cost = 0;
        
        // Add burger base cost
        if (type.equalsIgnoreCase("single")) {
            cost += SINGLE_COST;
        } else if (type.equalsIgnoreCase("double")) {
            cost += DOUBLE_COST;
        }
        
        // Add toppings
        if (cheese) {
            cost += CHEESE_COST;
        }
        if (bacon) {
            cost += BACON_COST;
        }
        if (meal) {
            cost += MEAL_COST;
        }
        
        return cost;
    }
    
    // Calculate total cost (item cost × quantity)
    public double calculateTotalCost() {
        return calculateItemCost() * quantity;
    }
    
    // Return formatted string representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Add quantity and burger type
        sb.append(quantity).append(" ");
        sb.append(type.substring(0, 1).toUpperCase())
          .append(type.substring(1).toLowerCase());
        
        // Build toppings string
        StringBuilder toppings = new StringBuilder();
        if (cheese) {
            toppings.append("cheese");
        }
        if (bacon) {
            if (toppings.length() > 0) {
                toppings.append(", ");
            }
            toppings.append("bacon");
        }
        if (meal) {
            if (toppings.length() > 0) {
                toppings.append(", ");
            }
            toppings.append("meal");
        }
        
        // Add toppings or nothing
        if (toppings.length() > 0) {
            sb.append(", ").append(toppings);
        }
        
        sb.append(" at $").append(String.format("%.2f", calculateItemCost())).append(" each");
        
        return sb.toString();
    }
    
    // Getters and Setters
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isCheese() {
        return cheese;
    }
    
    public void setCheese(boolean cheese) {
        this.cheese = cheese;
    }
    
    public boolean isBacon() {
        return bacon;
    }
    
    public void setBacon(boolean bacon) {
        this.bacon = bacon;
    }
    
    public boolean isMeal() {
        return meal;
    }
    
    public void setMeal(boolean meal) {
        this.meal = meal;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
