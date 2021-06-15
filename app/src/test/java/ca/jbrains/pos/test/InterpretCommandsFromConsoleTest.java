package ca.jbrains.pos.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class InterpretCommandsFromConsoleTest {
    @Test
    void oneCommand() throws Exception {
        final Controller controller = Mockito.mock(Controller.class);

        interpretCommands(controller, new StringReader("12345"));

        Mockito.verify(controller).onCommand("12345");
    }

    private void interpretCommands(final Controller controller, final StringReader commandSource) throws IOException {
        controller.onCommand(new BufferedReader(commandSource).readLine());
    }
}
