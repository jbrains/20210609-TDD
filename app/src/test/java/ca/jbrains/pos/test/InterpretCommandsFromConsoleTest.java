package ca.jbrains.pos.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class InterpretCommandsFromConsoleTest {

    private Controller controller = Mockito.mock(Controller.class);

    @Test
    void oneCommand() throws Exception {
        interpretCommands(controller, new StringReader("12345"));

        Mockito.verify(controller).onCommand("12345");
    }

    @Test
    void zeroCommands() throws Exception {
        interpretCommands(controller, new StringReader(""));

        Mockito.verifyNoInteractions(controller);
    }

    private void interpretCommands(final Controller controller, final StringReader commandSource) throws IOException {
        final String commandText = new BufferedReader(commandSource).readLine();
        if (commandText != null)
            controller.onCommand(commandText);
    }
}
