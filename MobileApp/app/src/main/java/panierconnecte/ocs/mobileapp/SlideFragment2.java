package panierconnecte.ocs.mobileapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import panierconnecte.ocs.mobileapp.utilities.ApiCaller;


public class SlideFragment2 extends Fragment {

    private Button pairButton;
    private SharedPreferences sharedPreferences;
    private EditText nameEdittext;
    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_slide_fragment2, container, false);


        sharedPreferences = this.getActivity().getSharedPreferences("prefs", this.getActivity().MODE_PRIVATE);
        nameEdittext = getView().findViewById(R.id.panierNameEdittext);
        pairButton = getView().findViewById(R.id.pairButton);
        name = nameEdittext.getText().toString();

        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = sharedPreferences.getString("BoxIP", null);
                ApiCaller.addPanier(ip, name);

            }
        });
        return rootView;
    }
}
