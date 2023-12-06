package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day04Part2 {


    //private static String file = "src/main/resources/04_input.txt";
    private static String file = "src/test/resources/04_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day04Part2 starter = new Day04Part2();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        HashMap<Integer, Integer> cards;
        HashMap<Integer, Integer> mapMatchingNumbers;
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
                cards.put(Integer.parseInt(splittedByGame[0].replaceAll("[^0-9]", "")), 1);

                gameDraw = splittedByGame[1].split("\\|");
                myCards = gameDraw[0].trim().split("\\s+");
                winnerCards = gameDraw[1].trim().split("\\s+");
                myCardList = Arrays.asList(myCards);
                winnerCardList = Arrays.asList(winnerCards);
                //Matching Numbers ermitteln.
                matchesPerCard = getPointsOfGame(myCardList, winnerCardList, false);
                mapMatchingNumbers.put(Integer.parseInt(splittedByGame[0].replaceAll("[^0-9]", "")), matchesPerCard);
                //mapMatchingNumbers.put(splittedByGame[0], matchesPerCard);
            }


        }
        addCardValues(mapMatchingNumbers, cards);
        //mapMatchingNumbers.forEach((key, value) -> System.out.println("Key: " + key + " --- Value: " + value));
       //System.out.println("#####");
        //ErgebnisListe:
        //values in Zielliste erhöhen


        System.out.println("#####");
        cards.forEach((key, value) -> System.out.println("Key: " + key + " ---- Value: " + value));


    }


    private void addCardValues(HashMap<Integer, Integer> mapMatchingNumbers, HashMap<Integer, Integer> cards) {
        ArrayList<Integer> arrayNumbersKeys = mapMatchingNumbers.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("ArrayNumberKeys: " + arrayNumbersKeys);
        ArrayList<Integer> arrayNumbersValue = mapMatchingNumbers.values().stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("ArrayNumbersValue: " + arrayNumbersValue);

        ArrayList<Integer> arrayCardsKeys = cards.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("arrayCardsKeys: " + arrayCardsKeys);
        ArrayList<Integer> arrayCardsValues = cards.values().stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("arrayCardsValues: " + arrayCardsValues);


        for (int i = 0; i < arrayNumbersKeys.size(); i++) {
            Integer card = arrayNumbersKeys.get(i);
            Integer value = arrayNumbersValue.get(i);
            System.out.println("Card : " + card);
            System.out.println("Wins : " + value);
            System.out.println("Cards Value: " + cards.get(value));

            //Card 2 -> 2
            if (value > 0) {
                for (int k = 1 + i; k < value + 1 && k < cards.size(); k++) {
                    Integer valueCards = arrayCardsValues.get(k);
                    Integer keyCards = arrayCardsKeys.get(k);
                    cards.put(keyCards, cards.get(keyCards) + 1);
                    arrayCardsValues.add(cards.get(keyCards));

                    System.out.println("Key Cards: " + keyCards + "Value Cards: " + valueCards);
                }
            }
            System.out.println("Modified List: " + arrayCardsValues);

        }


//        HashMap<Integer, Integer> result = new HashMap<>();
//        //Get Key and Value of mapMatchingNumbers
//        int counter = 1;
//        for(Map.Entry<Integer, Integer> entry : mapMatchingNumbers.entrySet()){
//            Integer key = entry.getKey();
//            Integer value = entry.getValue() ;
//            for (int i = counter + 1; i <= value + counter; i++){
//                //i = 1, value = 4
//                cards.put(i, cards.get(i) + 1);
//
//            }
//            counter++;
//        }

        //Iterate in cards over elements and add value
        //Set Card Values in map Matching Numbers
        //Do Again until cards.length erreicht ist.
    }


//    private void addCardValues(HashMap<String, Integer> mapMatchingNumbers, HashMap<String, Integer> cards) {
//        List<Map.Entry<String, Integer>> listMatchingNumbers = new ArrayList<>();
//        listMatchingNumbers.addAll(mapMatchingNumbers.entrySet());
//        List<Map.Entry<String, Integer>> listCards = new ArrayList<>();
//        listCards.addAll(cards.entrySet());
//        System.out.println(listMatchingNumbers);
//        System.out.println(listCards);
//        Integer entryValue = 0;
//        for (int i = 0; i < listMatchingNumbers.size(); i++) {
//            String str = listMatchingNumbers.get(i).toString();
//            System.out.println(str);
//            String[] splitted = str.split("=");
//            Integer value = Integer.parseInt(splitted[1]);
//            if (i != 0) {
//                for (int times = 0; times < value; times++) {
//                    for (int k = i + 1; k <= value + i; k++) {
//                        Map.Entry<String, Integer> entry = listCards.get(k);
//                        entryValue = entry.getValue();
//                        entry.setValue(entryValue + 1);
//                        listMatchingNumbers.get(i).setValue(entryValue.intValue());
//                    }
//                }
//            } else {
//                for (int k = i + 1; k <= value + i; k++) {
//                    Map.Entry<String, Integer> entry = listCards.get(k);
//                    entryValue = entry.getValue();
//                    entry.setValue(entryValue + 1);
//                }
//            }
//        }
//    }


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
