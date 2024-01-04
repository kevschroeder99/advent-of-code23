package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
            List<String> inputList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty()){
                    inputList.add(line);
                }
            }
            String seeds = inputList.get(0);


            //Second Line is Seed to soil:
            //the destination range start, the source range start, and the range length
            //50 98 2 -> source range: 50, 51 ; dest. range: 98, 99 = 50 -> 98, 51 -> 99
            //52 50 48 -> source range: 50, 51, ... 96, 97 ; dest. range: 52, 53, ... 98, 99 = 53 -> 55
            //Source Number that is not mapped -> Mapped to same dest. (10 -> 10)
            //seed  soil
            //0     0
            //1     1
            //...   ...
            //48    48
            //49    49
            //50    52
            //51    53
            //...   ...
            //96    98
            //97    99
            //98    50
            //99    51
            System.out.println(inputList);
            System.out.println(seeds);
        }
    }

}
