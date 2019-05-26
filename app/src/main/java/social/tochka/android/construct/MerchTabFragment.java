package social.tochka.android.construct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import social.tochka.android.R;


public class MerchTabFragment extends Fragment {

    private ExpandableListView expandableListView;

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;


    public MerchTabFragment() {
        // Required empty public constructor
    }

    public static MerchTabFragment newInstance() {
        MerchTabFragment fragment = new MerchTabFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_merch_tab, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.merch_tab_list_view);

        expandableListView.setIndicatorBoundsRelative(600, 700);

        // preparing list data
        prepareListData();

        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getContext(),
                listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(expandableListAdapter);


        return view;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("T°ОДЕЖДА");
        listDataHeader.add("T°АКСЕССУАРЫ");
        listDataHeader.add("T°ЮВЕЛИРКА");

        // Adding child data
        List<String> clotheslist = new ArrayList<String>();
        clotheslist.add("ЛОНГСЛИВЫ");
        clotheslist.add("ХУДИ");
        clotheslist.add("ФУТБОЛКИ");

        List<String> accessoriesList = new ArrayList<String>();
        accessoriesList.add("ШОППЕРЫ");
        accessoriesList.add("ЗНАЧКИ");
        accessoriesList.add("НОСКИ");


        List<String> jewerlyList = new ArrayList<String>();
        jewerlyList.add("БРАСЛЕТЫ");
        jewerlyList.add("КОЛЬЦА");

        listDataChild.put(listDataHeader.get(0), clotheslist); // Header, Child data
        listDataChild.put(listDataHeader.get(1), accessoriesList);
        listDataChild.put(listDataHeader.get(2), jewerlyList);
    }

}
