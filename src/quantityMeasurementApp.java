
import java.util.Scanner;

class quantityMeasurementApp {

    // Feet class
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Inches class
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Method for Feet equality
    public static void demonstrateFeetEquality(Scanner scanner) {
        try {
            String input = scanner.nextLine(); // e.g., "1.0 ft and 1.0 ft"
            String[] parts = input.split(" ");

            double v1 = Double.parseDouble(parts[0]);
            double v2 = Double.parseDouble(parts[3]);

            Feet f1 = new Feet(v1);
            Feet f2 = new Feet(v2);

            if (f1.equals(f2)) {
                System.out.println("Output: Equal (true)");
            } else {
                System.out.println("Output: Not Equal (false)");
            }
        } catch (Exception e) {
            System.out.println("Output: Not Equal (false)");
        }
    }

    // Method for Inches equality
    public static void demonstrateInchesEquality(Scanner scanner) {
        try {
            String input = scanner.nextLine(); // e.g., "1.0 inch and 1.0 inch"
            String[] parts = input.split(" ");

            double v1 = Double.parseDouble(parts[0]);
            double v2 = Double.parseDouble(parts[3]);

            Inches i1 = new Inches(v1);
            Inches i2 = new Inches(v2);

            if (i1.equals(i2)) {
                System.out.println("Output: Equal (true)");
            } else {
                System.out.println("Output: Not Equal (false)");
            }
        } catch (Exception e) {
            System.out.println("Output: Not Equal (false)");
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Feet input
        demonstrateFeetEquality(scanner);

        // Inches input
        demonstrateInchesEquality(scanner);

        scanner.close();
    }
}