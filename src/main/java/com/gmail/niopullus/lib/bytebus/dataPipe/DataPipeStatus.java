package com.gmail.niopullus.lib.bytebus.dataPipe;

public class DataPipeStatus {

    private boolean connected;
    private boolean yetConnected;
    private boolean closed;
    private DataPipeStatusDelegate delegate;
    private DataPipeContext context;

    DataPipeStatus(final DataPipeContext context) {
        this.context = context;
        connected = false;
        closed = false;
        delegate = null;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean hasBeenYetConnected() {
        return yetConnected;
    }

    void setConnectionStatus(final boolean status) {
        if (!closed && status != connected) {
            connected = status;
            updateDelegate();
            updateYetConnected();
        }
    }

    private void updateDelegate() {
        if (yetConnected) {
            updateDelegateYetConnected();
        } else {
            updateDelegateNotYetConnected();
        }
    }

    private void updateDelegateNotYetConnected() {
        if (connected) {
            notifyDelegateConnectionSucceeded();
        } else {
            notifyDelegateConnectionFailed();
        }
    }

    private void notifyDelegateConnectionSucceeded() {
        delegate.connectionSucceeded();
    }

    private void notifyDelegateConnectionFailed() {
        delegate.connectionFailed();
    }

    private void updateDelegateYetConnected() {
        if (connected) {
            notifyDelegateConnectionResumed();
        } else {
            notifyDelegateConnectionLost();
        }
    }

    private void notifyDelegateConnectionResumed() {
        delegate.connectionResumed();
    }

    private void notifyDelegateConnectionLost() {
        delegate.connectionLost();
    }

    private void updateYetConnected() {
        if (!yetConnected) {
            yetConnected = true;
        }
    }

    public void close() {
        closed = true;
        connected = false;
        notifyDelegateConnectionTerminated();
        context.close();
    }

    private void notifyDelegateConnectionTerminated() {
        delegate.connectionTerminated();
    }

}
