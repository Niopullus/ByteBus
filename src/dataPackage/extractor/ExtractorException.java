package dataPackage.extractor;

public class ExtractorException extends RuntimeException {

    private static final String EXCEPTION_PREFIX = "Data Extraction Exception: ";

    ExtractorException(final String details) {
        super(EXCEPTION_PREFIX + details);
    }

}
