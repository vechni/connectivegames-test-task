package com.semyonov.data.socket;

import com.semyonov.model.StatusConnection;
import com.semyonov.model.Request;
import com.semyonov.model.Response;

public interface Bridge
{
    StatusConnection openConnection();

    void closeConnection();

    void sendResponse( Response response );

    Request getRequest();

    boolean isOpenConnection();
}
