package com.gmail.niopullus.lib.bytebus.dataPipe;

public interface DataPipeStatusDelegate {

    void connectionFailed();
    void connectionLost();
    void connectionSucceeded();
    void connectionResumed();
    void connectionTerminated();

}
