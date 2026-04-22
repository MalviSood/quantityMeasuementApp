public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(1.0 / 1000.0),
    POUND(0.453592);

    private final double toBaseFactor; // base = kilogram

    WeightUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    public double convertToBaseUnit(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        return value * toBaseFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        if (!Double.isFinite(baseValue)) {
            throw new IllegalArgumentException("Base value must be a finite number");
        }
        return baseValue / toBaseFactor;
    }

    public static WeightUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.trim().toLowerCase()) {
            case "kg":
            case "kilogram":
            case "kilograms":
                return KILOGRAM;
            case "g":
            case "gram":
            case "grams":
                return GRAM;
            case "lb":
            case "lbs":
            case "pound":
            case "pounds":
                return POUND;
            default:
                throw new IllegalArgumentException("Unsupported weight unit: " + unit);
        }
    }
}