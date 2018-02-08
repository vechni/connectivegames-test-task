package com.semyonov.model;

public class Player
{
    private String token;
    private int coins;

    public Player( final String token, final int coins ){
        this.token = token;
        this.coins = coins;
    }

    public String getToken(){
        return token;
    }

    public int getCoins(){
        return coins;
    }

    @Override
    public String toString(){
        return "Token: " + token + ", coins: " + coins;
    }
}
