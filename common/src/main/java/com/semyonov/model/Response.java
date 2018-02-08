package com.semyonov.model;

import java.io.Serializable;

public class Response
        implements Serializable
{
    private StatusRequest status;
    private String message;
    private int bet;
    private int prize;
    private boolean isWin;

    public Response( final StatusRequest status, final String message, final int bet, final int prize, final boolean isWin ){
        this.status = status;
        this.message = message;
        this.bet = bet;
        this.prize = prize;
        this.isWin = isWin;
    }

    public StatusRequest getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public int getBet(){
        return bet;
    }

    public int getPrize(){
        return prize;
    }

    public boolean isWin(){
        return isWin;
    }

    @Override
    public String toString(){
        return "Status: " + status + ", message: " + message + ",is win: " + isWin + ", bet: " + bet + ", prize: " + prize;
    }
}

