package seedu.mtracker.error;

public class InvalidEmptyExpiryDateError extends Exception {
    @Override
    public String getMessage() {
        return ErrorMessage.INVALID_EXPIRY_DATE_EMPTY_ERROR;
    }
}
