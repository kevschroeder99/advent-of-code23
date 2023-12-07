package Day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

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
            System.out.println(playHandsList.size());
            //Five of a Kind : AAAAA
            //Four of a Kind : AA8AA
            //Full House     : 23332
            //Three of a Kind: TTT98
            //Two of a Kind  : 23432
            //One of a Kind  : A23A4
            //High Card      : 23456
            for (int i = 0; i < playHandsList.size(); i++) {
                ArrayList<String> cards = new ArrayList<>();
                String card = playHandsList.get(i).getHand();
                char[] ch = card.toCharArray();
                for(int k = 1; k < 5; k++){
                    String s = String.valueOf(ch[k]);
                    System.out.println(s);
                }
            }
        }
    }
}
