package com.gmail.niopullus.lib.bytebus.dataPipe;

public class ConnectionStatusConfiguration {

    private double statusCheckInterval;
    private double checkTolerance;
    private int seekTolerance;

    private static final double DEFAULT_STATUS_CHECK_INTERVAL = 15;
    private static final double DEFAULT_CHECK_TOLERANCE = 10;
    private static final int DEFAULT_SEEK_TOLERANCE = 10;

    public ConnectionStatusConfiguration() {
        super();
        statusCheckInterval = DEFAULT_STATUS_CHECK_INTERVAL;
        checkTolerance = DEFAULT_CHECK_TOLERANCE;
        seekTolerance = DEFAULT_SEEK_TOLERANCE;
    }

    double getCheckTolerance() {
        return checkTolerance;
    }

    double getStatusCheckInterval() {
        return statusCheckInterval;
    }

    int getSeekTolerance() {
        return seekTolerance;
    }

    public void setCheckTolerance(final double checkTolerance) {
        this.checkTolerance = checkTolerance;
    }

    public void setSeekTolerance(final int seekTolerance) {
        this.seekTolerance = seekTolerance;
    }

    public void setStatusCheckInterval(final double statusCheckInterval) {
        this.statusCheckInterval = statusCheckInterval;
    }

}
