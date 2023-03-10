package org.chicha.quizgame.data.data_source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import org.chicha.quizgame.domain.entity.QuizEntity;

@Dao
public interface QuizzesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuiz(QuizEntity quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuizzes(QuizEntity... quizzes);

    @Query("SELECT * FROM quizzesTable WHERE correct_answer NOT NULL GROUP BY category, id")
    LiveData<List<QuizEntity>> getAllQuizzes();

    @Query("SELECT * FROM quizzesTable WHERE correct_answer NOT NULL AND tags LIKE '%' || :tags || '%' ORDER BY RANDOM() LIMIT 10")
    LiveData<List<QuizEntity>> getQuizzesByCategory(String tags);

    @Query("SELECT * FROM quizzesTable WHERE id = :id")
    QuizEntity getQuizById(long id);

    @Query("DELETE FROM quizzesTable")
    void deleteAllQuizzes();

    @Query("DELETE FROM quizzesTable WHERE id = :id")
    void deleteQuiz(long id);

    @Query("SELECT COUNT(*) FROM quizzesTable WHERE correct_answer NOT NULL AND tags LIKE '%' || :tags || '%'")
    int getSizeQuizzesTags(String tags);

    @Query("SELECT 1")
    int start();
}
