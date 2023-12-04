import Day01.Day01;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    private static String file = "src/test/resources/01_input_test.txt";

    @Test
    public void test() throws IOException {
        Day01 starter = new Day01();
        ArrayList<Integer> digitList = starter.execute(file);
        assertEquals(281, starter.sum(digitList));
    }

}
