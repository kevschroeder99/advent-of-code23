package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Day06 {

    private static String file = "src/main/resources/06_input.txt";
    //private static String file = "src/test/resources/06_input_test.txt";

    public static void main(String[] args) throws Exception {
        Day06 starter = new Day06();
        starter.doPart1(file);
    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            ArrayList<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                list.addAll(Arrays.asList(line));
            }
            String time = list.get(0);
            String distance = list.get(1);
            String[] timeArray = time.split("\\s+");
            String[] distanceArray = distance.split("\\s+");

            ArrayList<Integer> winList = new ArrayList<>();
            for (int i = 1; i < timeArray.length; i++) {
                String timeRaceI = timeArray[i];
                String distanceRaceI = distanceArray[i];
                int wins = getBoatTravelDistance(Integer.parseInt(timeRaceI), Integer.parseInt(distanceRaceI));
                winList.add(wins);
            }
            System.out.println(winList.stream().reduce(1, (a, b) -> a * b));
        }
    }

    //hold button for 0msecs -> 0mm travelled
    //hold button for 1msecs -> 7msecs - 1msecs -> speed: 1mm/ms for 6ms -> 1mm/ms * 6ms = 6mm travelled
    //hold button for 2msecs -> 7 - 2 -> speed: 2mm/ms for 5ms -> 2mm/ms * 5ns = 10mm travelled
    //hold button for 3msecs -> 7 - 3 -> speed: 3mm/ms for 4ms -> 12mm travelled
    //hold button for 4msecs -> 7 - 4 -> speed: 4mm/ms for 3ms -> 12mm travelled
    //hold button for 5msecs -> 7 - 5 -> speed: 5mm/ms for 2ms -> 10mm travelled
    //hold button for 6msecs -> 7 - 6 -> speed: 6mm/ms for 1ms ->  6mm travelled
    //hold Button for 7msecs -> 7 - 7 -> speed: 7mm/ms for 0ms ->  0mm travelled (time over)
    private int getBoatTravelDistance(int time, int maxDistance) { //7     //9
        Boat boat = new Boat(0, 0, 0);
        int wins = 0;
        for (int i = 0; i < time; i++) {
            boat.setCharge(i);
            int timeDifference = time - boat.getCharge();
            boat.setSpeed(i);
            boat.setDistance(boat.getSpeed() * timeDifference);
            if (boat.getDistance() > maxDistance) {
                wins++;
            }
        }
        return wins;
    }

}
