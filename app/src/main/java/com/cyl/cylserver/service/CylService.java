package com.cyl.cylserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

import com.cyl.cylserver.utils.MyLog;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cyl
 * on 2016/7/23.
 * email:670654904@qq.com
 * 开启一个服务 用来作为 http 服务器
 */
public class CylService extends Service{
    /**
     * 启动服务
     */
    private final static int STARTRUN = 0;
    private WorkHanlder workHanlder;
    private static int localPort = 5555;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        HandlerThread handlerThread = new HandlerThread("cylserver");
        handlerThread.start();
        this.workHanlder = new WorkHanlder(handlerThread.getLooper());
        workHanlder.sendEmptyMessage(STARTRUN);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 工作hander
     */
    public class WorkHanlder extends Handler{
        public WorkHanlder(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case STARTRUN:
                    try {
                        ServerSocket serverSocket = new ServerSocket(localPort);
                        while (true){
                            Socket socket = serverSocket.accept();
                            ClientRequest request = new ClientRequest(socket);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        MyLog.d("<server_start_exception> %s",e.getMessage());
                    }
                    break;

            }
        }
    }
}
