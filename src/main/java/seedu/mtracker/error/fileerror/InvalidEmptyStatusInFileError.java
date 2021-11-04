package seedu.mtracker.error.fileerror;

import seedu.mtracker.error.ErrorMessage;

public class InvalidEmptyStatusInFileError extends Exception {
    @Override
    public String getMessage() {
        return ErrorMessage.EMPTY_STATUS_IN_FILE_ERROR;
    }
}