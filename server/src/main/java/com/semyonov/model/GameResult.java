package com.semyonov.model;

import java.io.Serializable;

public class GameResult
        implements Serializable
{
    private String id;
    private String playerToken;
    private String time;
    private int bet;
    private boolean isWin;
    private int prize;

    public GameResult( final String id,
                       final String token,
                       final String time,
                       final int bet,
                       final boolean isWin,
                       final int prize ){
        this.id = id;
        this.playerToken = token;
        this.time = time;
        this.bet = bet;
        this.isWin = isWin;
        this.prize = prize;
    }

    public String getId(){
        return id;
    }

    public String getPlayerToken(){
        return playerToken;
    }

    public String getTime(){
        return time;
    }

    public int getBet(){
        return bet;
    }

    public boolean isWin(){
        return isWin;
    }

    public int getPrize(){
        return prize;
    }


    @Override
    public String toString(){
        return "Id:" + id
                + ", player token: "
                + playerToken + ", bet: "
                + bet + ", is win: "
                + isWin + ", prize: "
                + prize + ", time:"
                + time;
    }
}
