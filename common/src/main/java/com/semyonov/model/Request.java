package com.semyonov.model;

import java.io.Serializable;

public class Request
        implements Serializable
{
    private String token;
    private String time;
    private int bet;
    private SideCoin sideCoin;
    private boolean isLast;

    public Request( final String token ){
        this.token = token;
    }

    public Request( final String token, final String time, final int bet, final SideCoin sideCoin, final boolean isLast ){
        this.token = token;
        this.time = time;
        this.bet = bet;
        this.sideCoin = sideCoin;
        this.isLast = isLast;
    }

    public String getToken(){
        return token;
    }

    public String getTime(){
        return time;
    }

    public int getBet(){
        return bet;
    }

    public SideCoin getSideCoin(){
        return sideCoin;
    }

    public boolean isLast(){
        return isLast;
    }

    @Override
    public String toString(){
        return "Token: " + token + ", bet: " + bet + ", Side coin: " + sideCoin;
    }
}
