package seedu.mtracker.commons.error;

public class InvalidPastReturnError extends Exception {
    @Override
    public String getMessage() {
        return ErrorMessage.INVALID_PAST_RETURN_ERROR;
    }
}