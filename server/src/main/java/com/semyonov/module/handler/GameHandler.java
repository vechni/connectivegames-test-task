package com.semyonov.module.handler;

import com.semyonov.data.socket.Bridge;
import com.semyonov.data.socket.BridgeImpl;
import com.semyonov.model.GameResult;
import com.semyonov.model.Player;
import com.semyonov.model.Request;
import com.semyonov.model.Response;
import com.semyonov.model.StatusConnection;
import com.semyonov.model.StatusRequest;
import com.semyonov.module.check.CheckManager;
import com.semyonov.module.game.GameController;
import com.semyonov.module.game.GameFiftyPercentWinController;
import com.semyonov.module.memory.MemoryController;
import com.semyonov.utils.GameUtils;

import java.net.Socket;

public class GameHandler
        extends Thread
        implements Handler
{
    private Bridge bridge;
    private GameController gameController = new GameFiftyPercentWinController(GameUtils.PRIZE_FACTOR);
    private CheckManager checkManager = new CheckManager();
    private MemoryController memoryController = new MemoryController();

    public GameHandler( final Socket socket ){
        bridge = new BridgeImpl(socket);
    }

    @Override
    public void execute(){
        start();
    }

    public void run(){
        if( bridge.openConnection() == StatusConnection.FAILED ){
            System.out.println("Could not support connection");
            return;
        }

        processGameAttempts();
        bridge.closeConnection();
    }

    private void processGameAttempts(){
        Response response;
        Request request;
        Player player;
        GameResult gameResult;
        String message;

        while( bridge.isOpenConnection() ){
            request = bridge.getRequest();

            // Check game is over
            if( request.isLast() ){
                break;
            }

            // Check Input param of game
            player = memoryController.getPlayer(request.getToken());
            if( checkManager.isNotCorrectParam(player, request, bridge) ){
                continue;
            }

            // Calculate of win
            gameResult = gameController.calculateWin(request);

            // Save result in memory
            memoryController.saveGame(gameResult);

            // Send result to client
            if( gameResult.isWin() ){
                message = GameUtils.MESSAGE_WIN;
            }else{
                message = GameUtils.MESSAGE_LOSS;
            }
            response =
                    new Response(StatusRequest.SUCCESS,
                                 message,
                                 gameResult.getBet(),
                                 gameResult.getPrize(),
                                 gameResult.isWin());
            bridge.sendResponse(response);
        }
    }
}
