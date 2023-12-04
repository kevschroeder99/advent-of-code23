import Day02.Day02;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day02Test {

    private static String file = "src/test/resources/02_input_test.txt";

    @Test
    public void test() throws IOException {
        Day02 day02 = new Day02();
        day02.execute(file);
    }
}
