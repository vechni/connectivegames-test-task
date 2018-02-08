package com.semyonov.data.socket;

import com.semyonov.model.Request;
import com.semyonov.model.Response;
import com.semyonov.model.StatusConnection;
import com.semyonov.utils.ClassNameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BridgeImpl
        implements Bridge
{
    private static final String EMPTY_STRING = "";
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    private Socket socket;
    private ObjectInputStream deserializer;
    private ObjectOutputStream serializer;

    public BridgeImpl( final Socket socket ){
        this.socket = socket;
    }

    @Override
    public StatusConnection openConnection(){
        try{
            log.debug("Connection open");
            serializer = new ObjectOutputStream(socket.getOutputStream());
            deserializer = new ObjectInputStream(socket.getInputStream());
        }catch( Exception e ){
            log.error("Connection open:", e);
            return StatusConnection.FAILED;
        }

        return StatusConnection.SUCCESS;
    }

    @Override
    public void closeConnection(){
        try{
            log.debug("Connection close");
            deserializer.close();
            serializer.close();
            socket.close();
        }catch( IOException e ){
            log.error("Connection close:", e);
        }
    }

    @Override
    public void sendResponse( final Response response ){
        try{
            log.debug("Response: " + response.toString());
            serializer.writeObject(response);
            serializer.flush();
        }catch( IOException e ){
            log.error("Response:", e);
        }
    }

    @Override
    public Request getRequest(){
        Request request = new Request(EMPTY_STRING);

        try{
            log.debug("Request: " + request.toString());
            request = (Request) deserializer.readObject();
        }catch( IOException e ){
            log.error("Request:", e);
        }catch( ClassNotFoundException e ){
            throw new RuntimeException("Error of developer! Class mismatch to contract!");
        }

        return request;
    }

    @Override
    public boolean isOpenConnection(){
        return !socket.isClosed();
    }
}
