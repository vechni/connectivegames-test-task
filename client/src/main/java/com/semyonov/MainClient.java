package com.semyonov;

import com.semyonov.common.utils.StressTestUtils;
import com.semyonov.module.factory.Factory;
import com.semyonov.module.factory.StressTestFactory;
import com.semyonov.module.input.OperatorInterface;
import com.semyonov.module.worker.Worker;

public class MainClient
{
    private final static int INTERVAL_BETWEEN_CREATE_PLAYERS = 5;

    public static void main( String[] args ){

        OperatorInterface operatorInterface = new OperatorInterface();
        operatorInterface.readParams();

        final Factory factory = new StressTestFactory();
        for( int i = 0; i < StressTestUtils.countPlayers; i++ ){
            final Worker worker = factory.create();
            worker.execute();
            delay();
        }
    }

    private static void delay(){
        try{
            Thread.sleep(INTERVAL_BETWEEN_CREATE_PLAYERS);
        }catch( InterruptedException e ){
            e.printStackTrace();
        }
    }
}
