package Day06;

import Day05.Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day06 {

    //private static String file = "src/main/resources/04_input.txt";
    private static String file = "src/test/resources/06_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day06 starter = new Day06();
        starter.doPart1(file);


        //Starting Speed of Boat 0mm/ms
            //For each whole ms after race Starts -> speed increses by + 1mm/ms


        //First race lasts 07msecs:
            //hold button for 0msecs -> 0mm travelled
            //hold button for 1msecs -> 7msecs - 1msecs -> speed: 1mm/ms for 6ms -> 1mm/ms * 6ms = 6mm travelled
            //hold button for 2msecs -> 7 - 2 -> speed: 2mm/ms for 5ms -> 2mm/ms * 5ns = 10mm travelled
            //hold button for 3msecs -> 7 - 3 -> speed: 3mm/ms for 4ms -> 12mm travelled
            //hold button for 4msecs -> 7 - 4 -> speed: 4mm/ms for 3ms -> 12mm travelled
            //hold button for 5msecs -> 7 - 5 -> speed: 5mm/ms for 2ms -> 10mm travelled
            //hold button for 6msecs -> 7 - 6 -> speed: 6mm/ms for 1ms ->  6mm travelled
            //hold Button for 7msecs -> 7 - 7 -> speed: 7mm/ms for 0ms ->  0mm travelled (time over)

        //Current Record for 07msecs = 9mm
        //4 different ways to win: 2ms, 3ms, 4ms, 5ms

        //result: multiple all ways to win: 4 * 8 (second race) * 9 (thrid race) = 288
    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                //First Race 07msecs & Distance 09mm
                //Second Race 15msecs & Distance 40mm
                //Third Race 30msecs & Distance 200mm
                Integer[] timeArray;

            }
        }
    }

}
