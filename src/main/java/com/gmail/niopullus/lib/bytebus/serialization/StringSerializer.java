package com.gmail.niopullus.lib.bytebus.serialization;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;

public class StringSerializer {

    public StringSerializer() {
        super();
    }

    public DataPackage serialize(final String string) {
        final DataPackage dataPackage;
        final byte[] data;
        data = string.getBytes();
        dataPackage = new DataPackage(data);
        return dataPackage;
    }

}
