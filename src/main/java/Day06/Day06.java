package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
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
            //Part#2: replaceAll hinzugefügt
            String time = list.get(0).replaceAll("\\s+", "");
            String distance = list.get(1).replaceAll("\\s+", "");
            //String time = list.get(0);
            //String distance = list.get(1);
            //Part#2: \\s+ ersetzt durch \\D+
            String[] timeArray = time.split("\\D+");
            String[] distanceArray = distance.split("\\D+");

            ArrayList<Integer> winList = new ArrayList<>();
            for (int i = 1; i < timeArray.length; i++) {
                String timeRaceI = timeArray[i];
                String distanceRaceI = distanceArray[i];
                int timeRaceIInteger = Integer.parseInt(timeRaceI);
                Long distanceRaceILong = Long.valueOf(distanceRaceI);
                int wins = getBoatTravelDistance(timeRaceIInteger, distanceRaceILong);
                winList.add(wins);
            }
            System.out.println(winList.stream().reduce(1, (a, b) -> a * b));
        }
    }

    private int getBoatTravelDistance(int time, Long maxDistance) {
        Boat boat = new Boat(0, 0, BigInteger.ZERO);
        int wins = 0;
        for (int i = 0; i < time; i++) {
            boat.setCharge(i);
            BigInteger timeDifference = BigInteger.valueOf(time - boat.getCharge());
            boat.setSpeed(i);
            //Distance is too high
            BigInteger distance = timeDifference.multiply(BigInteger.valueOf(boat.getSpeed()));
            boat.setDistance(distance);
            int compare = distance.compareTo(BigInteger.valueOf(maxDistance));
            if (compare == 1) {
                wins++;
            }
        }
        return wins;
    }

}
