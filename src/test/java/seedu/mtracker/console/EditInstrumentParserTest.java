package seedu.mtracker.console;

import org.junit.jupiter.api.Test;
import seedu.mtracker.model.Instrument;
import seedu.mtracker.model.subinstrument.Stock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditInstrumentParserTest extends GeneralInstrumentParserTest {

    public static final String USER_INPUT_EDIT_NAME_AND_REMARK = "TTTXXX" + SEPARATOR_SPECIFIER + "Test Remark";
    public static final String[] EXPECTED_KEYS_NAME_AND_REMARK = {"name", "remark"};
    public static final String[] EXPECTED_OUTPUT_NAME_AND_REMARK = {"TTTXXX", "Test Remark"};
    public static final HashSet<String> PARAM_INPUT_NAME_AND_REMARK = new HashSet<>(Arrays.asList("name", "remark"));

    public static final String USER_INPUT_EDIT_CURRENT_PRICE_AND_SENTIMENT = "100" + SEPARATOR_SPECIFIER + "neutral";
    public static final String[] EXPECTED_KEYS_CURRENT_PRICE_AND_SENTIMENT = {"current-price", "sentiment"};
    public static final String[] EXPECTED_OUTPUT_CURRENT_PRICE_AND_SENTIMENT = {"100", "neutral"};
    public static final HashSet<String> PARAM_INPUT_CURRENT_PRICE_AND_SENTIMENT =
            new HashSet<>(Arrays.asList("current-price", "sentiment"));

    public static final String USER_INPUT_EDIT_ENTRY_PRICE_AND_EXIT_PRICE = "10" + SEPARATOR_SPECIFIER + "100";
    public static final String[] EXPECTED_KEYS_ENTRY_PRICE_AND_EXIT_PRICE = {"entry-price", "exit-price"};
    public static final String[] EXPECTED_OUTPUT_ENTRY_PRICE_AND_EXIT_PRICE = {"10", "100"};
    public static final HashSet<String> PARAM_INPUT_ENTRY_PRICE_AND_EXIT_PRICE =
            new HashSet<>(Arrays.asList("entry-price", "exit-price"));

    public static final String TEST_NAME = "Test";
    public static final double TEST_PRICE = 1.0;
    public static final String TEST_SENTIMENT = "neutral";
    public static final String TEST_REMARK = "";
    public static final Instrument TEST_STOCK = new Stock(TEST_NAME, TEST_PRICE, TEST_SENTIMENT, TEST_REMARK);
    public static final int TEST_INDEX = 0;

    @Override
    public int getParameterSize() {
        return 0;
    }

    HashMap<String,String> initialiseTestResources(String[] expectedKeys, String[] expectedValues) {
        HashMap<String, String> expectedResult = new HashMap<>();
        for (int i = 0; i < expectedKeys.length; i++) {
            expectedResult.put(expectedKeys[i],expectedValues[i]);
        }
        return expectedResult;
    }

    void testEditInstrumentParameters(String input, HashSet<String> expectedParameters,
                                      String[] expectedKeys, String[] expectedValues) {
        simulateConsoleInput(input);
        HashMap<String, String> expectedHash = initialiseTestResources(expectedKeys, expectedValues);
        EditInstrumentParser editInstrumentParser = new EditInstrumentParser();
        editInstrumentParser.createEditCommand(expectedParameters, TEST_STOCK, TEST_INDEX);
        HashMap<String, String> outputHash = EditInstrumentParser.getEditedParametersHash();
        assertTrue(outputHash.equals(expectedHash));
    }

    @Test
    void editInstrumentParam_nameAndRemark_expectSuccess() {
        testEditInstrumentParameters(USER_INPUT_EDIT_NAME_AND_REMARK,
                PARAM_INPUT_NAME_AND_REMARK,
                EXPECTED_KEYS_NAME_AND_REMARK,
                EXPECTED_OUTPUT_NAME_AND_REMARK);
    }


    @Test
    void editInstrumentParam_currentPriceAndSentiment_expectSuccess() {
        testEditInstrumentParameters(USER_INPUT_EDIT_CURRENT_PRICE_AND_SENTIMENT,
                PARAM_INPUT_CURRENT_PRICE_AND_SENTIMENT,
                EXPECTED_KEYS_CURRENT_PRICE_AND_SENTIMENT,
                EXPECTED_OUTPUT_CURRENT_PRICE_AND_SENTIMENT);
    }

    @Test
    void editInstrumentParam_entryAndExitPrice_expectSuccess() {
        testEditInstrumentParameters(USER_INPUT_EDIT_ENTRY_PRICE_AND_EXIT_PRICE,
                PARAM_INPUT_ENTRY_PRICE_AND_EXIT_PRICE,
                EXPECTED_KEYS_ENTRY_PRICE_AND_EXIT_PRICE,
                EXPECTED_OUTPUT_ENTRY_PRICE_AND_EXIT_PRICE);
    }
}