package org.chicha.quizgame.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import org.chicha.quizgame.domain.entity.StatEntity;

public interface StatsRepo {
    LiveData<List<StatEntity>> getAllStats();
    LiveData<List<StatEntity>> getStatsByTag(String tag);
    void insertStats(StatEntity stat);
    void insertStats(StatEntity... stats);
    void deleteStats();
    void deleteStatsById(int id);
}
