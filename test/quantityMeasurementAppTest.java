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
    public void testAddition_TargetUnit_Feet() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_TargetUnit_Inches() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_TargetUnit_Yards() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_SameUnit_ToYards() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_InchesAndYards_ToFeet() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(36.0, quantityMeasurementApp.LengthUnit.INCHES);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.FEET);

        assertEquals(6.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_CentimetersAndInches_ToCentimeters() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(2.54, quantityMeasurementApp.LengthUnit.CENTIMETERS);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAddition_ZeroIdentity_ToYards() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(5.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(0.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(5.0 / 3.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_NegativeValue_ToInches() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(5.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(-2.0, quantityMeasurementApp.LengthUnit.FEET);

        quantityMeasurementApp.QuantityLength result =
                q1.add(q2, quantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_Commutative_InTargetUnit() {
        quantityMeasurementApp.QuantityLength a =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength b =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result1 =
                a.add(b, quantityMeasurementApp.LengthUnit.INCHES);
        quantityMeasurementApp.QuantityLength result2 =
                b.add(a, quantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(result1.getValue(), result2.getValue(), 0.000001);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    @Test
    public void testAddition_StaticMethod_WithTargetUnit() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result =
                quantityMeasurementApp.QuantityLength.add(
                        q1, q2, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_NullOtherQuantity() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                q1.add((quantityMeasurementApp.QuantityLength) null,
                        quantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testAddition_NullTargetUnit() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, quantityMeasurementApp.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () ->
                q1.add(q2, (quantityMeasurementApp.LengthUnit) null));
    }

    @Test
    public void testAddition_StaticMethod_FirstNull() {
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.add(
                        (quantityMeasurementApp.QuantityLength) null,
                        q2,
                        quantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testAddition_StaticMethod_SecondNull() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.add(
                        q1,
                        (quantityMeasurementApp.QuantityLength) null,
                        quantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testAddition_StaticMethod_TargetUnitNull() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityLength.add(
                        q1,
                        q2,
                        (quantityMeasurementApp.LengthUnit) null));
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
        assertEquals(30.48, q.getValue(), 0.000001);
        assertEquals(quantityMeasurementApp.LengthUnit.CENTIMETERS, q.getUnit());
    }

    @Test
    public void testParse_InvalidUnitInput() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("5 meter");

        assertNull(q);
    }

    @Test
    public void testConvertTo_YardsToFeet() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(1.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(3.0, q.convertTo(quantityMeasurementApp.LengthUnit.FEET), 0.000001);
    }

    @Test
    public void testToString_Output() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(2.0, quantityMeasurementApp.LengthUnit.YARDS);

        assertEquals("Quantity(2.0, \"yards\")", q.toString());
    }
}