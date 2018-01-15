package com.gmail.niopullus.lib.bytebus.dataPipe;

import java.io.OutputStream;

class ConnectionStatusChecker implements Runnable {

    private DataPipeContext context;
    private OutputStream outputStream;
    private DataPipeStatus status;
    private ConnectionStatusResponder connectionStatusResponder;

    ConnectionStatusChecker(final DataPipeContext context) {
        super();
        this.context = context;
        status = context.getStatus();
        connectionStatusResponder = new ConnectionStatusResponder(context);
        getOutputStream();
    }

    private void getOutputStream() {
        final DataConnector connector;
        connector = context.getDataConnector();
        outputStream = connector.getOutputStream();
    }

    void start() {
        final Thread thread;
        thread = new Thread(this);
        thread.start();
    }

    void close() {
        synchronized (this) {
            notify();
        }
    }

    void connectionAffirmed() {
        connectionStatusResponder.setConnectionAffirmed(true);
        if (!status.isConnected()) {
            synchronized (this) {
                notify();
            }
        }
    }

    public void run() {
        while (!status.isClosed()) {
            if (status.isConnected()) {
                connectionStatusResponder.maintainConnection();
            } else {
                connectionStatusResponder.seekConnection();
            }
        }
    }

}
