import java.time.LocalDate;

/**
 * Promotion that applies a discount if the sale meets a minimum product quantity.
 */
public class BulkPurchaseDiscount extends Promotion {
    private int minQuantity;
    private double percentage;

    public BulkPurchaseDiscount(String id, String name, LocalDate startDate, LocalDate endDate, int minQuantity, double percentage) {
        super(id, name, startDate, endDate);
        this.minQuantity = minQuantity;
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Sale sale) {
        if (sale == null || sale.getProducts() == null) return 0.0;
        
        int totalProducts = sale.getProducts().size();
        if (totalProducts >= minQuantity) {
            return sale.getTotal() * (percentage / 100.0);
        }
        return 0.0;
    }

    public int getMinQuantity() { return minQuantity; }
    public void setMinQuantity(int minQuantity) { this.minQuantity = minQuantity; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
}