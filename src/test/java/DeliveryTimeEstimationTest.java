import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTimeEstimationTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    @Test
    void testSampleInput() {
        String input = "100 5\nPKG1 50 30 OFR001\nPKG2 75 125 OFR0008\nPKG3 175 100 OFR003\nPKG4 110 60 OFR002\nPKG5 155 95 NA\n2 70 200\n2 70 200\\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DeliveryTimeEstimation.main(new String[]{});

        String output = outputStream.toString().trim();

        String expectedOutput = String.join("\n",
                "PKG1 0 750 3.98",
"PKG2 0 1475 1.78",
"PKG3 0 2350 1.42",
"PKG4 105 1395 0.85",
"PKG5 0 2125 4.19"
        );
System.out.println("EXPECTED:");
System.out.println(expectedOutput);
System.out.println("ACTUAL:");
System.out.println(output);
        assertEquals(normalize(expectedOutput), normalize(output));
    }
private String normalize(String s) {
    return s.replace("\r\n", "\n").trim();
}

}