import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class quantityMeasurementAppTest {

    // Helper for parsing input like "1 feet", "36 inches", "1 yard", "91.44 cm"
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
    public void testEquality_FeetToYards_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(3.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardsToInches_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(36.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimetersToInches_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(2.54, quantityMeasurementApp.LengthUnit.CENTIMETERS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimetersToFeet_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(30.48, quantityMeasurementApp.LengthUnit.CENTIMETERS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimetersToYards_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(91.44, quantityMeasurementApp.LengthUnit.CENTIMETERS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardsToCentimeters_DifferentValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(90.0, quantityMeasurementApp.LengthUnit.CENTIMETERS);

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

        assertFalse(q1.equals("1 feet"));
    }

    @Test
    public void testParse_FeetInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("1 feet");

        assertNotNull(q1);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, q1.getUnit());
        assertEquals(1.0, q1.getValue());
    }

    @Test
    public void testParse_InchesInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("12 inches");

        assertNotNull(q1);
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, q1.getUnit());
        assertEquals(12.0, q1.getValue());
    }

    @Test
    public void testParse_YardsInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("1 yard");

        assertNotNull(q1);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, q1.getUnit());
        assertEquals(1.0, q1.getValue());
    }

    @Test
    public void testParse_CentimetersInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("91.44 cm");

        assertNotNull(q1);
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS, q1.getUnit());
        assertEquals(91.44, q1.getValue());
    }

    @Test
    public void testEquality_NonNumericInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("abc feet");

        assertNull(q1);
    }

    @Test
    public void testEquality_InvalidUnitInput() {
        quantityMeasurementApp.QuantityLength q1 = parseQuantity("1 meter");

        assertNull(q1);
    }

    @Test
    public void testLengthUnit_FromString_FeetVariants() {
        assertEquals(quantityMeasurementApp.LengthUnit.FEET,
                quantityMeasurementApp.LengthUnit.fromString("feet"));
        assertEquals(quantityMeasurementApp.LengthUnit.FEET,
                quantityMeasurementApp.LengthUnit.fromString("foot"));
        assertEquals(quantityMeasurementApp.LengthUnit.FEET,
                quantityMeasurementApp.LengthUnit.fromString("ft"));
    }

    @Test
    public void testLengthUnit_FromString_InchesVariants() {
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES,
                quantityMeasurementApp.LengthUnit.fromString("inches"));
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES,
                quantityMeasurementApp.LengthUnit.fromString("inch"));
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES,
                quantityMeasurementApp.LengthUnit.fromString("in"));
    }

    @Test
    public void testLengthUnit_FromString_YardsVariants() {
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS,
                quantityMeasurementApp.LengthUnit.fromString("yards"));
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS,
                quantityMeasurementApp.LengthUnit.fromString("yard"));
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS,
                quantityMeasurementApp.LengthUnit.fromString("yd"));
    }

    @Test
    public void testLengthUnit_FromString_CentimeterVariants() {
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.fromString("cm"));
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.fromString("cms"));
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.fromString("centimeter"));
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.fromString("centimeters"));
    }

    @Test
    public void testLengthUnit_FromString_NullInput() {
        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.LengthUnit.fromString(null));
    }

    @Test
    public void testLengthUnit_FromString_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.LengthUnit.fromString("meter"));
    }

    @Test
    public void testHashCode_SameLogicalValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(91.44, quantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testToString_Output() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals("Quantity(1.0, \"yards\")", q1.toString());
    }
}