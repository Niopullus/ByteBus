package com.gmail.niopullus.lib.bytebus.extraction;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;
import com.gmail.niopullus.lib.bytebus.extraction.DataExtractor;

public class StringExtractor extends DataExtractor {

    public StringExtractor() {
        super();
    }

    public String extractString(final DataPackage dataPackage) {
        try {
            final byte[] data;
            data = dataPackage.getData();
            return new String(data);
        } catch (final Exception e) {
            throwExtractorException(e, null);
        }
        return null;
    }

}
