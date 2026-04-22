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
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be a finite number");
            }
            return value * toInchesFactor;
        }

        public double fromInches(double inchesValue) {
            if (!Double.isFinite(inchesValue)) {
                throw new IllegalArgumentException("Value must be a finite number");
            }
            return inchesValue / toInchesFactor;
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
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be a finite number");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

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

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            return targetUnit.fromInches(this.toInches());
        }

        // UC6 behavior: result in first operand unit
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double totalInches = this.toInches() + other.toInches();
            double resultValue = this.unit.fromInches(totalInches);
            return new QuantityLength(resultValue, this.unit);
        }

        // UC7 behavior: result in explicit target unit
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double totalInches = this.toInches() + other.toInches();
            double resultValue = targetUnit.fromInches(totalInches);
            return new QuantityLength(resultValue, targetUnit);
        }

        public static QuantityLength add(QuantityLength first, QuantityLength second) {
            if (first == null || second == null) {
                throw new IllegalArgumentException("Both quantities must be non-null");
            }
            return first.add(second);
        }

        public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
            if (first == null || second == null) {
                throw new IllegalArgumentException("Both quantities must be non-null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            return first.add(second, targetUnit);
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
            System.out.println("Enter first quantity:");
            QuantityLength q1 = readQuantity(scanner);

            System.out.println("Enter second quantity:");
            QuantityLength q2 = readQuantity(scanner);

            System.out.println("Enter target unit:");
            String targetUnitText = scanner.next();
            LengthUnit targetUnit = LengthUnit.fromString(targetUnitText);

            QuantityLength result = q1.add(q2, targetUnit);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
            scanner.close();
        }
    }
}