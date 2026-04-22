import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class quantityMeasurementAppTest {

    // Helper method for parsing user-style input
    private quantityMeasurementApp.QuantityLength parseQuantity(String input) {
        try {
            String[] parts = input.trim().split("\\s+");
            double value = Double.parseDouble(parts[0]);
            quantityMeasurementApp.LengthUnit unit =
                    quantityMeasurementApp.LengthUnit.fromString(parts[1]);

            return new quantityMeasurementApp.QuantityLength(value, unit);
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(2.0, quantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToInches_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToInches_DifferentConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(10.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_InchesToInches_SameValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchesToInches_DifferentValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(10.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_NullComparison() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q1));
    }

    @Test
    public void testEquality_DifferentObjectType() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals("1.0 feet"));
    }

    @Test
    public void testEquality_NonNumericInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("abc feet");
        quantityMeasurementApp.QuantityLength q2 = parseQuantity("1.0 feet");

        assertNull(q1);
        assertNotNull(q2);
    }

    @Test
    public void testEquality_InvalidUnitInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("1.0 meter");

        assertNull(q1);
    }

    @Test
    public void testEquality_ParseFeetInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("1.0 feet");

        assertNotNull(q1);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, q1.getUnit());
        assertEquals(1.0, q1.getValue());
    }

    @Test
    public void testEquality_ParseInchesInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("12.0 inches");

        assertNotNull(q1);
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, q1.getUnit());
        assertEquals(12.0, q1.getValue());
    }

    @Test
    public void testLengthUnit_FromString_FeetVariants() {
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, quantityMeasurementApp.LengthUnit.fromString("feet"));
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, quantityMeasurementApp.LengthUnit.fromString("foot"));
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, quantityMeasurementApp.LengthUnit.fromString("ft"));
    }

    @Test
    public void testLengthUnit_FromString_InchesVariants() {
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, quantityMeasurementApp.LengthUnit.fromString("inches"));
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, quantityMeasurementApp.LengthUnit.fromString("inch"));
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, quantityMeasurementApp.LengthUnit.fromString("in"));
    }

    @Test
    public void testLengthUnit_FromString_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            quantityMeasurementApp.LengthUnit.fromString(null);
        });
    }

    @Test
    public void testLengthUnit_FromString_UnsupportedUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            quantityMeasurementApp.LengthUnit.fromString("meter");
        });
    }

    @Test
    public void testHashCode_SameLogicalValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testToString_Output() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertEquals("Quantity(1.0, \"feet\")", q1.toString());
    }
}