import org.example.Calculator;
import org.example.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.001);
    }

    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10.0, 0.0);
        });
        assertEquals("Помилка: Ділення на нуль неможливе!", exception.getMessage());
    }

    @Test
    public void testSquareRootNegativeInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            calculator.squareRoot(-9.0);
        });
        assertTrue(exception.getMessage().contains("Неможливо обчислити квадратний корінь"));
    }

    @Test
    public void testSquareRootValid() throws InvalidInputException {
        assertEquals(4.0, calculator.squareRoot(16.0), 0.001);
    }
}