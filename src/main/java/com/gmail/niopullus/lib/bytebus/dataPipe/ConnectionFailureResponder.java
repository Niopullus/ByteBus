package com.gmail.niopullus.lib.bytebus.dataPipe;

public class ConnectionFailureResponder {

    private DataPipeContext context;

    public ConnectionFailureResponder(final DataPipeContext context) {
        super();
    }

    public DataPipeContext getContext() {
        return context;
    }

    public void connectionLost() {
        final DataPipeStatus status;
        status = context.getStatus();
        status.close();
    }

}
