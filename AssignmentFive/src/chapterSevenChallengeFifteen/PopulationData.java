package chapterSevenChallengeFifteen;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PopulationData {

    private final String fileName;
    private int[] data;

    public PopulationData(String fileName) {
        this.fileName = fileName;
    }

    public void displayResults() {
        readToArray();
        displayAverage();
        displayGreatestAndSmallestIncrease();
    }

    private void readToArray() {
        List<String> holder = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(fileName))) {
            while (s.hasNext()) {
                String line = s.nextLine();

                if (!line.isEmpty() && line.matches("[0-9]+")) {
                    holder.add(line);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found with name " + fileName);
        }

        //knew a little bit about streams from last semester, taking advantage of them
        data = holder.stream().mapToInt(Integer::parseInt).toArray();
    }

    private void displayAverage() {
        double total = 0;

        for (int yearlyPopulation : data) {
            total += yearlyPopulation;
        }

        double average = total / data.length;

        System.out.printf("The average from %d to %d was %.2f\n", 1950, 1990, average);
    }

    private void displayGreatestAndSmallestIncrease() {
        int currentYear = 1950;
        int maxYear = 1950;
        int minYear = 1950;

        int maxIncrease = Integer.MIN_VALUE;
        int minIncrease = Integer.MAX_VALUE;

        for (int i = 0; i < data.length - 1; i++) {
            int currentIncrease = data[i + 1] - data[i];

            if (currentIncrease > maxIncrease) {
               maxYear = currentYear;
               maxIncrease = currentIncrease;
            }

            if (currentIncrease < minIncrease) {
                minYear = currentYear;
                minIncrease = currentIncrease;
            }

            currentYear++;
        }

        System.out.printf("The max increase occurred during years %s to %s which gave an increase of %s\n", maxYear, maxYear + 1, maxIncrease);
        System.out.printf("The min increase occurred during years %s to %s which gave an increase of %s", minYear, minYear + 1, minIncrease);
    }





}
