package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day05 {

    //private static String file = "src/main/resources/04_input.txt";
    private static String file = "src/test/resources/05_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day05 starter = new Day05();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                //First Line is Seed to be planted

                //Second Line is Seed to soil:
                //the destination range start, the source range start, and the range length
                //50 98 2 -> source range: 50, 51 ; dest. range: 98, 99 = 50 -> 98, 51 -> 99
                //52 50 48 -> source range: 50, 51, ... 96, 97 ; dest. range: 52, 53, ... 98, 99 = 53 -> 55
                //Source Number that is not mapped -> Mapped to same dest. (10 -> 10)

            }
        }
    }

}
