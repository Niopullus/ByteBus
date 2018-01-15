package com.gmail.niopullus.lib.bytebus.socket;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataConnector;
import com.gmail.niopullus.lib.bytebus.dataPipe.DataPipe;
import com.gmail.niopullus.lib.bytebus.dataPipe.DataPipeContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketDataPipeBuilder {

    private String address;
    private int port;
    private Socket socket;
    private DataPipeContext context;
    private InputStream inputStream;
    private OutputStream outputStream;

    public SocketDataPipeBuilder() {
        super();
        context = new DataPipeContext();
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void setSocket(final Socket socket) {
        this.socket = socket;
    }

    public DataPipe createDataPipe() {
        if (socket == null) {
            createSocket();
        }
        createDataConnector();
        return new DataPipe(context);
    }

    private void createSocket() {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDataConnector() {
        final DataConnector dataConnector;
        extractIO();
        dataConnector = new DataConnector();
        dataConnector.setInputStream(inputStream);
        dataConnector.setOutputStream(outputStream);
        context.setDataConnector(dataConnector);
    }

    private void extractIO() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
