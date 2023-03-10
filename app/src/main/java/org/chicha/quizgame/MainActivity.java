package org.chicha.quizgame;

import static org.chicha.quizgame.Game.GAME_NOTIFICATION;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.Objects;

import javax.inject.Inject;

import org.chicha.quizgame.databinding.ActivityMainBinding;
import org.chicha.quizgame.domain.viewmodel.StatsViewModel;
import org.chicha.quizgame.ui.fragments.Home;
import org.chicha.quizgame.ui.fragments.Pregame;
import org.chicha.quizgame.ui.fragments.Stats;
import org.chicha.quizgame.utils.Menu;
import dagger.hilt.android.AndroidEntryPoint;

import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.VideoListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MeowBottomNavigation navBar;

    @Inject
    public StatsViewModel statsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StartAppSDK.setTestAdsEnabled(BuildConfig.DEBUG);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initScreenConfig();
        configureNavigationBar();
        checkBundleData();

    }

    private void initScreenConfig() {
        // Hide the status bar. and make the app fullscreen
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // Configuerer la barre de navigation avec l'icone correspondante
    private void configureNavigationBar() {
        navBar = binding.navBar;
        navBar.add(new MeowBottomNavigation.Model(Menu.HOME.getId(),R.drawable.ic_home));
        navBar.add(new MeowBottomNavigation.Model(Menu.PREGAME.getId(),R.drawable.ic_game));
        navBar.add(new MeowBottomNavigation.Model(Menu.STATS.getId(), R.drawable.ic_score));

        // switch pour afficher le fragment correspondant ?? l'icone selectionn??e
        navBar.setOnShowListener(item -> {
            Fragment fragment = null;
            switch(item.getId()){
                case 1:
                    fragment = new Home(this);
                    break;
                case 2:
                    fragment = new Pregame(this);
                    break;
                case 3:
                    fragment = new Stats(this, statsViewModel);
                    break;
            }
            assert fragment != null;
            showFragment(this, fragment);
        });

        // Afficher le fragment Home par d??faut
        changeNavBar(Menu.HOME,true);
        navBar.setOnClickMenuListener(item -> {});
        navBar.setOnReselectListener(item -> {
            if(item.getId() == Menu.HOME.getId()){
                showFragment(this, new Home(this));
            }
        });
    }

    private void checkBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;

        int menu = bundle.getInt(GAME_NOTIFICATION);
        if (menu != 0 && menu == Menu.STATS.getId()) {
            changeNavBar(Menu.STATS, false);
            showFragment(this, new Stats(this, statsViewModel));
        }
    }

    public void showFragment(MainActivity context, Fragment fragment) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeNavBar(Menu menu, boolean animation) {
        navBar.show(menu.getId(), animation);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmExit)
                .setCancelable(false)
                .setPositiveButton(R.string.confirmYes, (dialog, which) -> MainActivity.super.onBackPressed())
                .setNegativeButton(R.string.confirmNo, (dialog, which) -> dialog.cancel())
                .create()
                .show();
    }
}