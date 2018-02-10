package panierconnecte.ocs.mobileapp.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import panierconnecte.ocs.mobileapp.R;


public class SlideFragment1 extends Fragment {


    public SlideFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_slide_fragment1, container, false);


        return rootView;
    }


}
