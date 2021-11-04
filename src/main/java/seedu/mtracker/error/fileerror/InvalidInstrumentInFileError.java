package seedu.mtracker.error.fileerror;

import seedu.mtracker.error.ErrorMessage;

public class InvalidInstrumentInFileError extends Exception {
    @Override
    public String getMessage() {
        return ErrorMessage.INVALID_INSTRUMENT_IN_FILE_ERROR;
    }
}