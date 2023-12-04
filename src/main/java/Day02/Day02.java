package Day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day02 {

    private static String file = "src/main/resources/02_input.txt";
    //private static String file = "src/test/resources/02_input_test.txt";

    public static void main(String[] args) throws IOException {
        Day02 starter = new Day02();
        starter.doPart1();
        starter.doPart2();
    }

    private void doPart2() throws IOException {
        FileReader fileReader = new FileReader(file);
        HashMap<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splittedString = line.split(":");
                map.put(splittedString[0], getHighestCubeNumber(splittedString[1]));
            }
            System.out.println("Result Part 2: " + map.values().stream().mapToInt(i -> i).sum());
        }
    }

    private void doPart1() throws IOException {
        Map<String, Boolean> map = execute(file);
        //Nach True Filtern
        Map<String, Boolean> result = map.entrySet().stream()
                .filter(x -> Boolean.TRUE.equals(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //Summe aus Games bilden
        //regex
        Set<String> keys = result.keySet();
        ArrayList<Integer> gameNumbers = new ArrayList<>();
        for (String s : keys) {
            gameNumbers.add(Integer.parseInt(s.replaceAll("[^0-9]", "")));
        }
        System.out.println("Result Part 1: " + gameNumbers.stream().mapToInt(i -> i).sum());
    }

    private Integer getHighestCubeNumber(String str) {
        //3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        String[] splittedString = str.split(";");
        Integer highestRed = 0;
        Integer highestBlue = 0;
        Integer highestGreen = 0;

        for (String ziehung : splittedString) {
            String number;
            String[] color = ziehung.split(",");
            for (String s2 : color) {
                if (s2.contains("red")) {
                    number = s2.replaceAll("[^0-9]", "");
                    if (Integer.parseInt(number) > highestRed) {
                        highestRed = Integer.parseInt(number);
                    }
                }
                if (s2.contains("blue")) {
                    number = s2.replaceAll("[^0-9]", "");
                    if (Integer.parseInt(number) > highestBlue) {
                        highestBlue = Integer.parseInt(number);
                    }
                }
                if (s2.contains("green")) {
                    number = s2.replaceAll("[^0-9]", "");
                    if (Integer.parseInt(number) > highestGreen) {
                        highestGreen = Integer.parseInt(number);
                    }
                }
            }
        }
        return highestGreen * highestBlue * highestRed;
    }

    public Map<String, Boolean> execute(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Map<String, Boolean> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                //For each Line, put String before ":" in a Map as Key
                String[] splittedString = line.split(":");
                map.put(splittedString[0], checkIfPossible(splittedString[1]));
            }
        }
        return map;
    }

    //For Value: Check if the sets are not more than 12 red cubes, 13 green cubes, and 14 blue cubes
    //if yes: put true as value
    //if no: put false as value
    private Boolean checkIfPossible(String s) {
        //12 red, 8 green; 8 red, 6 green, 3 blue; 12 red, 4 blue, 2 green
        String[] splittedByGames = s.split(";");
        Boolean result = Boolean.TRUE;
        outerloop:
        for (String string : splittedByGames) {
            //12 red, 8 green
            //String splitten nach ersten buchstaben.
            String[] splittedByColor = string.split(",");

            for (String str : splittedByColor) {
                String number;
                if (str.contains("red")) {
                    number = str.replaceAll("[^0-9]", "");
                    if (Integer.parseInt(number) > 12) {
                        result = Boolean.FALSE;
                        break outerloop;
                    }
                }
                if (str.contains("blue")) {
                    number = str.replaceAll("[^0-9]", "");
                    if (Integer.parseInt(number) > 14) {
                        result = Boolean.FALSE;
                        break outerloop;
                    }
                }
                if (str.contains("green")) {
                    number = str.replaceAll("[^0-9]", "");
                    if (Integer.parseInt(number) > 13) {
                        result = Boolean.FALSE;
                        break outerloop;
                    }
                }
            }
        }
        return result;
    }
}
