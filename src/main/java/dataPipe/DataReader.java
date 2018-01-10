package dataPipe;

import packageBuilder.DataPackageBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

class DataReader implements Runnable {

    private DataPackageBuilder dataPackageBuilder;
    private Queue<DataPackage> dataPackages;
    private InputStream inputStream;
    private DataPipeStatus dataPipeStatus;

    private static final int READ_BUFFER_SIZE = 64;

    DataReader(final DataPipeContext context) {
        final DataConnector dataConnector;
        dataConnector = context.getDataConnector();
        inputStream = dataConnector.getInputStream();
        dataPipeStatus = context.getStatus();
        dataPackageBuilder = new DataPackageBuilder();
        dataPackages = new ArrayDeque<>();
        dataPackageBuilder.setDataPackages(dataPackages);
    }

    DataPackage readData() {
        if (dataPackages.isEmpty()) {
            waitForDataPackageCatchExceptions();
        }
        return dataPackages.remove();
    }

    private void waitForDataPackageCatchExceptions() {
        try {
            waitForDataPackage();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForDataPackage() throws InterruptedException {
        synchronized (this) {
            while (dataPackages.isEmpty() && !dataPipeStatus.isClosed()) {
                wait();
            }
        }
    }

    public void run() {
        while (!dataPipeStatus.isClosed()) {
            readInputDataCheckExceptions();
        }
    }

    private void readInputDataCheckExceptions() {
        try {
            readInputData();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void readInputData() throws IOException {
        final byte[] data;
        final int amountBytesRead;
        data = new byte[READ_BUFFER_SIZE];
        amountBytesRead = inputStream.read(data);
        dataPackageBuilder.addData(data, amountBytesRead);
    }

}
