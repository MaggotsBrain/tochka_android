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
import java.util.Map;

import social.tochka.android.R;


public class MerchTabFragment extends Fragment {

    private String[] mGroupsArray = new String[] {"ОДЕЖДА", "АКСЕССУАРЫ", "ЮВЕЛИРКА"};

    private String[] mClothesArray = new String[] { "ЛОНГСЛИВЫ", "ФУТБОЛКИ", "ХУДИ" };
    private String[] mAccessoriesArray = new String[] { "ШОППЕРЫ", "НОСКИ", "ЗНАЧКИ" };
    private String[] mJeweleryArray = new String[] { "КОЛЬЦА", "БРАСЛЕТЫ" };

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

        Map<String, String> map;
        // коллекция для групп
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : mGroupsArray) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] { "groupName" };
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] { android.R.id.text1 };

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы
        ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
        // заполняем список атрибутов для каждого элемента
        for (String clothes : mClothesArray) {
            map = new HashMap<>();
            map.put("itemName", clothes); // название месяца
            сhildDataItemList.add(map);
        }
        // добавляем в коллекцию коллекций
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для второй группы
        сhildDataItemList = new ArrayList<>();
        for (String accessory : mAccessoriesArray) {
            map = new HashMap<>();
            map.put("itemName", accessory);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для третьей группы
        сhildDataItemList = new ArrayList<>();
        for (String jewelery : mJeweleryArray) {
            map = new HashMap<>();
            map.put("itemName", jewelery);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);


        // список атрибутов элементов для чтения
        String childFrom[] = new String[] { "itemName" };
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[] { android.R.id.text1 };

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getContext(), groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, сhildDataList, android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.merch_tab_list_view);
        expandableListView.setAdapter(adapter);


        expandableListView.setIndicatorBoundsRelative(600, 700);

        return view;
    }


}
