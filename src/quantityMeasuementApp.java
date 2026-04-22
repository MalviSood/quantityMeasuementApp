import java.util.Scanner;
class quantityMeasurementApp {

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

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = this.toBaseUnit();
            return targetUnit.convertFromBaseUnit(baseValue);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = this.unit.convertFromBaseUnit(sumBase);
            return new QuantityLength(resultValue, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = targetUnit.convertFromBaseUnit(sumBase);
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
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }

        @Override
        public int hashCode() {
            long rounded = Math.round(toBaseUnit() * 1_000_000);
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