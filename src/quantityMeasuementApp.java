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
            return targetUnit.convertFromBaseUnit(this.toBaseUnit());
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            return new QuantityLength(this.unit.convertFromBaseUnit(sumBase), this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            return new QuantityLength(targetUnit.convertFromBaseUnit(sumBase), targetUnit);
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

    public static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityWeight(double value, WeightUnit unit) {
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

        public WeightUnit getUnit() {
            return unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public double convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            return targetUnit.convertFromBaseUnit(this.toBaseUnit());
        }

        public QuantityWeight add(QuantityWeight other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            return new QuantityWeight(this.unit.convertFromBaseUnit(sumBase), this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            return new QuantityWeight(targetUnit.convertFromBaseUnit(sumBase), targetUnit);
        }

        public static QuantityWeight add(QuantityWeight first, QuantityWeight second) {
            if (first == null || second == null) {
                throw new IllegalArgumentException("Both quantities must be non-null");
            }
            return first.add(second);
        }

        public static QuantityWeight add(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {
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
            QuantityWeight other = (QuantityWeight) obj;
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

    public static QuantityLength readLength(Scanner scanner) {
        double value = scanner.nextDouble();
        String unitText = scanner.next();
        return new QuantityLength(value, LengthUnit.fromString(unitText));
    }

    public static QuantityWeight readWeight(Scanner scanner) {
        double value = scanner.nextDouble();
        String unitText = scanner.next();
        return new QuantityWeight(value, WeightUnit.fromString(unitText));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Choose category: length or weight");
            String category = scanner.next().trim().toLowerCase();

            if ("length".equals(category)) {
                System.out.println("Enter first length:");
                QuantityLength q1 = readLength(scanner);

                System.out.println("Enter second length:");
                QuantityLength q2 = readLength(scanner);

                System.out.println("Enter target length unit:");
                LengthUnit targetUnit = LengthUnit.fromString(scanner.next());

                QuantityLength result = q1.add(q2, targetUnit);
                System.out.println("Result: " + result);

            } else if ("weight".equals(category)) {
                System.out.println("Enter first weight:");
                QuantityWeight q1 = readWeight(scanner);

                System.out.println("Enter second weight:");
                QuantityWeight q2 = readWeight(scanner);

                System.out.println("Enter target weight unit:");
                WeightUnit targetUnit = WeightUnit.fromString(scanner.next());

                QuantityWeight result = q1.add(q2, targetUnit);
                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid input");
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
            scanner.close();
        }
    }
}