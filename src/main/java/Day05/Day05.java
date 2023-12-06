package Day05;

import java.io.BufferedReader;
import java.io.FileReader;

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
                //First Line is Seed
                //Second Line is Soil
            }
        }
    }

}
