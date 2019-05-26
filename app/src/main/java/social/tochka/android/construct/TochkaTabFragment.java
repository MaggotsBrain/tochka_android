package social.tochka.android.construct;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import social.tochka.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TochkaTabFragment extends Fragment {


    public TochkaTabFragment() {
        // Required empty public constructor
    }

    public static TochkaTabFragment newInstance() {
        TochkaTabFragment fragment = new TochkaTabFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tochka_tab, container, false);
    }

}
