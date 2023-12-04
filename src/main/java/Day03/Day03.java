package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day03 {

    private static String file = "src/main/resources/03_input.txt";
    //private static String file = "src/test/resources/03_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day03 starter = new Day03();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        ArrayList<Character> symbolicChars = new ArrayList<>();
        symbolicChars.add('$');
        symbolicChars.add('*');
        symbolicChars.add('#');
        symbolicChars.add('+');
        symbolicChars.add('=');
        symbolicChars.add('/');
        symbolicChars.add('@');
        symbolicChars.add('%');
        symbolicChars.add('-');
        try (BufferedReader br = new BufferedReader(fileReader)) {
            char[][] charArray = get2DCharArray(br);
            ArrayList<String> numbers = new ArrayList<>();
            for (int i = 0; i < charArray.length; i++) {
                String number;
                for (int k = 0; k < charArray.length; k++) {
                    Character current = charArray[i][k];
                    if (symbolicChars.contains(current)) {
                        //System.out.println("Row: " + i + " ---- Spalte: " + k);


                        if (isCharacterNumber(charArray[i][k - 1])) {
                            numbers.add(getNumberFromCharArray(i, k - 1, charArray));
                        } else {
                            //schauen, ob in der Zeile drüber Zahl ist.
                            if (isCharacterNumber(charArray[i - 1][k - 1])) {
                                numbers.add(getNumberFromCharArray(i - 1, k - 1, charArray));
                            }
                            //schaun, ob in der Zeile drunter Zahl ist.
                            if (isCharacterNumber(charArray[i + 1][k - 1])) {
                                numbers.add(getNumberFromCharArray(i + 1, k - 1, charArray));
                            }
                        }
                        if (isCharacterNumber(charArray[i][k + 1])) {
                            //schauen, ob in der Zeile drüber oder drunter eine Zahl ist.
                            numbers.add(getNumberFromCharArray(i, k + 1, charArray));
                        } else {

                            //schauen, ob in der Zeile drüber Zahl ist.
                            if (isCharacterNumber(charArray[i - 1][k + 1])) {
                                numbers.add(getNumberFromCharArray(i - 1, k + 1, charArray));
                            }
                            //schaun, ob in der Zeile drunter Zahl ist.
                            if (isCharacterNumber(charArray[i + 1][k + 1])) {
                                numbers.add(getNumberFromCharArray(i + 1, k + 1, charArray));
                            }
                        }
                    }
                }
            }
            System.out.println(numbers);
            System.out.println(numbers.stream().mapToInt(i -> Integer.parseInt(i)).sum());
            Set<String> set = new HashSet<>(numbers);
            numbers.clear();
            numbers.addAll(set);
            System.out.println(numbers.stream().mapToInt(i -> Integer.parseInt(i)).sum());

        }
    }


    private String getNumberFromCharArray(int i, int k, char[][] charArray) {
        String number = "";
        Character charAtPoint = charArray[i][k];
        number = charAtPoint.toString();
        Character charPlus1 = charArray[i][k - 1];
        if (charPlus1.toString().matches("[0-9]")) {
            number = charPlus1.toString() + charAtPoint.toString();
            Character charPlus2 = charArray[i][k - 2];
            if (charPlus2.toString().matches("[0-9]")) {
                number = charPlus2.toString() + charPlus1 + charAtPoint;
            }else {
                Character charPlus6 = charArray[i][k+1];
                if(charPlus6.toString().matches("[0-9]")){
                    number = charPlus1.toString() + charAtPoint.toString() + charPlus6;
                }
            }
        } else {
            number = charAtPoint.toString();
            Character charPlus3 = charArray[i][k + 1];
            if (charPlus3.toString().matches("[0-9]")) {
                number = charAtPoint.toString() + charPlus3.toString();
                Character charPlus2 = charArray[i][k + 2];
                if (charPlus2.toString().matches("[0-9]")) {
                    number = charAtPoint.toString() + charPlus3.toString() + charPlus2.toString();
//                    Character charPlus6 = charArray[i][k + 3];
//                    if(charPlus6.toString().matches("[0-9]")){
//                        number = charAtPoint.toString() + charPlus3.toString() + charPlus2.toString() + charPlus6.toString();
//                    }
                }
            }
        }

        return number;
    }

    private boolean isCharacterNumber(char c) {
        String regex = "\\d+";
        Character character = c;
        return character.toString().matches(regex);
    }

    private char[][] get2DCharArray(BufferedReader br) throws Exception {
        String line;
        int numRows = 0;
        int numColumns = 0;

        while ((line = br.readLine()) != null) {
            numRows++;
            if (line.length() > numColumns) {
                numColumns = line.length();
            }
        }
        br.close();
        br = new BufferedReader(new FileReader("src/main/resources/03_input.txt"));
        char[][] charArray = new char[numRows][numColumns];
        int row = 0;
        while ((line = br.readLine()) != null) {
            char[] lineChars = line.toCharArray();
            System.arraycopy(lineChars, 0, charArray[row], 0, lineChars.length);
            row++;
        }
        return charArray;
    }

    private static void display2DCharArray(char[][] charArray) {
        for (char[] row : charArray) {
            for (char value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private ArrayList<Integer> getIndexOfNumbers(String line) {
        //jede Zeile hat eine fixe Länge
        //get first and last index of number
        ArrayList<Integer> indexList = new ArrayList<>();
        int firstIndex;
        int lastIndex;
        for (int i = 0; i < line.length(); i++) {
            Character previousChar = null;
            Character nextChar = null;
            Character currentChar = line.charAt(i);
            if (i > 0) {
                previousChar = line.charAt(i - 1);
            }
            if (i < line.length() - 1) {
                nextChar = line.charAt(i + 1);
            }

            if (currentChar.toString().matches("\\d")
                    && (i == 0 || previousChar.toString().matches("\\D"))) {
                firstIndex = i;
                indexList.add(firstIndex);

            }
            if (currentChar.toString().matches("\\d")
                    && (i == line.length() || nextChar.toString().matches("\\D"))) {
                lastIndex = i;
                indexList.add(lastIndex);
            }
        }
        //System.out.println(indexList);
        return indexList;
    }
}
