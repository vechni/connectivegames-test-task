package com.semyonov;

import com.semyonov.module.server.MultiThreadServer;

public class MainServer
{
    public static void main( String[] args ){
        System.out.println("Start listening connection...");

        final MultiThreadServer server = new MultiThreadServer();
        server.listen();
    }
}
