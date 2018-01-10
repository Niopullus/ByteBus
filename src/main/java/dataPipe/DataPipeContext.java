package dataPipe;

import java.util.Map;

public class DataPipeContext {

    private DataPipeStatus status;
    private DataConnector dataConnector;
    private ConnectionFailureResponder connectionFailureResponder;
    private ConnectionStatusChecker connectionStatusChecker;
    private Map<String, Object> connectionData;
    private DataReader dataReader;
    private DataWriter dataWriter;
    private ConnectionStatusConfiguration connectionStatusConfiguration;

    public DataPipeContext() {
        super();
    }

    public DataConnector getDataConnector() {
        return dataConnector;
    }

    public DataPipeStatus getStatus() {
        return status;
    }

    public Map<String, Object> getConnectionData() {
        return connectionData;
    }

    public ConnectionFailureResponder getConnectionFailureResponder() {
        return connectionFailureResponder;
    }

    public ConnectionStatusChecker getConnectionStatusChecker() {
        return connectionStatusChecker;
    }

    public DataReader getDataReader() {
        return dataReader;
    }

    public DataWriter getDataWriter() {
        return dataWriter;
    }

    public ConnectionStatusConfiguration getConnectionStatusConfiguration() {
        return connectionStatusConfiguration;
    }

    public void setDataConnector(final DataConnector dataConnector) {
        this.dataConnector = dataConnector;
    }

    public void setStatus(final DataPipeStatus status) {
        this.status = status;
    }

    public void setConnectionData(final Map<String, Object> connectionData) {
        this.connectionData = connectionData;
    }

    public void setConnectionFailureResponder(final ConnectionFailureResponder connectionFailureResponder) {
        this.connectionFailureResponder = connectionFailureResponder;
    }

    public void setConnectionStatusChecker(final ConnectionStatusChecker connectionStatusChecker) {
        this.connectionStatusChecker = connectionStatusChecker;
    }

    public void setDataReader(final DataReader dataReader) {
        this.dataReader = dataReader;
    }

    public void setDataWriter(final DataWriter dataWriter) {
        this.dataWriter = dataWriter;
    }

    public void setConnectionStatusConfiguration(ConnectionStatusConfiguration connectionStatusConfiguration) {
        this.connectionStatusConfiguration = connectionStatusConfiguration;
    }

}
