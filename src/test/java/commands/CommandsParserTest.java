package commands;

import exceptions.RyanGoslingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the CommandsParser class.
 */
public class CommandsParserTest {

    /**
     * Test for the "bye" command.
     *
     * @throws RyanGoslingException if an error occurs during parsing
     */
    @Test
    public void byeTest() throws RyanGoslingException {
        assertEquals(1, new CommandsParserStub().parseCommandsOriginal("bye"));
    }

    /**
     * Test for the "todo" command.
     *
     * @throws RyanGoslingException if an error occurs during parsing
     */
    @Test
    public void todoTest() throws RyanGoslingException {
        assertEquals("todo drive", new CommandsParserStub().parseCommandsReturnString("todo drive"));

        assertEquals("todo LOL", new CommandsParserStub().parseCommandsReturnString("todo LOL"));

        try {
            assertEquals("", new CommandsParserStub().parseCommandsReturnString("todo"));
            fail();
        } catch (RyanGoslingException e) {
            assertEquals("Incomplete todo command, todo <event>", e.getMessage());
        }
    }
}
