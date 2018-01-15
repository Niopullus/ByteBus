package com.gmail.niopullus.lib.bytebus.dataPipe;

public class DataPipeBuilder {

    private DataPipeContext context;

    public DataPipeBuilder() {
        super();
        context = new DataPipeContext();
    }

    public void setDataConnector(DataConnector dataConnector) {
        context.setDataConnector(dataConnector);
    }

    private DataPipe createDataPipe() {
        final DataPipe dataPipe;
        setupDataPipeContext();
        return new DataPipe(context);
    }

    private void setupDataPipeContext() {
        final ConnectionStatusChecker connectionStatusChecker;
        final ConnectionFailureResponder connectionFailureResponder;
        connectionStatusChecker = new ConnectionStatusChecker(context);
        connectionFailureResponder = new ConnectionFailureResponder(context);
        context.setConnectionStatusChecker(connectionStatusChecker);
        context.setConnectionFailureResponder(connectionFailureResponder);
    }

}
