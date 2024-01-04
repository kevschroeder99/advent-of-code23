package Day22;

import Day15.Day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day22 {

    //private static String file = "src/main/resources/22_input.txt";
    private static String file = "src/test/resources/22_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day22 starter = new Day22();
        starter.readFile(file);
    }

    private void readFile(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<Integer> results = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
