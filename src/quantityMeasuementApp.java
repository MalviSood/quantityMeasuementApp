import java.util.Scanner;

class quantityMeasurementApp {

    // Step 1: Enum for supported length units with conversion factors to feet
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
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

                default:
                    throw new IllegalArgumentException("Unsupported unit: " + unit);
            }
        }
    }

    // Step 2: Generic quantity class for length measurement
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

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

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
        }
    }

    // Helper to parse input like: 1.0 feet
    public static QuantityLength readQuantity(Scanner scanner) {
        double value = scanner.nextDouble();
        String unitText = scanner.next();
        LengthUnit unit = LengthUnit.fromString(unitText);
        return new QuantityLength(value, unit);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Example user input:
            // 1.0 feet
            // 12.0 inches
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