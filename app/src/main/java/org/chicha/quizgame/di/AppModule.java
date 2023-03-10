package org.chicha.quizgame.di;

import android.app.Application;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import org.chicha.quizgame.BuildConfig;
import org.chicha.quizgame.data.repository.AnswersRepo;
import org.chicha.quizgame.data.repository.QuizzesRepo;
import org.chicha.quizgame.data.repository.StatsRepo;
import org.chicha.quizgame.data.repository.impl.AnswersRepoImpl;
import org.chicha.quizgame.data.repository.impl.QuizzesRepoImpl;
import org.chicha.quizgame.data.repository.impl.StatsRepoImpl;
import org.chicha.quizgame.database.QuizzesDatabase;
import org.chicha.quizgame.domain.viewmodel.AnswersViewModel;
import org.chicha.quizgame.domain.viewmodel.QuizzesViewModel;
import org.chicha.quizgame.domain.viewmodel.StatsViewModel;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    private static final String URL = BuildConfig.QUIZZES_URL;
    private static final String DATABASE_NAME = BuildConfig.QUIZZES_DB_NAME;

    // Respect the nomenclature des methods
    // prefixed par "provide"

    @Provides
    @Singleton
    public static QuizzesDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, QuizzesDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    // repositories
    @Provides
    @Singleton
    public static QuizzesRepo provideQuizzesRepo(QuizzesDatabase database) {
        return new QuizzesRepoImpl(database.quizzesDao());
    }

    @Provides
    @Singleton
    public static AnswersRepo provideAnswersRepo(QuizzesDatabase database) {
        return new AnswersRepoImpl(database.answersDao());
    }

    @Provides
    @Singleton
    public static StatsRepo provideStatsRepo(QuizzesDatabase database) {
        return new StatsRepoImpl(database.statsDao());
    }

    // ViewModels
    @Provides
    @Singleton
    public static QuizzesViewModel provideQuizzesViewModel(QuizzesRepo quizzesRepo) {
        return new QuizzesViewModel(quizzesRepo);
    }

    @Provides
    @Singleton
    public static AnswersViewModel provideAnswersViewModel(AnswersRepo answersRepo) {
        return new AnswersViewModel(answersRepo);
    }

    @Provides
    @Singleton
    public static StatsViewModel provideStatsViewModel(StatsRepo statsRepo) {
        return new StatsViewModel(statsRepo);
    }

}
