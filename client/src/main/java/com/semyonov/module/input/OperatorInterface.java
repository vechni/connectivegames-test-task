package com.semyonov.module.input;

import com.semyonov.common.utils.StressTestUtils;

import java.util.Scanner;

public class OperatorInterface
{
    private final String MESSAGE_ENTER_PARAM_COUNT_PLAYERS = "Please enter amount of players:";
    private final String MESSAGE_ENTER_PARAM_COUNT_REQUESTS = "Please enter the max number of request of player:";
    private final String MESSAGE_ENTER_PARAM_INTERVAL_OF_TIME = "Interval of time between of requests (one player):";
    private final String ERROR_INVALID_FORMAT = "Error! Please enter the number.";

    private Scanner scanner = new Scanner(System.in);
    private Validation validation = new Validation();

    public void readParams(){
        int countPlayers = readParam(MESSAGE_ENTER_PARAM_COUNT_PLAYERS);
        int countRequest = readParam(MESSAGE_ENTER_PARAM_COUNT_REQUESTS);
        int intervalRequests = readParam(MESSAGE_ENTER_PARAM_INTERVAL_OF_TIME);
        StressTestUtils.init(countPlayers, countRequest, intervalRequests);
    }

    private int readParam( final String message ){
        int param;
        do{
            param = readValue(message);
        }while( validation.isNotCorrectParam(param) );
        return param;
    }

    private int readValue( final String name ){
        System.out.print(name);
        while( ! scanner.hasNextInt() ){
            System.out.println(ERROR_INVALID_FORMAT);
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}
