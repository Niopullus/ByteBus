package dataPipe;

public interface DataPipeStatusDelegate {

    void connectionFailed();
    void connectionLost();
    void connectionSucceeded();
    void connectionResumed();
    void connectionTerminated();

}
