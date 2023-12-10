package Day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day07 {

    //private static String file = "src/main/resources/07_input.txt";
    private static String file = "src/test/resources/07_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day07 starter = new Day07();
        starter.doPart1(file);
    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            ArrayList<PlayHand> playHandsList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                PlayHand playHand = new PlayHand("", 0, 0);
                String[] splitHand = line.split(" ");
                playHand.setHand(splitHand[0]);
                playHand.setBid(Integer.parseInt(splitHand[1]));
                playHandsList.add(playHand);
            }
            for (int i = 0; i < playHandsList.size(); i++) {
                String card = playHandsList.get(i).getHand();
                Map<String, Long> occurance = new HashMap<>();
                for (int k = 0; k < 5; k++) {
                    occurance = Arrays.stream(card.split("")).collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
                }
                System.out.println(occurance);
                if (occurance.size() == 1) {
                    playHandsList.get(i).setRank(playHandsList.size());
                } else if (occurance.size() == 5) {
                    playHandsList.get(i).setRank(1);
                } else {
                    if (occurance.size() == 4) {
                        playHandsList.get(i).setRank(2);
                    } else if (occurance.size() == 2) {
                        setRankFor2Elements(occurance, playHandsList.get(i), playHandsList.size());
                    } else {
                        setRankFor3Elements(occurance, playHandsList.get(i), playHandsList.size());
                    }
                }
                System.out.println("Rank: " + playHandsList.get(i).getRank());
            }

            for (int i = 1; i <= playHandsList.size(); i++) {
                restructurePlayHand(i, playHandsList);
            }

            //Map 1 Element                   -> Highest (Set Rank = playHandsList.size)
            //Map 2 Elemente && ein Value = 4 -> Highest - 1
            //Map 2 Elemente && ein Value = 3 -> Highest - 2

            //Map 3 Elemente && ein Value = 3 -> Highest - 3 (Set Rank = playHandsList.size / 2 )
            //Map 3 Elemente && ein Value = 2 -> Highest - 4
            //Map 4 Elemente                  -> Highest - 5
            //Map hat 5 Elemente -> High Card -> Highest - 6 (Lowest) (Set Rank = 0)

            //Alle highest sind auf dem höchsten
            //Alle lowest sind auf dem niedrigsten
            //Alle anderen sind in der Mitte
            //System.out.println(playHandsList.get(5).getRank());
        }
    }

    private void restructurePlayHand(int i, ArrayList<PlayHand> playHandsList) {
        //Filtern nach Rank. Zu jedem doppelten Rank müssen die Karten verglichen werden.
        ArrayList<PlayHand> filtered = playHandsList.stream().filter(obj -> obj.getRank() == i).collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() != 1) {
            rerankPlayhand(i,filtered, playHandsList);
        }
    }

    private void rerankPlayhand(int k, ArrayList<PlayHand> filtered, ArrayList<PlayHand> playHandsList) {
        if (filtered.size() == 2) {
            char[] hand1 = filtered.get(0).getHand().toCharArray();
            char[] hand2 = filtered.get(1).getHand().toCharArray();
            for (int i = 0; i < hand1.length; i++) {
                if (hand1[i] < hand2[i]) {
                    playHandsList.set(i,filtered.get(1));
                    break;
                } else if (hand1[i] > hand2[i]) {

                    break;
                }
            }
        }
    }

    private void setRankFor3Elements(Map<String, Long> occurance, PlayHand playHand, int size) {
        //Map 3 Elemente && ein Value = 3 -> Highest - 3 (Set Rank = playHandsList.size / 2 )
        //Map 3 Elemente && ein Value = 2 -> Highest - 4
        if (occurance.values().stream().filter(v -> v == 3).findAny().isPresent()) {
            playHand.setRank(size / 2);
        } else {
            playHand.setRank((size / 2) - 1);
        }
    }

    private void setRankFor2Elements(Map<String, Long> occurance, PlayHand playHand, int size) {
        //Map 2 Elemente && ein Value = 4 -> Highest - 1
        //Map 2 Elemente && ein Value = 3 -> Highest - 2
        if (occurance.values().stream().filter(v -> v == 4).findAny().isPresent()) {
            playHand.setRank(size - 1);
        } else {
            playHand.setRank(size - 2);
        }
    }

    //Five of a Kind : AAAAA
    //Four of a Kind : AA8AA
    //Full House     : 23332
    //Three of a Kind: TTT98
    //Two of a Kind  : 23432
    //One of a Kind  : A23A4
    //High Card      : 23456
}
