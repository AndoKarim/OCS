package panierconnecte.ocs.mobileapp.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.github.paolorotolo.appintro.AppIntro;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

public class SlideActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Note here that we DO NOT use setContentView();

        Fragment slideFragment1 = new SlideFragment1();
        Fragment slideFragment2 = new SlideFragment2();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(slideFragment1);
        addSlide(slideFragment2);

        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));


        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", this.MODE_PRIVATE);
        EditText nameEdittext = findViewById(R.id.panierNameEdittext);
        String name = nameEdittext.getText().toString();

        String ip = sharedPreferences.getString("BoxIP", null);
        ApiCaller.addPanier(ip, name);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}