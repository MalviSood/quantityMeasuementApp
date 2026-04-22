import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class quantityMeasurementAppTest {

    private quantityMeasurementApp.QuantityWeight parseWeight(String input) {
        try {
            String[] parts = input.trim().split("\\s+");
            double value = Double.parseDouble(parts[0]);
            WeightUnit unit = WeightUnit.fromString(parts[1]);
            return new quantityMeasurementApp.QuantityWeight(value, unit);
        } catch (Exception e) {
            return null;
        }
    }

    private quantityMeasurementApp.QuantityLength parseLength(String input) {
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
    public void testWeightEquality_KgToKg_SameValue() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testWeightEquality_KgToGram_SameValue() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testWeightEquality_KgToPound_SameValue() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1.0 / 0.453592, WeightUnit.POUND);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testWeightEquality_GramToPound_DifferentValue() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(500.0, WeightUnit.GRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.POUND);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testWeightConvert_KgToGram() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(1000.0, q.convertTo(WeightUnit.GRAM), 0.000001);
    }

    @Test
    public void testWeightConvert_GramToKg() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(500.0, WeightUnit.GRAM);

        assertEquals(0.5, q.convertTo(WeightUnit.KILOGRAM), 0.000001);
    }

    @Test
    public void testWeightConvert_PoundToKg() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.POUND);

        assertEquals(0.453592, q.convertTo(WeightUnit.KILOGRAM), 0.000001);
    }

    @Test
    public void testWeightConvert_KgToPound() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(1.0 / 0.453592, q.convertTo(WeightUnit.POUND), 0.000001);
    }

    @Test
    public void testWeightAdd_SameUnit() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(2.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(3.0, WeightUnit.KILOGRAM);

        quantityMeasurementApp.QuantityWeight result = q1.add(q2);

        assertEquals(5.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testWeightAdd_CrossUnit_DefaultUnit() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(500.0, WeightUnit.GRAM);

        quantityMeasurementApp.QuantityWeight result = q1.add(q2);

        assertEquals(1.5, result.getValue(), 0.000001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testWeightAdd_CrossUnit_TargetGram() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.POUND);

        quantityMeasurementApp.QuantityWeight result = q1.add(q2, WeightUnit.GRAM);

        assertEquals(1453.592, result.getValue(), 0.000001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testWeightAdd_CrossUnit_TargetPound() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1000.0, WeightUnit.GRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        quantityMeasurementApp.QuantityWeight result = q1.add(q2, WeightUnit.POUND);

        assertEquals(2.0 / 0.453592, result.getValue(), 0.000001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testWeightAdd_Commutative() {
        quantityMeasurementApp.QuantityWeight a =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight b =
                new quantityMeasurementApp.QuantityWeight(500.0, WeightUnit.GRAM);

        quantityMeasurementApp.QuantityWeight result1 = a.add(b, WeightUnit.GRAM);
        quantityMeasurementApp.QuantityWeight result2 = b.add(a, WeightUnit.GRAM);

        assertEquals(result1.getValue(), result2.getValue(), 0.000001);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    @Test
    public void testWeightStaticAdd_DefaultUnit() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1000.0, WeightUnit.GRAM);

        quantityMeasurementApp.QuantityWeight result =
                quantityMeasurementApp.QuantityWeight.add(q1, q2);

        assertEquals(2.0, result.getValue(), 0.000001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testWeightStaticAdd_TargetUnit() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1000.0, WeightUnit.GRAM);

        quantityMeasurementApp.QuantityWeight result =
                quantityMeasurementApp.QuantityWeight.add(q1, q2, WeightUnit.POUND);

        assertEquals(2.0 / 0.453592, result.getValue(), 0.000001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testWeightVsLength_Incompatibility_Equals() {
        quantityMeasurementApp.QuantityWeight weight =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityLength length =
                new quantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
        assertFalse(length.equals(weight));
    }

    @Test
    public void testWeightUnit_FromString() {
        assertEquals(WeightUnit.KILOGRAM, WeightUnit.fromString("kg"));
        assertEquals(WeightUnit.KILOGRAM, WeightUnit.fromString("kilogram"));
        assertEquals(WeightUnit.GRAM, WeightUnit.fromString("g"));
        assertEquals(WeightUnit.GRAM, WeightUnit.fromString("gram"));
        assertEquals(WeightUnit.POUND, WeightUnit.fromString("lb"));
        assertEquals(WeightUnit.POUND, WeightUnit.fromString("pound"));
    }

    @Test
    public void testParseWeight_Valid() {
        quantityMeasurementApp.QuantityWeight q = parseWeight("2 kg");

        assertNotNull(q);
        assertEquals(2.0, q.getValue(), 0.000001);
        assertEquals(WeightUnit.KILOGRAM, q.getUnit());
    }

    @Test
    public void testParseWeight_InvalidUnit() {
        assertNull(parseWeight("5 ounce"));
    }

    @Test
    public void testParseWeight_NonNumeric() {
        assertNull(parseWeight("abc kg"));
    }

    @Test
    public void testWeightConstructor_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new quantityMeasurementApp.QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    public void testWeightConstructor_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new quantityMeasurementApp.QuantityWeight(1.0, null));
    }

    @Test
    public void testWeightConvert_NullTargetUnit() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> q.convertTo(null));
    }

    @Test
    public void testWeightAdd_NullOther() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () ->
                q.add((quantityMeasurementApp.QuantityWeight) null));
    }

    @Test
    public void testWeightAdd_NullTargetUnit() {
        quantityMeasurementApp.QuantityWeight q1 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight q2 =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.GRAM);

        assertThrows(IllegalArgumentException.class, () ->
                q1.add(q2, (WeightUnit) null));
    }

    @Test
    public void testWeightStaticAdd_NullArguments() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityWeight.add(
                        (quantityMeasurementApp.QuantityWeight) null, q));

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityWeight.add(
                        q, (quantityMeasurementApp.QuantityWeight) null));

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityWeight.add(
                        (quantityMeasurementApp.QuantityWeight) null,
                        q,
                        WeightUnit.KILOGRAM));

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityWeight.add(
                        q,
                        (quantityMeasurementApp.QuantityWeight) null,
                        WeightUnit.KILOGRAM));

        assertThrows(IllegalArgumentException.class, () ->
                quantityMeasurementApp.QuantityWeight.add(
                        q,
                        q,
                        (WeightUnit) null));
    }

    @Test
    public void testWeightEdgeCases_ZeroNegativeLarge() {
        quantityMeasurementApp.QuantityWeight zero =
                new quantityMeasurementApp.QuantityWeight(0.0, WeightUnit.KILOGRAM);
        quantityMeasurementApp.QuantityWeight negative =
                new quantityMeasurementApp.QuantityWeight(-500.0, WeightUnit.GRAM);
        quantityMeasurementApp.QuantityWeight large =
                new quantityMeasurementApp.QuantityWeight(1_000_000.0, WeightUnit.GRAM);

        assertEquals(0.0, zero.convertTo(WeightUnit.GRAM), 0.000001);
        assertEquals(-0.5, negative.convertTo(WeightUnit.KILOGRAM), 0.000001);
        assertEquals(1000.0, large.convertTo(WeightUnit.KILOGRAM), 0.000001);
    }

    @Test
    public void testWeightToString() {
        quantityMeasurementApp.QuantityWeight q =
                new quantityMeasurementApp.QuantityWeight(2.0, WeightUnit.POUND);

        assertEquals("Quantity(2.0, \"pound\")", q.toString());
    }

    @Test
    public void testLengthStillWorks() {
        quantityMeasurementApp.QuantityLength q1 =
                new quantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        quantityMeasurementApp.QuantityLength q2 =
                new quantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }
}