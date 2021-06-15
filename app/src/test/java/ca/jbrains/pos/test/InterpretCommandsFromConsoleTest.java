package ca.jbrains.pos.test;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

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

    @Test
    void severalCommandsWithNoTrailingLineSeparator() throws Exception {
        interpretCommands(controller, new StringReader("::command 1::\n::command 2::\n::command 3::"));

        final InOrder checkCommandOrder = Mockito.inOrder(controller);
        Arrays.asList("::command 1::", "::command 2::", "::command 3::")
                .stream().forEachOrdered(checkCommandOrder.verify(controller)::onCommand);
    }

    private void interpretCommands(final Controller controller, final StringReader commandSource) throws IOException {
        new BufferedReader(commandSource).lines().forEachOrdered(controller::onCommand);
    }
}
