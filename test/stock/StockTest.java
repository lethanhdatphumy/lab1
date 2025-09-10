package stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Stock class. Verifies constructors, getters, setters, calculations, and string
 * formatting.
 */
public class StockTest {
  /*
   * Unit tests for the Stock class.
   * Tests all methods including constructors, getters, setters,
   * calculations, and string formatting.
   */
  private Stock appleStock;
  private Stock teslaStock;
  private Stock zeroStock;

  /** Sets up test fixtures before each test method. Creates sample Stock instances for testing. */
  @BeforeEach
  public void setUp() {
    appleStock = new Stock("AAPL", "Apple Inc", 192.50);
    teslaStock = new Stock("TESL", "Tesla Inc", 200.00);
    zeroStock = new Stock("ZERO", "Zero Corporation", 0.0);
  }

  /** Tests the Stock constructor with valid parameters. */
  @Test
  public void testConstructor() {
    Stock stock = new Stock("MSFT", "Microsoft Corporation", 350.25);
    assertEquals("MSFT", stock.getSymbol());
    assertEquals("Microsoft Corporation", stock.getName());
    assertEquals(350.25, stock.getCostBasis(), 0.001);
    assertEquals(350.25, stock.getCurrentPrice(), 0.001);
  }

  /** Tests the getSymbol methods. */
  @Test
  public void testGetSymbol() {
    assertEquals("AAPL", appleStock.getSymbol());
    assertEquals("TESL", teslaStock.getSymbol());
    assertEquals("ZERO", zeroStock.getSymbol());
  }

  /** Tests the getName methods. */
  @Test
  public void testGetName() {
    assertEquals("Apple Inc", appleStock.getName());
    assertEquals("Tesla Inc", teslaStock.getName());
    assertEquals("Zero Corporation", zeroStock.getName());
  }

  /** Tests the getCostBasis method. */
  @Test
  public void testGetCostBasis() {
    assertEquals(192.50, appleStock.getCostBasis(), 0.001);
    assertEquals(200.00, teslaStock.getCostBasis(), 0.001);
    assertEquals(0.0, zeroStock.getCostBasis(), 0.001);
  }

  /** Tests the getCurrentPrice method with initial values (should equal cost basis initially). */
  @Test
  public void testGetCurrentPriceInitial() {
    assertEquals(192.50, appleStock.getCurrentPrice(), 0.001);
    assertEquals(200.00, teslaStock.getCurrentPrice(), 0.001);
    assertEquals(0.0, zeroStock.getCurrentPrice(), 0.001);
  }

  /** Tests the setCostBasis method. */
  @Test
  public void testSetCostBasis() {
    appleStock.setCostBasis(200.00);
    assertEquals(200.00, appleStock.getCostBasis(), 0.001);

    teslaStock.setCostBasis(2000.00);
    assertEquals(2000.00, teslaStock.getCostBasis(), 0.001);

    zeroStock.setCostBasis(100.0);
    assertEquals(100.0, zeroStock.getCostBasis(), 0.001);
  }

  /** Tests the setCurrentPrice method. */
  @Test
  public void testSetCurrentPrice() {
    appleStock.setCurrentPrice(300.0);
    assertEquals(300.0, appleStock.getCurrentPrice(), 0.001);

    teslaStock.setCurrentPrice(400.0);
    assertEquals(400.0, teslaStock.getCurrentPrice(), 0.001);

    zeroStock.setCurrentPrice(50.0);
    assertEquals(50.0, zeroStock.getCurrentPrice(), 0.001);

    // Test negative value (edge case)
    appleStock.setCurrentPrice(-10.50);
    assertEquals(-10.50, appleStock.getCurrentPrice(), 0.001);
  }

  /** Tests the getChangePercent method with no change (current = cost basis). */
  @Test
  public void testGetChangePercentNoChange() {
    // Initial state: current price equals cost basis
    assertEquals(0.0, appleStock.getChangePercent(), 0.001);
    assertEquals(0.0, teslaStock.getChangePercent(), 0.001);
    assertEquals(0.0, zeroStock.getChangePercent(), 0.001);
  }

  /** Tests the getChangePercent method with gains. */
  @Test
  public void testGetChangePercentGain() {
    // Apple: 192.50 -> 231.00 = 20% gain
    appleStock.setCurrentPrice(231.00);
    assertEquals(0.20, appleStock.getChangePercent(), 0.001);

    // Tesla: 200.00 -> 250.00 = 25% gain
    teslaStock.setCurrentPrice(250.00);
    assertEquals(0.25, teslaStock.getChangePercent(), 0.001);
  }

  /** Tests the getChangePercent method with losses. */
  @Test
  public void testGetChangePercentLoss() {
    // Apple: 192.50 -> 154.00 = -20% loss
    appleStock.setCurrentPrice(154.00);
    assertEquals(-0.20, appleStock.getChangePercent(), 0.001);

    // Tesla: 200.00 -> 150.00 = -25% loss
    teslaStock.setCurrentPrice(150.00);
    assertEquals(-0.25, teslaStock.getChangePercent(), 0.001);
  }

  /** Tests the getChangePercent method with zero cost basis (edge case). */
  @Test
  public void testGetChangePercentZeroCostBasis() {
    zeroStock.setCurrentPrice(100.0);
    // Should return 0.0 to avoid division by zero
    assertEquals(0.0, zeroStock.getChangePercent(), 0.001);
  }

  /** Tests the toString method with no change. */
  @Test
  public void testToStringNoChange() {
    String result = appleStock.toString();
    assertTrue(result.contains("Apple Inc"));
    assertTrue(result.contains("$ 192.50"));
    assertTrue(result.contains("0.00%"));
    assertTrue(result.contains("Current Price:"));
    assertTrue(result.contains("Gain/Loss:"));
    assertTrue(result.contains("\n")); // Two-line format
  }

  /** Tests the toString method with a gain. */
  @Test
  public void testToStringWithGain() {
    appleStock.setCurrentPrice(231.00);
    String result = appleStock.toString();

    assertTrue(result.contains("Apple Inc"));
    assertTrue(result.contains("$ 231.00"));
    assertTrue(result.contains("20.00%"));
    assertTrue(result.contains("Current Price:"));
    assertTrue(result.contains("Gain/Loss:"));
    assertTrue(result.contains("\n"));
  }

  /** Tests the toString method with a loss. */
  @Test
  public void testToStringWithLoss() {
    teslaStock.setCurrentPrice(150.00);
    String result = teslaStock.toString();

    assertTrue(result.contains("Tesla Inc"));
    assertTrue(result.contains("$ 150.00"));
    assertTrue(result.contains("-25.00%"));
    assertTrue(result.contains("Current Price:"));
    assertTrue(result.contains("Gain/Loss:"));
    assertTrue(result.contains("\n"));
  }

  /** Tests complete workflow using existing stock variables. */
  @Test
  public void testCompleteWorkflow() {
    // Test with appleStock - initial state
    assertEquals("AAPL", appleStock.getSymbol());
    assertEquals("Apple Inc", appleStock.getName());
    assertEquals(192.50, appleStock.getCostBasis(), 0.001);
    assertEquals(192.50, appleStock.getCurrentPrice(), 0.001);
    assertEquals(0.0, appleStock.getChangePercent(), 0.001);

    // Simulate price increase
    appleStock.setCurrentPrice(231.00);
    assertEquals(0.20, appleStock.getChangePercent(), 0.001); // 20% gain

    // Modify cost basis
    appleStock.setCostBasis(220.00);
    assertEquals(0.05, appleStock.getChangePercent(), 0.001); // 5% gain

    // Verify toString with teslaStock
    teslaStock.setCurrentPrice(240.00);
    String result = teslaStock.toString();
    assertTrue(result.contains("Tesla Inc"));
    assertTrue(result.contains("$ 240.00"));
  }
}
