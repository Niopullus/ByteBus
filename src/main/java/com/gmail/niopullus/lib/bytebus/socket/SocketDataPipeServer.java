package com.gmail.niopullus.lib.bytebus.socket;

import com.gmail.niopullus.lib.bytebus.dataPipe.DataPipe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;

public class SocketDataPipeServer implements Runnable {

    private int port;
    private ServerSocket serverSocket;
    private boolean closed;
    private Queue<DataPipe> clients;

    public SocketDataPipeServer(final int port) {
        super();
        this.port = port;
        closed = false;
        clients = new ArrayDeque<>();
    }

    public void start() {
        if (!closed) {
            createServer();
            createThread();
        }
    }

    public void close() {
        closed = true;
        notify();
    }

    private DataPipe getClient() {
        if (clients.isEmpty()) {
            waitForClientCheckExceptions();
        }
        return clients.remove();
    }

    private void waitForClientCheckExceptions() {
        try {
            waitForClient();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForClient() throws InterruptedException {
        while (!closed) {
            synchronized(this) {
                wait();
            }
        }
    }

    private void createServer() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void createThread() {
        final Thread thread;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (!closed) {
            receiveClient();
        }
    }

    private void receiveClient() {
        final Socket socket;
        try {
            socket = serverSocket.accept();
            addClient(socket);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        notify();
    }

    private void addClient(final Socket socket) {
        final SocketDataPipeBuilder socketDataPipeBuilder;
        final DataPipe client;
        socketDataPipeBuilder = new SocketDataPipeBuilder();
        socketDataPipeBuilder.setSocket(socket);
        client = socketDataPipeBuilder.createDataPipe();
        clients.add(client);
    }

}
