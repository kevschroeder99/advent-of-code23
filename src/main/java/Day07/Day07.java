package Day07;

import java.io.BufferedReader;
import java.io.FileReader;

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
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
