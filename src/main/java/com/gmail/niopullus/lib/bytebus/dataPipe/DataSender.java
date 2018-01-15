package com.gmail.niopullus.lib.bytebus.dataPipe;

import java.io.IOException;
import java.io.OutputStream;

class DataSender {

    private DataPipeContext context;
    private OutputStream outputStream;

    DataSender(final DataPipeContext context) {
        super();
        this.context = context;
    }

    void send(final DataPackage dataPackage) {
        final byte[] data;
        retrieveOutputStream();
        data = dataPackage.getData();
        for (byte byteData : data) {
            writeData(byteData);
        }
    }

    private void retrieveOutputStream() {
        final DataConnector dataConnector;
        dataConnector = context.getDataConnector();
        outputStream = dataConnector.getOutputStream();
    }

    private void writeData(final byte byteData) {
        try {
            outputStream.write(byteData);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
