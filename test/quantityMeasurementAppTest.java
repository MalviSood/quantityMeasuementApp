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
    public void testAdd_FeetAndInches_ResultInFeet() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAdd_InchesAndFeet_ResultInInches() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(24.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAdd_YardsAndFeet_ResultInYards() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(3.0, quantityMeasurementApp.LengthUnit.FEET);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAdd_CentimetersAndInches_ResultInCentimeters() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(2.54, quantityMeasurementApp.LengthUnit.CENTIMETERS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(5.08, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAdd_CentimetersAndFeet_ResultInCentimeters() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(30.48, quantityMeasurementApp.LengthUnit.CENTIMETERS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(60.96, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAdd_YardsAndCentimeters_ResultInYards() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(91.44, quantityMeasurementApp.LengthUnit.CENTIMETERS);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAdd_SameUnitFeet() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(2.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(3.0, quantityMeasurementApp.LengthUnit.FEET);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(5.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAdd_SameUnitInches() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(10.0, quantityMeasurementApp.LengthUnit.INCHES);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(5.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(15.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAdd_UsingStaticMethod() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                quantityMeasurementApp.QuantityLength.add(q1, q2);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAdd_NullOtherQuantity() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    public void testAdd_StaticMethod_FirstNull() {
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.add(null, q2));
    }

    @Test
    public void testAdd_StaticMethod_SecondNull() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.add(q1, null));
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
    public void testConvert_YardsToFeet() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(3.0, q.convertTo(quantityMeasurementApp.LengthUnit.FEET), 0.000001);
    }

    @Test
    public void testParse_FeetInput() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("1 feet");

        assertNotNull(q);
        assertEquals(1.0, q.getValue());
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, q.getUnit());
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
    public void testQuantityConstructor_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new quantityMeasurementApp.QuantityLength(Double.NaN,
                        quantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testQuantityConstructor_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new quantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    public void testToString_Output() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(2.0, quantityMeasurementApp.LengthUnit.FEET);

        assertEquals("Quantity(2.0, \"feet\")", q.toString());
    }
}