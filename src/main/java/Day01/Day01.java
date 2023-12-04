package Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day01 {
    private static String file = "src/main/resources/01_input.txt";

    public static void main(String[] args) throws IOException {
        Day01 starter = new Day01();
        ArrayList<Integer> digitList = starter.execute(file);
        System.out.println("Result: " + starter.sum(digitList));

    }

    public Integer sum(ArrayList<Integer> digitList) {
        return digitList.stream().mapToInt(i -> i).sum();
    }

    public ArrayList<Integer> execute(String file) throws IOException {
        //Input lesen
        FileReader fileReader = new FileReader(file);
        ArrayList<Integer> digitList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                //Day#1 Part#2 Comment in for Part#2
                line = formatLines(line);

                String numbersOnly = line.replaceAll("[^0-9]", "");
                if (numbersOnly.length() != 0) {
                    if (numbersOnly.length() > 1) {
                        String newDigit1 = numbersOnly.substring(0, 1);
                        String newDigit2 = numbersOnly.substring(numbersOnly.length() - 1, numbersOnly.length());
                        String newDigit = newDigit1 + newDigit2;
                        digitList.add(Integer.parseInt(newDigit));
                    } else {
                        String digit3 = numbersOnly + numbersOnly;
                        digitList.add(Integer.parseInt(digit3));
                    }
                } else {
                    digitList.add(Integer.parseInt("00"));
                }
            }
            System.out.println(digitList);
        }
        return digitList;
    }

    //Part2: Replacement Value is weird?!
    private String formatLines(String line) {
        StringBuilder result = new StringBuilder();
        String resultString = "";
        for (char c : line.toCharArray()) {
            result.append(c);
            resultString = result.toString();
            if (resultString.contains("one")) {
                resultString = resultString.replace("one", "o1e");
            }
            if (resultString.contains("two")) {
                resultString = resultString.replace("two", "t2o");
            }
            if (resultString.contains("three")) {
                resultString = resultString.replace("three", "th3ee");
            }
            if (resultString.contains("four")) {
                resultString = resultString.replace("four", "f4ur");
            }
            if (resultString.contains("five")) {
                resultString = resultString.replace("five", "f5ve");
            }
            if (resultString.contains("six")) {
                resultString = resultString.replace("six", "s6x");
            }
            if (resultString.contains("seven")) {
                resultString = resultString.replace("seven", "se7en");
            }
            if (resultString.contains("eight")) {
                resultString = resultString.replace("eight", "ei8ht");
            }
            if (resultString.contains("nine")) {
                resultString = resultString.replace("nine", "n9ne");
            }
            result = new StringBuilder(resultString);
        }
        return resultString;
    }
}
