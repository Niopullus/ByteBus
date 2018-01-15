package com.gmail.niopullus.lib.bytebus.dataPipe;

import java.util.ArrayDeque;
import java.util.Queue;

class DataWriter implements Runnable {

    private DataPipeStatus status;
    private Queue<DataPackage> dataPackages;
    private DataSender dataSender;

    DataWriter(final DataPipeContext context) {
        super();
        dataPackages = new ArrayDeque<>();
        dataSender = new DataSender(context);
    }

    void writeData(final DataPackage dataPackage) {
        dataPackages.add(dataPackage);
        notifyDataPackageAdded();
    }

    void start() {
        final Thread thread;
        thread = new Thread(this);
        thread.start();
    }

    void close() {
        notifyDataPackageAdded();
    }

    private void notifyDataPackageAdded() {
        synchronized (this) {
            notify();
        }
    }

    public void run() {
        while (!status.isClosed()) {
            checkForDataPackages();
        }
    }

    private void checkForDataPackages() {
        if (dataPackages.isEmpty()) {
            waitForDataPackagesCatchExceptions();
        } else {
            sendData();
        }
    }

    private void sendData() {
        final DataPackage dataPackage;
        dataPackage = dataPackages.remove();
        dataSender.send(dataPackage);
    }

    private void waitForDataPackagesCatchExceptions() {
        try {
            waitForDataPackages();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForDataPackages() throws InterruptedException {
        while (keepWaiting()) {
            synchronized (this) {
                wait();
            }
        }
    }

    private boolean keepWaiting() {
        return dataPackages.isEmpty() && !status.isClosed();
    }

}
