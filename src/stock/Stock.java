package stock;

/**
 * Represents a stock with a symbol, name, cost basis, and current price. Provides methods to access
 * and modify stock data and calculate gain/loss.
 */
public class Stock {
  // Instance variables for stock symbol, name, cost basis, and current price
  private String symbol;
  private String name;
  private double costBasis;
  private double currentPrice;

  /**
   * Constructs a new Stock with the specified symbol, name, and cost basis. The current price is
   * initially set equal to the cost basis.
   *
   * @param symbol the stock ticker symbol
   * @param name the company name
   * @param costBasis the initial purchase price per share
   */
  public Stock(String symbol, String name, double costBasis) {
    this.symbol = symbol;
    this.name = name;
    this.costBasis = costBasis;
    this.currentPrice = costBasis; // Initialize current price to cost basis
  }

  /**
   * Returns the stock ticker symbol.
   *
   * @return the stock symbol
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Returns the company name.
   *
   * @return the company name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the cost basis (purchase price) of the stock.
   *
   * @return the cost basis per share
   */
  public double getCostBasis() {
    return costBasis;
  }

  /**
   * Returns the current market price of the stock.
   *
   * @return the current price per share
   */
  public double getCurrentPrice() {
    return currentPrice;
  }

  /**
   * Updates the cost basis (purchase price) of the stock.
   *
   * @param costBasis the new cost basis value
   */
  public void setCostBasis(double costBasis) {
    this.costBasis = costBasis;
  }

  /**
   * Updates the current market price of the stock.
   *
   * @param currentPrice the new current price value
   */
  public void setCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }

  /**
   * Calculates the percentage change from cost basis to current price. Returns 0.0 if cost basis is
   * zero to avoid division by zero.
   *
   * @return the decimal representation of the percentage change (e.g., 0.20 for 20%)
   */
  public double getChangePercent() {
    if (costBasis == 0) {
      return 0.0; // Avoid division by zero
    }s
    return (currentPrice - costBasis) / costBasis;
  }

  /**
   * Returns a formatted string representation of the stock. Includes the name, current price, and
   * gain/loss percentage.
   *
   * @return a string containing stock information with formatting
   */
  public String toString() {
    double percentChange = getChangePercent() * 100;
    return String.format(
        "%s[ Current Price: $ %.2f\n] Gain/Loss: %.2f%%",
        name,
        currentPrice,
        percentChange
    );
  }
}
