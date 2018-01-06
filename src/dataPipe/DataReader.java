package dataPipe;

import dataPackage.DataPackage;

class DataReader implements Runnable {

    private DataConnector dataConnector;
    private byte[] data;
    private DataPackage dataPackage;

    DataReader(final DataConnector dataConnector) {
        this.dataConnector = dataConnector;
    }

    DataPackage readData() {
        if (dataPackage == null) {

        }
    }

    public void run() {

    }

}
