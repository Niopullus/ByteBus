package com.gmail.niopullus.lib.bytebus.extraction;

public class ExtractorException extends RuntimeException {

    private static final String EXCEPTION_PREFIX = "Data Extraction Exception: ";

    public ExtractorException(final String details) {
        super(EXCEPTION_PREFIX + details);
    }

}
