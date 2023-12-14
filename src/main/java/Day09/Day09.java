package Day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Day09 {

    private static String file = "src/main/resources/09_input.txt";
    //private static String file = "src/test/resources/09_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day09 starter = new Day09();
        starter.doPart1(file);
    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<Integer> values = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                int value = convertStringToArray(line);
                values.add(value);
            }
            System.out.println(values);
            System.out.println(values.stream().mapToInt(i -> i).sum());
        }
    }

    private int convertStringToArray(String line) {
        String[] stringArray = line.split("\\s+");
        List<Integer> startingList = new ArrayList<>();
        for (String s : stringArray) {
            startingList.add(Integer.parseInt(s));
        }
        List<Integer> deltaList = startingList;
        List<List<Integer>> historyList = new ArrayList<>();
        historyList.add(startingList);
        //Solange Listen erstellen, bis nur nullen in der Liste sind.
        while (!allElementAreZeros(deltaList)) {
            deltaList = getDeltasInStartingList(deltaList);
            historyList.add(deltaList);
        }
        //Von unten auffüllen:
        //1. eine Null einfügen
        //2. Zahl dadrüber einfügen
        //3. Zahl aus 2. + Zahl dadrüber = neue Zahl
        //4. weiter

        //Für Part 2: Das gleiche bloß am Anfang der Liste
        int lowestList = historyList.size();
        for (int i = lowestList - 1; i >= 0; i--) {
            historyList.get(i);
            if (i == lowestList - 1) {
                //Part 1:
                //addValuesToListRight(historyList, i, true);
                addValuesToListLeft(historyList, i, true);
            } else {
                //Part 1:
                //addValuesToListRight(historyList, i, false);
                addValuesToListLeft(historyList, i, false);
            }
        }
        //return getHistoryValues(historyList);
        return getHistoryValuesPart2(historyList);
    }



    private void addValuesToListRight(List<List<Integer>> integers, int i, boolean isLowestList) {
        if (isLowestList) {
            integers.get(i).add(0, 0);
        } else {
            int sizeCurrentList = integers.get(i).size();
            int sizeNextList = integers.get(i + 1).size();
            int valueCurrentList = integers.get(i).get(sizeCurrentList - 1);
            int valueNextList = integers.get(i + 1).get(sizeNextList - 1);
            integers.get(i).add(valueNextList + valueCurrentList);
        }
    }

    private void addValuesToListLeft(List<List<Integer>> integers, int i, boolean isLowestList) {
        if (isLowestList) {
            integers.get(i).add(0);
        } else {
            int valueCurrentList = integers.get(i).get(0);
            int valueNextList = integers.get(i + 1).get(0);
            integers.get(i).add(0,valueCurrentList - valueNextList);
        }
    }

    private int getHistoryValuesPart2(List<List<Integer>> historyList) {
        List<Integer> highestList = historyList.get(0);
        return highestList.get(0);
    }

    private int getHistoryValues(List<List<Integer>> historyList) {
        List<Integer> highestList = historyList.get(0);
        int size = highestList.size();
        return highestList.get(size - 1);
    }

    private boolean allElementAreZeros(List<Integer> deltaList) {
        for (Integer i : deltaList) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getDeltasInStartingList(List<Integer> startingList) {
        List<Integer> deltaList = new ArrayList<>();
        for (int i = 0; i < startingList.size() - 1; i++) {
            Integer value1 = startingList.get(i);
            Integer value2 = startingList.get(i + 1);
            deltaList.add(value2 - value1);
        }
        return deltaList;
    }

    private boolean allValuesAreZeros(List<Integer> deltaList) {
        AtomicBoolean allValuesAreZeros = new AtomicBoolean(true);
        deltaList.stream().forEach(i -> {
            if (i != 0) {
                allValuesAreZeros.set(false);
            }
        });
        return allValuesAreZeros.get();
    }
}
