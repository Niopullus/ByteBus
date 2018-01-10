package dataPipe;

class ConnectionStatusResponder {

    private ConnectionStatusConfiguration connectionStatusConfiguration;
    private final ConnectionStatusChecker connectionStatusChecker;
    private DataPipeStatus status;
    private boolean connectionAffirmed;
    private int seekAttempts;

    ConnectionStatusResponder(final DataPipeContext context) {
        super();
        connectionStatusConfiguration = context.getConnectionStatusConfiguration();
        connectionStatusChecker = context.getConnectionStatusChecker();
        status = context.getStatus();
        connectionAffirmed = false;
        seekAttempts = 0;
    }

    void setConnectionAffirmed(final boolean connectionAffirmed) {
        this.connectionAffirmed = connectionAffirmed;
    }

    void maintainConnection() {
        requestConnectionAffirmation();
        waitForAffirmation();
        checkForAffirmation();
        delayConnectionAffirmationCheck();
    }

    private void requestConnectionAffirmation() {

    }

    private void waitForAffirmation() {
        try {
            final double checkTolerance;
            checkTolerance = getCheckTolerance();
            Thread.sleep((long) checkTolerance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double getCheckTolerance() {
        return connectionStatusConfiguration.getCheckTolerance();
    }

    private void checkForAffirmation() {
        if (!connectionAffirmed) {
            reportNoConnection();
        }
    }

    private void reportNoConnection() {
        status.setConnectionStatus(false);
    }

    private void delayConnectionAffirmationCheck() {
        try {
            final double statusCheckInterval;
            statusCheckInterval = getStatusCheckInterval();
            Thread.sleep((long) statusCheckInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double getStatusCheckInterval() {
        return connectionStatusConfiguration.getStatusCheckInterval();
    }

    void seekConnection() {
        seekAttempts = 0;
        try {
            waitForConnection();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForConnection() throws InterruptedException {
        while (!connectionAffirmed) {
            synchronized (connectionStatusChecker) {
                final double checkTolerance;
                checkTolerance = getCheckTolerance();
                connectionStatusChecker.wait((long) checkTolerance);
            }
            checkConnectionEstablished();
        }
    }

    private void checkConnectionEstablished() {
        if (connectionAffirmed) {
            status.setConnectionStatus(true);
        }
        incrementSeekAttempts();
    }

    private void incrementSeekAttempts() {
        final int seekTolerance;
        seekTolerance = getSeekTolerance();
        if (seekAttempts > seekTolerance) {
            status.setConnectionStatus(false);
        } else {
            seekAttempts += 1;
        }
    }

    private int getSeekTolerance() {
        return connectionStatusConfiguration.getSeekTolerance();
    }

}
