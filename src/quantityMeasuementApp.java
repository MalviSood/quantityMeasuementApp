import java.util.Scanner;

class quantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(1.0 / 2.54);

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double toInches(double value) {
            return value * toInchesFactor;
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

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toInches() {
            return unit.toInches(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toInches() - other.toInches()) < EPSILON;
        }

        @Override
        public int hashCode() {
            long rounded = Math.round(toInches() * 1_000_000);
            return Long.hashCode(rounded);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
        }
    }

    public static QuantityLength readQuantity(Scanner scanner) {
        double value = scanner.nextDouble();
        String unitText = scanner.next();
        LengthUnit unit = LengthUnit.fromString(unitText);
        return new QuantityLength(value, unit);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            QuantityLength q1 = readQuantity(scanner);
            QuantityLength q2 = readQuantity(scanner);

            if (q1.equals(q2)) {
                System.out.println("Output: Equal (true)");
            } else {
                System.out.println("Output: Not Equal (false)");
            }
        } catch (Exception e) {
            System.out.println("Output: Not Equal (false)");
        } finally {
            scanner.close();
        }
    }
}