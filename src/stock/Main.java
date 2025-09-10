package stock;

/** Entry point for the Stocks application. Demonstrates usage of the Stock class. */
public class Main {
  /**
   * The main entry point for the application. Creates a Stock object for Apple, updates its price,
   * and displays information.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    Stock apple = new Stock("AAPL", "Apple Computer", 192.50);

    apple.setCurrentPrice(2000.00);
    double change = apple.getChangePercent();

    System.out.println(apple.toString());
  }
}
