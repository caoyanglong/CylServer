package com.cyl.cylserver.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by cyl
 * on 2016/7/23.
 * email:670654904@qq.com
 */
public class ClientRequest extends Thread {
    private Socket socket;
    private BufferedInputStream bufferIn;
    private BufferedOutputStream bufferedOut;

    public ClientRequest(Socket socket) {
        super();
        this.socket = socket;
        try {
            bufferIn = new BufferedInputStream(socket.getInputStream());
            bufferedOut = new BufferedOutputStream(socket.getOutputStream());
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();

    }
}
