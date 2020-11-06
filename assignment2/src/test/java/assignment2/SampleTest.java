package assignment2;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;

import scoreannotation.Score;
import testutils.NoExitSecurityManager;
import static testutils.Statics.getStringFromResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class SampleTest {
    private SecurityManager initialSecurityManager = System.getSecurityManager();
    private InputStream stdIn = System.in;
    private PrintStream stdOut = System.out;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @After
    public void teardown() {
        System.setOut(stdOut);
        System.setIn(stdIn);
        System.setSecurityManager(initialSecurityManager);
    }

    private void test(String testName, List<String> codes, GameConfiguration config) {
        /* SETUP */
        // Disallow System.exit() with a SecurityManager
        System.setSecurityManager(new NoExitSecurityManager());

        // Replace stdin
        String input = getStringFromResource(
                getClass(),
                testName + "/input.txt"
        );
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Replace stdout
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Create SecretCodeGenerator mock
        SecretCodeGeneratorMock genMock = new SecretCodeGeneratorMock(codes);

        /* EXECUTE UNIT UNDER TEST */
        Driver.start(false, config, genMock);

        /* VERIFICATION */
        String expected = getStringFromResource(
                getClass(),
                testName + "/expected_output.txt"
        );
        assertEquals(expected.replaceAll("\\s+", " "), output.toString().replaceAll("\\s+"," "));
        assertTrue(genMock.isDone());
    }

    @Test
    @Score(1)
    public void testAccordingToProjectDescription() {
        test(
                "according_to_project_description",
                Arrays.asList("YRBY", "YRBY"),
                new GameConfiguration(12, new String[]{"B","G","O","P","R","Y"}, 4)
        );
    }

    @Test
    @Score(1)
    public void testConfigurationTestFail() {
        test(
                "configuration_test_fail",
                Arrays.asList("QWERTY"),
                new GameConfiguration(4, new String[]{"Q","W","E","R","T","Y"}, 6)
        );
    }

    @Test
    @Score(1)
    public void testOneGuessWin() {
        test(
                "one_guess_win",
                Arrays.asList("OBPY"),
                new GameConfiguration(1, new String[]{"B","G","O","P","R","Y"}, 4)
        );
    }

    @Test
    @Score(1)
    public void testStandardTest() {
        test(
                "standard_test",
                Arrays.asList("BGOP"),
                new GameConfiguration(12, new String[]{"B","G","O","P","R","Y"}, 4)
        );
    }

    @Test
    @Score(1)
    public void testTwoGameHistory() {
        test(
                "two_game_history",
                Arrays.asList("OPGB", "RBBY"),
                new GameConfiguration(12, new String[]{"B","G","O","P","R","Y"}, 4)
        );
    }
}
