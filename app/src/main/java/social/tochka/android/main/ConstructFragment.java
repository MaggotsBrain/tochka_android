package social.tochka.android.main;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import social.tochka.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConstructFragment extends Fragment {


    public ConstructFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_construct_splash, container, false);
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }
}
