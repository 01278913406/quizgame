package org.chicha.quizgame.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.chicha.quizgame.BuildConfig;
import org.chicha.quizgame.data.data_source.local.dao.AnswersDao;
import org.chicha.quizgame.data.data_source.local.dao.QuizzesDao;
import org.chicha.quizgame.data.data_source.local.dao.StatsDao;
import org.chicha.quizgame.domain.entity.Answers;
import org.chicha.quizgame.domain.entity.QuizEntity;
import org.chicha.quizgame.domain.entity.StatEntity;

@Database(entities = { QuizEntity.class, Answers.class, StatEntity.class }, version = 2, exportSchema = false)
public abstract class QuizzesDatabase extends RoomDatabase {

    public abstract QuizzesDao quizzesDao();
    public abstract AnswersDao answersDao();
    public abstract StatsDao statsDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
}
