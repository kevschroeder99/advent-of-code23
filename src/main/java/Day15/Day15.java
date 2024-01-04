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
                for (int i = 0; i < splitted.length; i++) {
                    int hashValue = getHashValue(splitted[i]);
                    results.add(hashValue);
                }
            }
            System.out.println(results.stream().mapToInt(i -> i).sum());
        }
    }

    //Part2:
    //256 Boxes, starting with box 0
    //The boxes are arranged in a line starting from the point where light enters the facility.
    //The boxes have holes that allow light to pass from one box to the next all the way down the line.
    //Boxes have lens slots. Lenses can be removed or added
    //Along the wall running parallel to the boxes is a large library containing lenses organized by focal length ranging from 1 through 9



    private int getHashValue(String s) {
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
