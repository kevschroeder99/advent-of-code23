package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    //private static String file = "src/main/resources/10_input.txt";
    private static String file = "src/test/resources/10_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day10 starter = new Day10();
        starter.readFile(file);
    }

    private void readFile(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);

            }
        }
    }
}
