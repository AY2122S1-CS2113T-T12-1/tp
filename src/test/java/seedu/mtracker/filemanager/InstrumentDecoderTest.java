package seedu.mtracker.filemanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mtracker.error.FileLoadError;
import seedu.mtracker.error.InvalidInstrumentInFileError;
import seedu.mtracker.model.Instrument;
import seedu.mtracker.model.InstrumentManager;
import seedu.mtracker.model.subinstrument.Forex;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InstrumentDecoderTest {

    public static final String TEST_TYPE = "forex";
    public static final String TEST_INVALID_TYPE = "nft";
    public static final String TEST_NAME = "Test12";
    public static final String TEST_INVALID_FOREX_NAME = "Test";
    public static final double TEST_PRICE = 34.5;
    public static final String TEST_PRICE_STRING = "34.5";
    public static final String TEST_INVALID_PRICE_STRING = "-1";
    public static final String TEST_SENTIMENT = "negative";
    public static final String TEST_INVALID_SENTIMENT = "+";
    public static final String TEST_DONE_STRING = "false";
    public static final double TEST_ENTRY_PRICE = 30.0;
    public static final String TEST_ENTRY_PRICE_STRING = "30.0";
    public static final double TEST_EXIT_PRICE = 31.0;
    public static final String TEST_EXIT_PRICE_STRING = "31.0";
    public static final int DAYS_DIFFERENCE = 1;
    public static final LocalDate FUTURE_DATE = LocalDate.now().plusDays(DAYS_DIFFERENCE);
    public static final String FUTURE_DATE_STRING = "2021-12-02";
    public static final String FUTURE_INVALID_DATE_STRING = "2 November";
    public static final String TEST_REMARK = "";
    public static final Instrument TEST_FOREX = new Forex(TEST_NAME, TEST_PRICE, TEST_SENTIMENT,
            TEST_ENTRY_PRICE, TEST_EXIT_PRICE, FUTURE_DATE, TEST_REMARK);

    public static final int NUMBER_OF_PARAMS = 8;
    public static final int LENGTH_OF_DATE = 10;
    public static final int LENGTH_OF_PRICES = 12;
    public static final int LENGTH_OF_DONE = 5;
    public static final int LENGTH_OF_INSTRUMENT = 5;

    public static final String[] INVALID_INSTRUMENT_TYPE_TEXT_SEGMENT = {TEST_INVALID_TYPE,
        TEST_NAME,
        TEST_PRICE_STRING,
        TEST_SENTIMENT,
        TEST_DONE_STRING,
        TEST_ENTRY_PRICE_STRING,
        TEST_EXIT_PRICE_STRING,
        FUTURE_DATE_STRING,
        TEST_REMARK
    };

    public static final String[] INVALID_FOREX_NAME_TEXT_SEGMENT = {TEST_TYPE,
        TEST_INVALID_FOREX_NAME,
        TEST_PRICE_STRING,
        TEST_SENTIMENT,
        TEST_DONE_STRING,
        TEST_ENTRY_PRICE_STRING,
        TEST_EXIT_PRICE_STRING,
        FUTURE_DATE_STRING,
        TEST_REMARK
    };

    public static final String[] INVALID_FOREX_PRICE_TEXT_SEGMENT = {TEST_TYPE,
        TEST_NAME,
        TEST_INVALID_PRICE_STRING,
        TEST_SENTIMENT,
        TEST_DONE_STRING,
        TEST_ENTRY_PRICE_STRING,
        TEST_EXIT_PRICE_STRING,
        FUTURE_DATE_STRING,
        TEST_REMARK
    };

    public static final String[] INVALID_FOREX_SENTIMENT_TEXT_SEGMENT = {TEST_TYPE,
        TEST_NAME,
        TEST_PRICE_STRING,
        TEST_INVALID_SENTIMENT,
        TEST_DONE_STRING,
        TEST_ENTRY_PRICE_STRING,
        TEST_EXIT_PRICE_STRING,
        FUTURE_DATE_STRING,
        TEST_REMARK
    };

    public static final String[] INVALID_FOREX_EXPIRY_TEXT_SEGMENT = {TEST_TYPE,
        TEST_NAME,
        TEST_PRICE_STRING,
        TEST_INVALID_SENTIMENT,
        TEST_DONE_STRING,
        TEST_ENTRY_PRICE_STRING,
        TEST_EXIT_PRICE_STRING,
        FUTURE_INVALID_DATE_STRING,
        TEST_REMARK
    };

    private InstrumentManager instrumentManager;
    private Storage storage;

    @BeforeEach
    void initialiseTestResources() {
        storage = new Storage();
        instrumentManager = InstrumentManager.getInstance();
        instrumentManager.addInstrument(TEST_FOREX);
    }

    @Test
    void checkNoOfParameters() {
        String textFileFormattedInstrument = instrumentManager.getInstrument(0).textFileFormatting();
        String combinedString = TEST_NAME + TEST_SENTIMENT + TEST_REMARK;
        int lengthOfFormattedInstrument = combinedString.length() + NUMBER_OF_PARAMS + LENGTH_OF_DATE
                + LENGTH_OF_PRICES + LENGTH_OF_DONE + LENGTH_OF_INSTRUMENT;
        assertEquals(lengthOfFormattedInstrument, textFileFormattedInstrument.length());
    }

    @Test
    void decodeGeneralAttributes_invalidName_expectException() {
        assertThrows(FileLoadError.class,
            () -> InstrumentDecoder
                    .validateAndDecodeGeneralAttributes(INVALID_FOREX_NAME_TEXT_SEGMENT));
    }

    @Test
    void decodeGeneralAttributes_invalidPrice_expectException() {
        assertThrows(FileLoadError.class,
            () -> InstrumentDecoder
                    .validateAndDecodeGeneralAttributes(INVALID_FOREX_PRICE_TEXT_SEGMENT));
    }

    @Test
    void decodeGeneralAttributes_invalidSentiment_expectException() {
        assertThrows(FileLoadError.class,
            () -> InstrumentDecoder
                    .validateAndDecodeGeneralAttributes(INVALID_FOREX_SENTIMENT_TEXT_SEGMENT));
    }

    @Test
    void decodeSpecificAttributes_invalidExpiry_expectException() {
        assertThrows(FileLoadError.class,
            () -> ForexDecoder
                    .validateAndDecodeSpecificAttributes(INVALID_FOREX_EXPIRY_TEXT_SEGMENT));
    }

    @Test
    void addSavedInstrumentToList_invalidType_expectException() {
        assertThrows(InvalidInstrumentInFileError.class,
            () -> InstrumentDecoder
                    .addSavedInstrumentToList(instrumentManager, INVALID_INSTRUMENT_TYPE_TEXT_SEGMENT));
    }
}
