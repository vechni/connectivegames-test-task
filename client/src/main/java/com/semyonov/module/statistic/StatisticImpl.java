package com.semyonov.module.statistic;

import com.semyonov.model.PlayerStatistic;
import com.semyonov.module.report.Report;
import com.semyonov.module.report.ReportImpl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StatisticImpl
        implements Statistic
{
    private static StatisticImpl instance = null;
    private List<PlayerStatistic> statistic = new CopyOnWriteArrayList<>();
    private Report report = new ReportImpl();

    private StatisticImpl(){ }

    public static synchronized StatisticImpl getInstance(){
        if( instance == null ){
            instance = new StatisticImpl();
        }

        return instance;
    }


    @Override
    public void saveStatisticPlayer( final PlayerStatistic playerStatistic ){
        statistic.add(playerStatistic);
    }

    @Override
    public void showStatisticPlayer( final PlayerStatistic playerStatistic ){
        report.printStatisticPlayer(playerStatistic);
    }

    @Override
    public void showStatisticPlayers(){
        report.printStatisticPlayers(statistic);
    }
}
