package com.gmail.niopullus.lib.bytebus.dataPipe;

public class DataPackage {

    private byte[] data;

    public DataPackage(final byte[] data) {
        super();
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

}
