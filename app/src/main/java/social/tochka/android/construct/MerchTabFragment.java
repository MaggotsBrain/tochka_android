package social.tochka.android.construct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
import social.tochka.android.construct.dummy.DummyContent;


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

        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getContext(),
                listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d("TAG","group :" + listDataHeader.get(groupPosition) + "  ,child : "
                        + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) );

                // clicked on shoppers
                // show fragment with list
                // list contains images and something else

                expandableListView.setVisibility(View.GONE);

                return loadFragment(new MerchListFragment());
            }
        });

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


    private boolean loadFragment(Fragment fragment) {

        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
