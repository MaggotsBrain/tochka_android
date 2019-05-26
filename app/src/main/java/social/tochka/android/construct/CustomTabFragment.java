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
public class CustomTabFragment extends Fragment {


    public CustomTabFragment() {
        // Required empty public constructor
    }

    public static CustomTabFragment newInstance() {
        CustomTabFragment fragment = new CustomTabFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_tab, container, false);
    }

}
