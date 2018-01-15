package com.gmail.niopullus.lib.bytebus.extraction;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;

public class DataExtractor {

    private DataPackage dataPackage;

    public DataExtractor(final DataPackage dataPackage) {
        super();
        this.dataPackage = dataPackage;
    }

    public DataPackage getDataPackage() {
        return dataPackage;
    }

}
