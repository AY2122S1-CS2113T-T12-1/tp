package seedu.mtracker.error;

public class InvalidSentimentError extends Exception {
    @Override
    public String getMessage() {
        return ErrorMessage.INVALID_SENTIMENT_ERROR;
    }
}
