package panierconnecte.ocs.mobileapp.views.slide;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.utilities.ApiCaller;


public class SlideFragment2 extends Fragment {

    private Button pairButton;
    private SharedPreferences sharedPreferences;
    private EditText nameEdittext;
    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null)
            return null;
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_slide_fragment2, container, false);

        return rootView;
    }
}
