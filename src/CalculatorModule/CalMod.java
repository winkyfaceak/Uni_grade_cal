package CalculatorModule;

import java.util.Arrays;

public class CalMod {
    private float[] grades;
    private int onlyCount;
    private float maxMark;
    private double weight;

    public void gradeGrabber(String grade) {
        String[] gradeStrings = grade.split(" ");
        grades = new float[gradeStrings.length];
        try {
            for (int i = 0; i < gradeStrings.length; i++) {
                grades[i] = Float.parseFloat(gradeStrings[i]);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid grade format. Please enter valid numbers.", e);
        }
    }

    public float[] getGrades() {
        return grades;
    }

    public float getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(float maxMark) {
        if (maxMark < 0) throw new IllegalArgumentException("Number of grades cannot be negative.");
        this.maxMark = maxMark;
    }

    public double getWeight() {
        return weight * 100;
    }

    public void setWeight(double weightPercentage) {
        if (weightPercentage < 0) throw new IllegalArgumentException("Weight percentage cannot be negative.");
        if (weightPercentage > 100) throw new IllegalArgumentException("Weight percentage cannot exceed 100.");
        this.weight = weightPercentage / 100;
    }

    public int getOnlyCount() {
        return onlyCount;
    }

    public void setOnlyCount(int onlyCount) {
        if (onlyCount < 0) throw new IllegalArgumentException("Number of grades cannot be negative.");
        this.onlyCount = onlyCount;
    }

    public boolean calculateState() {
        boolean isGradesValid = (grades != null && grades.length > 0);
        if (!isGradesValid) return true;

        boolean isOnlyCountValid = (onlyCount > 0);
        if (!isOnlyCountValid) return true;

        boolean isMaxMarkValid = (maxMark > 0);
        if (!isMaxMarkValid) return true;

        boolean isWeightValid = (weight > 0);
        return !isWeightValid;
    }

    public double gradeJudge() {
        if (grades == null || grades.length == 0) {
            throw new IllegalStateException("No grades available for calculation.");
        }

        Arrays.sort(grades);
        float[] sortedGrades = new float[grades.length];
        for (int i = 0; i < grades.length; i++) {
            sortedGrades[i] = grades[grades.length - i - 1]; // Reverse sorting
        }

        int count = Math.min(onlyCount, sortedGrades.length);
        float[] topGrades = Arrays.copyOfRange(sortedGrades, 0, count);

        float sum = 0;
        for (float grade : topGrades) {
            sum += grade;
        }

        double calc = ((sum / (maxMark * count)) * weight) * 100;
        return (double) Math.round(calc * 100.0) / 100.0;
    }
}