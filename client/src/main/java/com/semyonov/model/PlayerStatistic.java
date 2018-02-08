package com.semyonov.model;

public class PlayerStatistic
{
    private String token;
    private int countSuccessRequest;
    private int countFailedRequest;
    private double averageTime;
    private String warning;

    public PlayerStatistic( final String token,
                            final int countSuccessRequest,
                            final int countFailedRequest,
                            final double averageTime,
                            final String warning ){
        this.token = token;
        this.countSuccessRequest = countSuccessRequest;
        this.countFailedRequest = countFailedRequest;
        this.averageTime = averageTime;
        this.warning = warning;
    }

    public int getCountSuccessRequest(){
        return countSuccessRequest;
    }

    public int getCountFailedRequest(){
        return countFailedRequest;
    }

    public double getAverageTime(){
        return averageTime;
    }

    public String getToken(){
        return token;
    }

    public String getWarning(){
        return warning;
    }

    @Override
    public String toString(){
        return "Token: " + token + ", Success: " + countFailedRequest + ", Failed: " + countFailedRequest + ", Average time: " +
                averageTime;
    }
}
