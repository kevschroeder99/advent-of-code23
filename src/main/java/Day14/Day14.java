package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {

    private static String file = "src/main/resources/14_input.txt";
    //private static String file = "src/test/resources/14_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day14 starter = new Day14();
        starter.readFile(file);
    }

    private void readFile(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<List<Character>> table = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                List<Character> row = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
                table.add(row);
            }
            boolean hasChanged = true;
            while (hasChanged) {
                hasChanged = false;
                for (int i = 1; i < table.size(); i++) {
                    List<Character> charListRow = table.get(i);
                    for (int k = 0; k < charListRow.size(); k++) {
                        if (charListRow.get(k).equals('O')) {
                            //Move up
                            if (isRoundMoveable(table.get(i - 1), k)) {
                                //Aber nur, wenn in Previous Line nicht "O" oder "#" ist.
                                table.get(i - 1).set(k, 'O');
                                //Replace Zero in current Line
                                table.get(i).set(k, '.');
                                hasChanged = true;
                            }
                        }
                    }
                }
            }
            int value = countValues(table);
            System.out.println(value);
        }
    }

    private int countValues(List<List<Character>> table) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            List<Character> row = table.get(i);
            long count = row.stream().filter(c -> c.equals('O')).count();
            results.add(Long.valueOf(count).intValue() * (table.size() - i));
        }
        return results.stream().mapToInt(i -> i).sum();
    }

    private boolean isRoundMoveable(List<Character> characters, int k) {
        if (characters.get(k).equals('O') || characters.get(k).equals('#')) {
            return false;
        }
        return true;
    }
}
