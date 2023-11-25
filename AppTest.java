import io.IO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AppTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void run() throws FileNotFoundException {
        
        // get input from file input.txt
        File infile = new File("input.txt");
        Scanner scanner = new Scanner(infile);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(os);
        
        App app = new App(scanner, printStream);
        app.run();
        
        // get expected output from file output.txt
        File ans = new File("output.txt");
        Scanner ansScanner = new Scanner(ans);
        String expectedOutput = "";
        StringBuilder stringBuilder = new StringBuilder();
        while(ansScanner.hasNext()) {
            stringBuilder.append("\n");
            stringBuilder.append(ansScanner.nextLine());
        }
        stringBuilder.append("\n"); // the extra command prompt
        expectedOutput = stringBuilder.toString();
        
        String currentOutput = os.toString(StandardCharsets.UTF_8);

        assertEquals(expectedOutput, currentOutput);
    }
}