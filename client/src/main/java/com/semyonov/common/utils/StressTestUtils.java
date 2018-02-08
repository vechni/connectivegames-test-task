package com.semyonov.common.utils;

public class StressTestUtils
{
    public static int countPlayers;
    public static int countAttempts;
    public static int timeInterval;

    public static void init( final int paramCountPlayers, final int paramCountAttempts, final int paramTimeInterval ){
        countPlayers = paramCountPlayers;
        countAttempts = paramCountAttempts;
        timeInterval = paramTimeInterval;
    }
}
