package com.gmail.niopullus.lib.bytebus.dataPipe;

import java.io.InputStream;
import java.io.OutputStream;

public class DataConnector {

    private InputStream inputStream;
    private OutputStream outputStream;

    public DataConnector() {
        super();
    }

    InputStream getInputStream() {
        return inputStream;
    }

    OutputStream getOutputStream() {
        return outputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

}
