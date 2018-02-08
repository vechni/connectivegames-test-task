package com.semyonov.module.worker.game;

import com.semyonov.common.utils.StressTestUtils;
import com.semyonov.data.socket.Client;
import com.semyonov.data.socket.ClientImpl;
import com.semyonov.model.PlayerStatistic;
import com.semyonov.model.Request;
import com.semyonov.model.Response;
import com.semyonov.model.SideCoin;
import com.semyonov.model.StatusConnection;
import com.semyonov.module.statistic.Statistic;
import com.semyonov.module.statistic.StatisticImpl;
import com.semyonov.module.worker.Worker;
import com.semyonov.utils.ClassNameUtils;
import com.semyonov.utils.GameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameLaunchWorker
        extends Thread
        implements Worker
{
    private static final String EMPTY_STRING = "";
    private static final String MESSAGE_NO_CONNECTION = "Could not open connection";

    private String token;
    private PlayerEmulator player = new PlayerEmulator(GameUtils.START_COUNT_COINS);
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    private final Logger log = LoggerFactory.getLogger(ClassNameUtils.getCurrentClassName());
    private Client client;
    private Statistic statistic = StatisticImpl.getInstance();

    public GameLaunchWorker(){
        token = player.getToken();
    }

    @Override
    public void execute(){
        start();
    }

    @Override
    public void run(){
        client = new ClientImpl();
        if( client.openConnection() == StatusConnection.FAILED ){
            makeReportOfGame(MESSAGE_NO_CONNECTION);
            return;
        }

        emulateGame();
        client.closeConnection();
    }

    private void emulateGame(){
        Response response;
        for( int i = 1; i <= StressTestUtils.countAttempts; i++ ){
            final Request request = generateParamOfGame();
            client.sendRequest(request);

            response = client.getResponse();
            player.updateBalance(response);

            if( isLastRequest(request, i) ){
                break;
            }

            // Time interval between of requests
            try{
                Thread.sleep(StressTestUtils.timeInterval);
            }catch( InterruptedException e ){
                log.error("Time interval between of requests", e);
            }
        }
        makeReportOfGame(EMPTY_STRING);
    }

    private void makeReportOfGame( final String message ){
        final double averageTime = client.getAverageTimeRequest();
        final int countSuccessRequest = client.getCountSuccessRequest();
        final int countFailedRequest = client.getCountFailedRRequest();
        final PlayerStatistic playerStatistic = new PlayerStatistic(token, countSuccessRequest, countFailedRequest, averageTime, message);
        statistic.saveStatisticPlayer(playerStatistic);
        statistic.showStatisticPlayer(playerStatistic);
    }

    private Request generateParamOfGame(){
        final int bet = player.placeBet();
        final SideCoin sideCoin = player.chooseSideCoin();
        final String time = dateFormat.format(Calendar.getInstance()
                                                      .getTime());
        boolean isLastRequest = false;
        if( bet == 0 ){
            isLastRequest = true;
        }
        return new Request(token, time, bet, sideCoin, isLastRequest);
    }

    private boolean isLastRequest( final Request request, final int attempt ){
        if( request.isLast() ){
            return true;
        }

        if( attempt == StressTestUtils.countAttempts ){
            final Request lastRequest = new Request(player.getToken(), EMPTY_STRING, 0, SideCoin.HEAD, true);
            client.sendRequest(lastRequest);
            return true;
        }

        return false;
    }
}
