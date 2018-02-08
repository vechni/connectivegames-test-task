package com.semyonov.model;

import java.util.Random;

public enum SideCoin
{
    HEAD,
    TAIL;

    public static SideCoin getRandomSideCoin(){
        final Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
