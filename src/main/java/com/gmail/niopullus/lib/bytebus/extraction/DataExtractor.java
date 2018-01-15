package com.gmail.niopullus.lib.bytebus.extraction;

public class DataExtractor {

    public DataExtractor() {
        super();
    }

    public void throwExtractorException(final Exception exception, final String reason) {
        final ExtractorException extractorException;
        extractorException = new ExtractorException(reason);
        if (exception != null) {
            extractorException.initCause(exception);
        }
        throw extractorException;
    }

}
