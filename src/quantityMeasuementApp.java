//package com.apps.quantitymeasurement;

import java.util.Scanner;

 class quantityMeasurementApp {

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // Ask input in required format
            String input = scanner.nextLine(); // Example: "1.0 ft and 1.0 ft"

            // Extract values
            String[] parts = input.split(" ");

            double value1 = Double.parseDouble(parts[0]);
            double value2 = Double.parseDouble(parts[3]);

            Feet feet1 = new Feet(value1);
            Feet feet2 = new Feet(value2);

            boolean result = feet1.equals(feet2);

            // Required output format
            if (result) {
                System.out.println("Output: Equal (true)");
            } else {
                System.out.println("Output: Not Equal (false)");
            }

        } catch (Exception e) {
            System.out.println("Output: Not Equal (false)");
        }

        scanner.close();
    }
}