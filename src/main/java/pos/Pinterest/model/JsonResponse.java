package pos.Pinterest.model;

public class JsonResponse {
    private boolean hasError;
    private ErrorType errorType;

    public JsonResponse(boolean hasError, ErrorType errorType) {
        this.hasError = hasError;
        this.errorType = errorType;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}

