package com.semyonov.module.server;

import com.semyonov.module.handler.GameHandler;
import com.semyonov.module.handler.Handler;
import com.semyonov.utils.ClassNameUtils;
import com.semyonov.utils.ConnectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer
{
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    public void listen(){
        try( ServerSocket server = new ServerSocket(ConnectionUtils.PORT) ){
            while( !server.isClosed() ){
                final Socket socket = server.accept();
                final Handler handler = new GameHandler(socket);
                handler.execute();
            }
        }catch( IOException e ){
            log.error("Server error", e);
        }
    }
}
