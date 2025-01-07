package Cli;

import CalculatorModule.CalMod;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalMod calculator = new CalMod();
        float [] currentGrades;
        System.out.println("Welcome to Grade Calculator!");
        while (true) {
            boolean canCalculate = !calculator.calculateState();
            if (calculator.getGrades() == null){
                currentGrades = new float[0];
            }else{
                currentGrades = calculator.getGrades();
            }

            int currentCount = calculator.getOnlyCount();
            float currentMaxMark = calculator.getMaxMark();
            double currentWeight = calculator.getWeight();
            System.out.println("\nOptions:");
            System.out.println("1. Enter grades (space-separated) " + Arrays.toString(currentGrades));
            System.out.println("2. Set the number of grades to consider " + currentCount);
            System.out.println("3. Set the maximum mark " + currentMaxMark);
            System.out.println("4. Set the weight percentage " + currentWeight);
            if (canCalculate) {
                System.out.println("5. Calculate weighted grade");
                System.out.println("6. Exit");
            } else {
                System.out.println("5. Exit");
            }
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " +
                        (canCalculate ? "6" : "5") + ".");
                continue;
            }

            if ((!canCalculate && choice > 5) || (canCalculate && choice > 6) || choice < 1) {
                System.out.println("Invalid choice. Please choose a number between 1 and " +
                        (canCalculate ? "6" : "5") + ".");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter grades (space-separated): ");
                    String gradeInput = scanner.nextLine();
                    try {
                        calculator.gradeGrabber(gradeInput);
                        System.out.println("Grades saved successfully.");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the number of grades to consider: ");
                    try {
                        int count = Integer.parseInt(scanner.nextLine().trim());
                        calculator.setOnlyCount(count);
                        System.out.println("Count updated successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter the maximum mark: ");
                    try {
                        float maxMark = Float.parseFloat(scanner.nextLine().trim());
                        calculator.setMaxMark(maxMark);
                        System.out.println("Max mark updated successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter the weight: ");
                    try {
                        double weight = Double.parseDouble(scanner.nextLine().trim());
                        calculator.setWeight(weight);
                        System.out.println("Weight updated successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    if (canCalculate) {
                        try {
                            double weightedGrade = calculator.gradeJudge();
                            System.out.print("The calculated weighted grade is: \n" + weightedGrade);
                        } catch (IllegalStateException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    }
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }
}