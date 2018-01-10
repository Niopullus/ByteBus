package socket;

import dataPipe.ConnectionFailureResponder;
import dataPipe.DataPipeContext;
import java.util.Map;

public class SocketConnectionFailureResponder extends ConnectionFailureResponder {

    private static final String ADDRESS_CONNECTION_DATA_KEY = "Address";
    private static final String PORT_CONNECTION_DATA_KEY = "Port";
    private boolean attemptReconnect;

    public SocketConnectionFailureResponder(final DataPipeContext context) {
        super(context);
        attemptReconnect = true;
    }

    public void enableAttemptReconnect() {
        attemptReconnect = true;
    }

    public void disableAttemptReconnect() {
        attemptReconnect = false;
    }

    @Override
    public void connectionLost() {
        if (attemptReconnect) {
            reconnect();
        } else {
            super.connectionLost();;
        }
    }

    private void reconnect() {
        //TODO Reconnect socket
    }

    private String getAddress() {
        final Map<String, Object> connectionData;
        connectionData = getConnectionData();
        return (String) connectionData.get(ADDRESS_CONNECTION_DATA_KEY);
    }

    private String getPort() {
        final Map<String, Object> connectionData;
        connectionData = getConnectionData();
        return (String) connectionData.get(PORT_CONNECTION_DATA_KEY);
    }

    private Map<String, Object> getConnectionData() {
        final DataPipeContext context;
        context = getContext();
        return context.getConnectionData();
    }

}
