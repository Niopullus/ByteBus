package com.gmail.niopullus.lib.bytebus.serialization;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;
import com.gmail.niopullus.lib.niodatatree.DataTree;

public class DataTreeSerializer {

    private StringSerializer stringSerializer;

    public DataTreeSerializer() {
        super();
        stringSerializer = new StringSerializer();
    }

    public DataPackage serialize(final DataTree dataTree) {
        final String compressedData;
        compressedData = dataTree.compress();
        return stringSerializer.serialize(compressedData);
    }

}
