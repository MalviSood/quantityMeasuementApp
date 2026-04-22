import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class quantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue() {
        quantityMeasurementApp.Feet feet1 = new quantityMeasurementApp.Feet(1.0);
        quantityMeasurementApp.Feet feet2 = new quantityMeasurementApp.Feet(1.0);

        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        quantityMeasurementApp.Feet feet1 = new quantityMeasurementApp.Feet(1.0);
        quantityMeasurementApp.Feet feet2 = new quantityMeasurementApp.Feet(2.0);

        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        quantityMeasurementApp.Feet feet = new quantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        quantityMeasurementApp.Feet feet = new quantityMeasurementApp.Feet(1.0);
        quantityMeasurementApp.Inches inches = new quantityMeasurementApp.Inches(1.0);

        assertFalse(feet.equals(inches));
    }

    @Test
    public void testFeetEquality_SameReference() {
        quantityMeasurementApp.Feet feet = new quantityMeasurementApp.Feet(1.0);

        assertTrue(feet.equals(feet));
    }

    @Test
    public void testInchesEquality_SameValue() {
        quantityMeasurementApp.Inches inches1 = new quantityMeasurementApp.Inches(1.0);
        quantityMeasurementApp.Inches inches2 = new quantityMeasurementApp.Inches(1.0);

        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        quantityMeasurementApp.Inches inches1 = new quantityMeasurementApp.Inches(1.0);
        quantityMeasurementApp.Inches inches2 = new quantityMeasurementApp.Inches(2.0);

        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        quantityMeasurementApp.Inches inches = new quantityMeasurementApp.Inches(1.0);

        assertFalse(inches.equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        quantityMeasurementApp.Inches inches = new quantityMeasurementApp.Inches(1.0);
        quantityMeasurementApp.Feet feet = new quantityMeasurementApp.Feet(1.0);

        assertFalse(inches.equals(feet));
    }

    @Test
    public void testInchesEquality_SameReference() {
        quantityMeasurementApp.Inches inches = new quantityMeasurementApp.Inches(1.0);

        assertTrue(inches.equals(inches));
    }
}