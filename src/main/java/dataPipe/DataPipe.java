package dataPipe;

import java.util.HashMap;
import java.util.Map;

public class DataPipe {

    private DataReader dataReader;
    private DataWriter dataWriter;
    private DataPipeContext context;
    private DataPipeStatus status;

    public DataPipe(final DataPipeContext context) {
        this.context = context;
        setupStatus();
        setupDataReader();
        setupDataWriter();
        setupContext();
        dataReader = new DataReader(context);
    }

    public ConnectionStatusConfiguration getConfig() {
        return context.getConnectionStatusConfiguration();
    }

    public void setConnectionStatusChecker(final ConnectionStatusChecker connectionStatusChecker) {
        context.setConnectionStatusChecker(connectionStatusChecker);
    }

    public void setConnectionFailureResponder(final ConnectionFailureResponder connectionFailureResponder) {
        context.setConnectionFailureResponder(connectionFailureResponder);
    }

    private void setupStatus() {
        status = new DataPipeStatus();
    }

    private void setupDataReader() {
        dataReader = new DataReader(context);
    }

    private void setupDataWriter() {
        dataWriter = new DataWriter();
    }

    private void setupContext() {
        status = new DataPipeStatus();
        context.setDataReader(dataReader);
        context.setDataWriter(dataWriter);
        context.setStatus(status);
        setupConnectionData();
    }

    private void setupConnectionData() {
        final Map<String, Object> connectionData;
        connectionData = new HashMap<>();
        context.setConnectionData(connectionData);
    }

    public DataPackage readData() {
        return dataReader.readData();
    }

    public void sendData(final DataPackage dataPackage) {

    }

    public void close() {
        status.close();
    }

}
