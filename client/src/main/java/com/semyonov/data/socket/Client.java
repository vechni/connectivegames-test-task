package com.semyonov.data.socket;

import com.semyonov.model.Request;
import com.semyonov.model.Response;
import com.semyonov.model.StatusConnection;

public interface Client
{
    StatusConnection openConnection();

    void closeConnection();

    void sendRequest( Request request );

    Response getResponse();

    double getAverageTimeRequest();

    int getCountSuccessRequest();

    int getCountFailedRRequest();
}
