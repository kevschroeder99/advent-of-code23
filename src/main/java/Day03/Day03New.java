package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day03New {

    //private static String file = "src/main/resources/03_input.txt";
    private static String file = "src/test/resources/03_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day03New starter = new Day03New();
        starter.doPart1(file);

    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            List<List<Character>> table = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                List<Character> row = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
                table.add(row);
            }
            List<Integer> partsList = new ArrayList<>();
            for (int i = 0; i < table.size(); i++) {
                List<Character> charListRow = table.get(i);
                List<Character> possiblePart = new ArrayList<>();
                for (int k = 0; k < charListRow.size(); k++) {
                    Character character = charListRow.get(k);

                    boolean isSymbol = false;

                    if (isPartNumber(character)) {
                        possiblePart.add(character);
                    }

                    if (isCharSymbol(character)) {
                        int part = getPartNumber(possiblePart);
                        partsList.add(part);
                        //Replace Numbers to Dots
                        //replaceNumberWithDots(charListRow, possiblePart);
                        //Liste wird zurückgesetzt
                        possiblePart.clear();
                    } else {
                        if (i == 0) {
                            isSymbol = checkUpperList(table.get(i + 1), k);
                        } else if (i == table.size() - 1) {
                            isSymbol = checkLowerList(table.get(i - 1), k);
                        } else {
                            isSymbol = checkUpperandLowerList(table.get(i + 1), table.get(i - 1), k);
                        }
                    }

                    if (isSymbol) {
                        if (isNextCharNumber(charListRow, k)) {
                            possiblePart.add(charListRow.get(k + 1));
                            if (isNextCharNumber(charListRow, k + 1)) {
                                possiblePart.add(charListRow.get(k + 2));
                                if (isNextCharNumber(charListRow, k + 2)) {
                                    possiblePart.add(charListRow.get(k + 3));
                                }
                            }
                        }
                        int part = getPartNumber(possiblePart);
                        partsList.add(part);
                        possiblePart.clear();
                    } else if (isCharDot(character)) {
                        possiblePart.clear();
                    }
                }
            }
            System.out.println(partsList);
            System.out.println(partsList.stream().mapToInt(i->i).sum());

        }

    }

    private boolean isNextCharNumber(List<Character> charListRow, int k) {
        if (charListRow.get(k) != null && isIndexADigit(charListRow.get(k + 1))) {
            return true;
        }
        return false;
    }

    private boolean checkUpperandLowerList(List<Character> characters, List<Character> characters1, int index) {
        if (isCharSymbol(characters.get(index)) || isCharSymbol(characters1.get(index))) {
            return true;
        }
        return false;
    }

    private boolean checkLowerList(List<Character> characters, int index) {
        if (isCharSymbol(characters.get(index))) {
            return true;
        }
        return false;
    }

    private boolean checkUpperList(List<Character> characters, int index) {
        if (isCharSymbol(characters.get(index))) {
            return true;
        }
        return false;
    }

    private void replaceNumberWithDots(List<Character> charListRow, List<Character> possiblePart) {

    }

    private boolean checkLowerLine(List<Character> charsLower, int currentIndex) {
        //Wenn Auf gleicher Höhe oder +/-1 auch ein Symbol ist, dann true
        if (isCharSymbol(charsLower.get(currentIndex))) {
            return true;
        }
        if (currentIndex > 0 && (isCharSymbol(charsLower.get(currentIndex - 1)))) {
            return true;
        }
//        if (isCharSymbol(charsLower.get(currentIndex + 1))) {
//            return true;
//        }
        return false;
    }

    private boolean checkUpperAndLowerLine(List<Character> charsUpper, List<Character> charsLower, int currentIndex) {
        //Wenn Auf gleicher Höhe oder +/-1 auch ein Symbol ist, dann true
        if (isCharSymbol(charsUpper.get(currentIndex)) || isCharSymbol(charsLower.get(currentIndex))) {
            return true;
        }
        if (currentIndex > 0 && (isCharSymbol(charsUpper.get(currentIndex - 1)) || isCharSymbol(charsLower.get(currentIndex - 1)))) {
            return true;
        }
//        if (isCharSymbol(charsUpper.get(currentIndex + 1)) || isCharSymbol(charsLower.get(currentIndex + 1))) {
//            return true;
//        }
        return false;
    }

    private int getPartNumber(List<Character> possiblePart) {
        StringBuilder sb = new StringBuilder();
        if (possiblePart.size() != 0) {
            for (Character c : possiblePart) {
                sb.append(c);
            }
        } else {
            sb.append('0');
        }
        return Integer.parseInt(sb.toString());
    }

    private boolean isCharDot(Character character) {
        return character.equals('.');
    }

    private boolean isCharSymbol(Character character) {
        ArrayList<Character> symbolicChars = new ArrayList<>();
        symbolicChars.add('$');
        symbolicChars.add('*');
        symbolicChars.add('#');
        symbolicChars.add('+');
        symbolicChars.add('=');
        symbolicChars.add('/');
        symbolicChars.add('@');
        symbolicChars.add('&');
        symbolicChars.add('%');
        symbolicChars.add('-');
        if (symbolicChars.contains(character)) {
            return true;
        }
        return false;
    }

    private boolean isPartNumber(Character character) {
        String regex = "\\d+";
        Character c = character;
        return c.toString().matches(regex);
    }

    private int getPartsNumbers(List<List<Character>> linesAsList) {
        //get index of numbers.
        List<List<Integer>> numbersIndex = getNumbersIndex(linesAsList);
        List<List<Integer>> symbolsIndex = getSymbolsIndex(linesAsList);
        List<Integer> partsNumbers = new ArrayList<>();
        for (int i = 0; i < linesAsList.size() - 1; i++) {
            List<Character> line = linesAsList.get(i);
            List<Integer> numbersLine = numbersIndex.get(i);
            List<Integer> symbolsLine = symbolsIndex.get(i);
            List<Integer> nextSymbolList = symbolsIndex.get(i + 1);
            //System.out.println("Line: " + line);
            System.out.println(numbersLine);
            System.out.println(symbolsLine);

            //List<Integer> nextSymbolLine = symbolsIndex.get(i + 1);
            //For Loop für Character
            if (isPartNumberInLine(numbersLine, symbolsLine)) {
                //Wenn ja, dann number adden + removen
            } else if (isPartNumberFromNextLine(numbersLine, nextSymbolList)) {
                //Wenn ja, dann number adden + removen
            }


        }
        System.out.println(linesAsList);

        // System.out.println(numbersIndex);

        //System.out.println(symbolsIndex);

        return 0;
    }

    private boolean isPartNumberFromNextLine(List<Integer> numbersLine, List<Integer> nextSymbolLine) {
        for (Integer i : nextSymbolLine) {
            if (numbersLine.contains(i)) {
                System.out.println("Gleiche Höhe");
            } else if (numbersLine.contains(i + 1)) {
                System.out.println("Rechtsdiagonal");
            } else if (numbersLine.contains(i - 1)) {
                System.out.println("Linksdiagonal");

            }
        }
        return false;
    }

    private boolean isPartNumberInLine(List<Integer> numbersLine, List<Integer> symbolsLine) {
        for (Integer i : symbolsLine) {
            if (numbersLine.contains(i + 1)) {
                //return true;
            } else if (numbersLine.contains(i - 1)) {
                //return true;
            }
        }
        return false;
    }

    private List<List<Integer>> getSymbolsIndex(List<List<Character>> linesAsList) {
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
        List<List<Integer>> symbolsIndex = new ArrayList<>();
        for (List<Character> charList : linesAsList) {
            List<Integer> symbolsInLine = new ArrayList<>();
            for (int i = 0; i < charList.size(); i++) {
                if (symbolicChars.contains(charList.get(i))) {
                    symbolsInLine.add(i);
                }
            }
            symbolsIndex.add(symbolsInLine);
        }
        return symbolsIndex;
    }

    private List<List<Integer>> getNumbersIndex(List<List<Character>> linesAsList) {
        List<List<Integer>> numbersIndex = new ArrayList<>();
        for (List<Character> charList : linesAsList) {
            List<Integer> numbersInLine = new ArrayList<>();
            for (int i = 0; i < charList.size(); i++) {
                if (isIndexADigit(charList.get(i))) {
                    numbersInLine.add(i);
                }
            }
            numbersIndex.add(numbersInLine);
        }
        return numbersIndex;
    }

    private boolean isIndexADigit(Character character) {
        return character.toString().matches("\\d+");
    }

    private List<Character> createLineSplitted(String line) {
        List<Character> lineSplitted = new ArrayList<>();
        for (Character c : line.toCharArray()) {
            lineSplitted.add(c);
        }

        return lineSplitted;
    }
}
