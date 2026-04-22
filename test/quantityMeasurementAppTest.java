import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class quantityMeasurementAppTest {

    private quantityMeasurementApp.QuantityLength parseQuantity(String input) {
        try {
            String[] parts = input.trim().split("\\s+");
            double value = Double.parseDouble(parts[0]);
            LengthUnit unit = LengthUnit.fromString(parts[1]);

            return new quantityMeasurementApp.QuantityLength(value, unit);
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    public void testEquality_FeetToInches_SameValue() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testConvert_YardsToFeet() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(3.0, q.convertTo(LengthUnit.FEET), 0.000001);
    }

    @Test
    public void testAdd_TargetUnit_Yards() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES);

        quantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), 0.000001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testParse_ValidFeet() {
        quantityMeasurementApp.QuantityLength q = parseQuantity("1 feet");

        assertNotNull(q);
        assertEquals(1.0, q.getValue(), 0.000001);
        assertEquals(LengthUnit.FEET, q.getUnit());
    }

    @Test
    public void testParse_InvalidUnit() {
        assertNull(parseQuantity("5 meter"));
    }

    @Test
    public void testToString_Output() {
        quantityMeasurementApp.QuantityLength q =
                new quantityMeasurementApp.QuantityLength(2.0, LengthUnit.YARDS);

        assertEquals("Quantity(2.0, \"yards\")", q.toString());
    }
}