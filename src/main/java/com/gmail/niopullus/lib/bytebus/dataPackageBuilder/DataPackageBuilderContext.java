package com.gmail.niopullus.lib.bytebus.dataPackageBuilder;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPackage;

import java.util.List;
import java.util.Queue;

class DataPackageBuilderContext {

    private List<Byte> data;
    private int readPosition;
    private int contentStartPosition;
    private int dataSize;
    private boolean dataSizeFound;
    private Queue<DataPackage> dataPackages;

    DataPackageBuilderContext() {
        super();
        readPosition = 0;
        dataSize = 0;
        dataSizeFound = false;
    }

    int getReadPosition() {
        return readPosition;
    }

    List<Byte> getData() {
        return data;
    }

    int getDataSize() {
        return dataSize;
    }

    int getContentStartPosition() {
        return contentStartPosition;
    }

    Queue<DataPackage> getDataPackages() {
        return dataPackages;
    }

    boolean isDataSizeFound() {
        return dataSizeFound;
    }

    void setData(List<Byte> data) {
        this.data = data;
    }

    void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }

    void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    void setDataSizeFound(boolean dataSizeFound) {
        this.dataSizeFound = dataSizeFound;
    }

    void setContentStartPosition(final int contentStartPosition) {
        this.contentStartPosition = contentStartPosition;
    }

    void setDataPackages(final Queue<DataPackage> dataPackages) {
        this.dataPackages = dataPackages;
    }

}
