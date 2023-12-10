package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day04Part2 {


    private static String file = "src/main/resources/04_input.txt";
    //private static String file = "src/test/resources/04_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day04Part2 starter = new Day04Part2();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        ArrayList<Card> cardsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<String> myCardList;
            List<String> winnerCardList;
            ArrayList<String> allCards = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                allCards.add(line);
            }
            String[] splittedByGame;
            String[] gameDraw;
            String[] myCards;
            String[] winnerCards;
            Integer matchesPerCard = 0;
            for (int i = 0; i < allCards.size(); i++) {
                String cardString = allCards.get(i);
                splittedByGame = cardString.split(":");
                int cardId = Integer.parseInt(splittedByGame[0].replaceAll("[^0-9]", ""));
                gameDraw = splittedByGame[1].split("\\|");
                myCards = gameDraw[0].trim().split("\\s+");
                winnerCards = gameDraw[1].trim().split("\\s+");
                myCardList = Arrays.asList(myCards);
                winnerCardList = Arrays.asList(winnerCards);
                matchesPerCard = getPointsOfGame(myCardList, winnerCardList, false);
                Card card = new Card(cardId, 1, matchesPerCard);
                cardsList.add(card);
            }
            incrementCardValues(cardsList);
            System.out.println(cardsList.stream().map(Card::getCopies).mapToInt(i -> i).sum());
        }
    }

    private void incrementCardValues(ArrayList<Card> cardsList) {
        for (int i = 0; i < cardsList.size(); i++) {
            int matchingNumbers = cardsList.get(i).getMatchingNumbers();
            if (matchingNumbers > 0) {
                for (int j = 0; j < cardsList.get(i).getCopies(); j++) {
                    for (int k = 0; k < matchingNumbers; k++) {
                        Card nextCard = cardsList.get(i + k + 1);
                        nextCard.copies++;
                    }
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
