package testView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockImageViewTest {
  private MockImageView mockView;

  @Before
  public void setUp() {
    mockView = new MockImageView();
  }

  @Test
  public void testPrintStatements() {
    String message = "Hello, World!";
    mockView.printStatements(message);
    assertEquals("Hello, World!\n", mockView.getLog());
  }

  @Test
  public void testMultiplePrintStatements() {
    mockView.printStatements("First message.");
    mockView.printStatements("Second message.");
    assertEquals("First message.\nSecond message.\n", mockView.getLog());
  }

}
