import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // Helper method to simulate parsing logic
    private boolean checkEquality(String input) {
        try {
            String[] parts = input.split(" ");
            double value1 = Double.parseDouble(parts[0]);
            double value2 = Double.parseDouble(parts[3]);

            quantityMeasurementApp.Feet f1 = new quantityMeasurementApp.Feet(value1);
            quantityMeasurementApp.Feet f2 = new quantityMeasurementApp.Feet(value2);

            return f1.equals(f2);
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    void testEquality_SameValue() {
        assertTrue(checkEquality("1.0 ft and 1.0 ft"),
                "1.0 ft and 1.0 ft should be equal");
    }

    @Test
    void testEquality_DifferentValue() {
        assertFalse(checkEquality("1.0 ft and 2.0 ft"),
                "1.0 ft and 2.0 ft should not be equal");
    }

    @Test
    void testEquality_NullComparison() {
        quantityMeasurementApp.Feet f1 = new quantityMeasurementApp.Feet(1.0);
        assertFalse(f1.equals(null),
                "Feet object should not be equal to null");
    }

    @Test
    void testEquality_NonNumericInput() {
        assertFalse(checkEquality("abc ft and xyz ft"),
                "Non-numeric input should return false");
    }

    @Test
    void testEquality_SameReference() {
        quantityMeasurementApp.Feet f1 = new quantityMeasurementApp.Feet(1.0);
        assertTrue(f1.equals(f1),
                "Same reference should return true");
    }
}