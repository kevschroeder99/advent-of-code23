package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day04Part2 {


    //private static String file = "src/main/resources/04_input.txt";
    private static String file = "src/test/resources/04_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day04Part2 starter = new Day04Part2();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        HashMap<String, Integer> cards;
        HashMap<String, Integer> mapMatchingNumbers;
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<String> myCardList;
            List<String> winnerCardList;
            mapMatchingNumbers = new HashMap<>();
            cards = new HashMap<>();
            ArrayList<String> allCards = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                allCards.add(line);
            }
            //System.out.println(allCards);
            String[] splittedByGame;
            String[] gameDraw;
            String[] myCards;
            String[] winnerCards;
            Integer matchesPerCard = 0;
            for (int i = 0; i < allCards.size(); i++) {
                //System.out.println(allCards.get(i));
                String card = allCards.get(i);
                //Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                //Jede Karte gibt es mindestens 1x mal
                splittedByGame = card.split(":");
                //[0] = Card 1, [1] = 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                cards.put(splittedByGame[0], 1);

                gameDraw = splittedByGame[1].split("\\|");
                myCards = gameDraw[0].trim().split("\\s+");
                winnerCards = gameDraw[1].trim().split("\\s+");
                myCardList = Arrays.asList(myCards);
                winnerCardList = Arrays.asList(winnerCards);
                //Matching Numbers ermitteln.
                matchesPerCard = getPointsOfGame(myCardList, winnerCardList, false);
                mapMatchingNumbers.put(splittedByGame[0], matchesPerCard);
//                for (int k = 1; k <= matchesPerCard; k++) {
//                    String wholeCard = allCards.get(i + k);
//                    String[] nextCard = wholeCard.split(":");
//                    System.out.println("Next Card: " + nextCard[0]);
//                    //Values hochsetzen:
//                    //cards.compute(nextCard[0], (key, value) -> value + 1);
//                }
            }


        }

        //values in Zielliste erhöhen
        addCardValues(mapMatchingNumbers, cards);

        System.out.println("#####");
        mapMatchingNumbers.forEach((key, value) -> System.out.println("Key: " + key + " --- Value: " + value));
        System.out.println("#####");
        //ErgebnisListe:
        cards.forEach((key, value) -> System.out.println("Key: " + key + " ---- Value: " + value));

    }

    private void addCardValues(HashMap<String, Integer> mapMatchingNumbers, HashMap<String, Integer> cards) {
        List<Map.Entry<String, Integer>> listMatchingNumbers = new ArrayList<>();
        listMatchingNumbers.addAll(mapMatchingNumbers.entrySet());
        List<Map.Entry<String, Integer>> listCards = new ArrayList<>();
        listCards.addAll(cards.entrySet());
        System.out.println(listMatchingNumbers);
        System.out.println(listCards);
        Integer entryValue = 0;
        for (int i = 0; i < listMatchingNumbers.size(); i++) {
            String str = listMatchingNumbers.get(i).toString();
            System.out.println(str);
            String[] splitted = str.split("=");
            Integer value = Integer.parseInt(splitted[1]);
            if (i != 0) {
                for (int times = 0; times < value; times++) {
                    for (int k = i + 1; k <= value + i; k++) {
                        Map.Entry<String, Integer> entry = listCards.get(k);
                        entryValue = entry.getValue();
                        entry.setValue(entryValue + 1);
                        listMatchingNumbers.get(i).setValue(entryValue.intValue());
                    }
                }
            } else {
                for (int k = i + 1; k <= value + i; k++) {
                    Map.Entry<String, Integer> entry = listCards.get(k);
                    entryValue = entry.getValue();
                    entry.setValue(entryValue + 1);
                }
            }
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
