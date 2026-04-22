public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toBaseFactor; // base unit = feet

    LengthUnit(double toBaseFactor) {
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

    public static LengthUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        String normalized = unit.trim().toLowerCase();

        switch (normalized) {
            case "ft":
            case "foot":
            case "feet":
                return FEET;
            case "in":
            case "inch":
            case "inches":
                return INCHES;
            case "yd":
            case "yard":
            case "yards":
                return YARDS;
            case "cm":
            case "cms":
            case "centimeter":
            case "centimeters":
                return CENTIMETERS;
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }
}