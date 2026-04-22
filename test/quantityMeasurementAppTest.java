import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class quantityMeasurementAppTest {

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
    public void testConvert_FeetToInches() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                1.0,
                quantityMeasurementApp.LengthUnit.FEET,
                quantityMeasurementApp.LengthUnit.INCHES
        );

        assertEquals(12.0, result, 0.000001);
    }

    @Test
    public void testConvert_YardsToFeet() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                1.0,
                quantityMeasurementApp.LengthUnit.YARDS,
                quantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(3.0, result, 0.000001);
    }

    @Test
    public void testConvert_YardsToInches() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                1.0,
                quantityMeasurementApp.LengthUnit.YARDS,
                quantityMeasurementApp.LengthUnit.INCHES
        );

        assertEquals(36.0, result, 0.000001);
    }

    @Test
    public void testConvert_CentimetersToInches() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                2.54,
                quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.INCHES
        );

        assertEquals(1.0, result, 0.000001);
    }

    @Test
    public void testConvert_CentimetersToFeet() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                30.48,
                quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(1.0, result, 0.000001);
    }

    @Test
    public void testConvert_CentimetersToYards() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                91.44,
                quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.YARDS
        );

        assertEquals(1.0, result, 0.000001);
    }

    @Test
    public void testConvert_InchesToFeet() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                24.0,
                quantityMeasurementApp.LengthUnit.INCHES,
                quantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(2.0, result, 0.000001);
    }

    @Test
    public void testConvert_FeetToYards() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                6.0,
                quantityMeasurementApp.LengthUnit.FEET,
                quantityMeasurementApp.LengthUnit.YARDS
        );

        assertEquals(2.0, result, 0.000001);
    }

    @Test
    public void testConvert_SameUnitFeetToFeet() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                5.0,
                quantityMeasurementApp.LengthUnit.FEET,
                quantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(5.0, result, 0.000001);
    }

    @Test
    public void testConvert_SameUnitCmToCm() {
        double result = quantityMeasurementApp.QuantityLength.convert(
                25.0,
                quantityMeasurementApp.LengthUnit.CENTIMETERS,
                quantityMeasurementApp.LengthUnit.CENTIMETERS
        );

        assertEquals(25.0, result, 0.000001);
    }

    @Test
    public void testConvert_InstanceMethod_FeetToInches() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(2.0, quantityMeasurementApp.LengthUnit.FEET);

        assertEquals(24.0, q.convertTo(quantityMeasurementApp.LengthUnit.INCHES), 0.000001);
    }

    @Test
    public void testConvert_InstanceMethod_YardsToCm() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(91.44, q.convertTo(quantityMeasurementApp.LengthUnit.CENTIMETERS), 0.000001);
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
    public void testEquality_YardsToFeet_SameConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(3.0, quantityMeasurementApp.LengthUnit.FEET);

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
    public void testEquality_DifferentConvertedValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(35.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testConvert_InvalidSourceUnit_Null() {
        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.convert(
                        1.0,
                        null,
                        quantityMeasurementApp.LengthUnit.FEET
                )
        );
    }

    @Test
    public void testConvert_InvalidTargetUnit_Null() {
        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.convert(
                        1.0,
                        quantityMeasurementApp.LengthUnit.FEET,
                        null
                )
        );
    }

    @Test
    public void testConvert_InvalidValue_NaN() {
        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.convert(
                        Double.NaN,
                        quantityMeasurementApp.LengthUnit.FEET,
                        quantityMeasurementApp.LengthUnit.INCHES
                )
        );
    }

    @Test
    public void testConvert_InvalidValue_Infinity() {
        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.convert(
                        Double.POSITIVE_INFINITY,
                        quantityMeasurementApp.LengthUnit.FEET,
                        quantityMeasurementApp.LengthUnit.INCHES
                )
        );
    }

    @Test
    public void testQuantityConstructor_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new quantityMeasurementApp.QuantityLength(
                        Double.NaN,
                        quantityMeasurementApp.LengthUnit.FEET
                )
        );
    }

    @Test
    public void testQuantityConstructor_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new quantityMeasurementApp.QuantityLength(1.0, null)
        );
    }

    @Test
    public void testParse_FeetInput() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("1 feet");

        assertNotNull(q);
        assertEquals(1.0, q.getValue());
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, q.getUnit());
    }

    @Test
    public void testParse_YardsInput() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("2 yards");

        assertNotNull(q);
        assertEquals(2.0, q.getValue());
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, q.getUnit());
    }

    @Test
    public void testParse_CentimetersInput() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("30.48 cm");

        assertNotNull(q);
        assertEquals(30.48, q.getValue());
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS, q.getUnit());
    }

    @Test
    public void testParse_InvalidUnitInput() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("5 meter");

        assertNull(q);
    }

    @Test
    public void testToString_Output() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals("Quantity(1.0, \"yards\")", q.toString());
    }
}