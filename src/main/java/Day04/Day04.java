package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day04 {

    private static String file = "src/main/resources/04_input.txt";
    //private static String file = "src/test/resources/04_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day04 starter = new Day04();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<String> myCardList;
            List<String> winnerCardList;
            ArrayList<Integer> pointsList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] splittedByGame = line.split(":");
                String[] gameDraw = splittedByGame[1].split("\\|");

                String[] myCards = gameDraw[0].trim().split("\\s+");
                String[] winnerCards = gameDraw[1].trim().split("\\s+");

                myCardList = Arrays.asList(myCards);
                winnerCardList = Arrays.asList(winnerCards);
                //Part#1:
                pointsList.add(getPointsOfGame(myCardList, winnerCardList, true));

            }
            System.out.println(pointsList.stream().mapToInt(i -> i).sum());
        }
    }

    private int getPointsOfGame(List<String> myCardList, List<String> winnerCardList, boolean isPart1) {
        int points = 0;
        for (String currentCard : myCardList) {
            Integer currentNumber = Integer.parseInt(currentCard);
            for (int i = 0; i < winnerCardList.size(); i++) {
                if (currentNumber.equals(Integer.parseInt(winnerCardList.get(i)))) {
                    points++;
                }
            }
        }
        if (isPart1) {
            if (points == 1 || points == 2) {
                return points;
            }
            Double power = Math.pow(2, points - 1);
            return power.intValue();
        } else {
            return points;
        }
    }
}
