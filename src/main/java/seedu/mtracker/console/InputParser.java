package seedu.mtracker.console;

import seedu.mtracker.LogHelper;
import seedu.mtracker.commands.AddInstrumentCommand;
import seedu.mtracker.commands.Command;
import seedu.mtracker.commands.DeleteCommand;
import seedu.mtracker.commands.ExitCommand;
import seedu.mtracker.commands.ListCommand;
import seedu.mtracker.error.InvalidCommandError;
import seedu.mtracker.error.InvalidIndexError;
import seedu.mtracker.error.InvalidInstrumentError;
import seedu.mtracker.error.InvalidNoIndexError;
import seedu.mtracker.ui.TextUi;

import java.util.Scanner;
import java.util.logging.Logger;

public class InputParser {

    public static final String SEPARATOR = " ";

    public static final String POSITIVE_SENTIMENT = "positive";
    public static final String NEUTRAL_SENTIMENT = "neutral";
    public static final String NEGATIVE_SENTIMENT = "negative";

    public static final int INDEX_OFFSET = 1;
    public static final int INSTRUMENT_INDEX = 1;

    protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final int MAIN_COMMAND_INDEX = 0;

    protected static Scanner inputScanner;

    private int instrumentNumber;

    public InputParser() {
        inputScanner = new Scanner(System.in);
        instrumentNumber = -1;
    }

    public static String getUserInput() {
        TextUi.displayPrompter();
        return inputScanner.nextLine().trim();
    }

    public AddInstrumentCommand getAddInstrumentParameters() throws InvalidInstrumentError {
        TextUi.displayAddInstrumentFirstInstruction();
        String addInstrumentType = getUserInput();
        return AddInstrumentParser.filterByInstrumentType(getCommandComponents(addInstrumentType));
    }

    public DeleteCommand getDeleteInstrumentIndex(String[] commandComponents) throws InvalidIndexError,
            InvalidNoIndexError {
        DeleteCommand deleteCommand = new DeleteCommand();
        getIndexNumber(commandComponents);
        deleteCommand.setIndex(instrumentNumber);
        return deleteCommand;
    }

    public Command filterByCommandType(String[] commandComponents) throws Exception {
        Command command;
        switch (commandComponents[MAIN_COMMAND_INDEX]) {
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case AddInstrumentCommand.COMMAND_WORD:
            command = getAddInstrumentParameters();
            break;
        case DeleteCommand.COMMAND_WORD:
            command = getDeleteInstrumentIndex(commandComponents);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        default:
            logger.info(LogHelper.LOG_INVALID_COMMAND);
            throw new InvalidCommandError();
        }
        return command;
    }

    public String[] getCommandComponents(String commandInput) {
        return commandInput.trim().split(SEPARATOR);
    }

    public void getIndexNumber(String[] commandComponents) {
        try {
            instrumentNumber = Integer.parseInt(commandComponents[INSTRUMENT_INDEX]) - INDEX_OFFSET;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidNoIndexError();
        } catch (NumberFormatException e) {
            throw new InvalidIndexError();
        }
    }
}
