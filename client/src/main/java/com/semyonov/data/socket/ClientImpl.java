package com.semyonov.data.socket;

import com.semyonov.model.Request;
import com.semyonov.model.Response;
import com.semyonov.model.StatusConnection;
import com.semyonov.model.StatusRequest;
import com.semyonov.utils.ClassNameUtils;
import com.semyonov.utils.ConnectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientImpl
        implements Client
{
    private static final String EMPTY_STRING = "";
    private static final int TIMEOUT = 5000;
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());

    private Socket socket;
    private ObjectInputStream deserializer;
    private ObjectOutputStream serializer;
    private long startTimeRequest = 0;
    private long endTimeRequest = 0;
    private long totalTimeRequest = 0;
    private int counterRequest = 0;
    private int countSuccessRequest = 0;

    public ClientImpl(){
    }

    @Override
    public StatusConnection openConnection(){
        try{
            log.debug("Connection open");
            socket = new Socket(ConnectionUtils.HOST, ConnectionUtils.PORT);
            serializer = new ObjectOutputStream(socket.getOutputStream());
            deserializer = new ObjectInputStream(socket.getInputStream());
            socket.setSoTimeout(TIMEOUT);
        }catch( Exception e ){
            log.error("Connection:", e);
            return StatusConnection.FAILED;
        }

        return StatusConnection.SUCCESS;
    }

    @Override
    public void closeConnection(){
        try{
            log.debug("Connection close");
            socket.close();
            serializer.close();
            deserializer.close();
        }catch( IOException e ){
            log.error("Connection:", e);
        }
    }

    @Override
    public void sendRequest( final Request request ){
        try{
            log.debug("Request: " + request.toString());
            serializer.writeObject(request);
            serializer.flush();
        }catch( IOException e ){
            log.error("Request:", e);
            return;
        }

        if( ! request.isLast() ){
            registerStartOfRequest();
        }
    }

    @Override
    public Response getResponse(){
        Response response = new Response(StatusRequest.ERROR, EMPTY_STRING, 0, 0, false);
        try{
            response = (Response) deserializer.readObject();
            log.debug("Response: " + response.toString());
            countSuccessRequest++;
        }catch( EOFException e ){
            closeConnection();
        }catch( IOException e ){
            log.error("Response:", e);
        }catch( ClassNotFoundException e ){
            throw new RuntimeException("Error of developer! Class mismatch to contract!");
        }finally{
            registerEndOfRequest();
        }

        return response;
    }

    @Override
    public double getAverageTimeRequest(){
        return (double) totalTimeRequest / counterRequest;
    }

    @Override
    public int getCountSuccessRequest(){
        return countSuccessRequest;
    }

    @Override
    public int getCountFailedRRequest(){
        return counterRequest - countSuccessRequest;
    }

    private void registerStartOfRequest(){
        startTimeRequest = System.currentTimeMillis();
        counterRequest++;
    }

    private void registerEndOfRequest(){
        endTimeRequest = System.currentTimeMillis();
        totalTimeRequest += endTimeRequest - startTimeRequest;
    }
}
