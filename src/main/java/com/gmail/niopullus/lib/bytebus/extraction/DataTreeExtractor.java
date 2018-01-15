package com.gmail.niopullus.lib.bytebus.extraction;

import com.gmail.niopullus.lib.niodatatree.DataTree;
import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;

public class DataTreeExtractor extends DataExtractor {

    private StringExtractor stringExtractor;

    public DataTreeExtractor() {
        super();
        stringExtractor = new StringExtractor();
    }

    public DataTree extractDataTree(final DataPackage dataPackage) {
        try {
            final String compressedData;
            compressedData = stringExtractor.extractString(dataPackage);
            return DataTree.decompress(compressedData);
        } catch (final Exception e) {
            throwExtractorException(e, null);
        }
        return null;
    }

}
