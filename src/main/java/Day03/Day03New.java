package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
            List<List<Character>> linesAsList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                List<Character> lineSplitted = createLineSplitted(line);
                linesAsList.add(lineSplitted);
            }
            int partsNumbers = getPartsNumbers(linesAsList);
        }
    }

    private int getPartsNumbers(List<List<Character>> linesAsList) {
        //get index of numbers.
        List<List<Integer>> numbersIndex = getNumbersIndex(linesAsList);
        List<List<Integer>> symbolsIndex = getSymbolsIndex(linesAsList);
        List<Integer> partsNumbers = new ArrayList<>();
        for (int i = 0; i < linesAsList.size()-1; i++) {
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
            if(numbersLine.contains(i)){
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
