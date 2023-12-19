package Day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day15 {

    private static String file = "src/main/resources/15_input.txt";
    //private static String file = "src/test/resources/15_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day15 starter = new Day15();
        starter.readFile(file);
    }

    private void readFile(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<Integer> results = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(",");
                //System.out.println(splitted.length);
                for (int i = 0; i < splitted.length; i++) {
                    int hashValue = getHashValue(splitted[i]);
                    results.add(hashValue);
                }
            }
            System.out.println(results.stream().mapToInt(i -> i).sum());
        }
    }

    private int getHashValue(String s) {
//        r = 114 * 17 = 1938 / 256 = 146
//        n = 146 + 110= 256 * 17 = 4352 / 256 = 0
//                = = 0 + 61 = 61 * 17 = 1037 / 256 = 13
//        1 = 49 + 13 = 62 * 17 = 1054 / 256 = 30
        int startingValue = 0;
        for (Character c : s.toCharArray()) {
            int charAsAscii = (int) c + startingValue;
            int multipliedAscii = charAsAscii * 17;
            int modulo = multipliedAscii % 256;
            startingValue = modulo;
        }
        return startingValue;
    }
}
