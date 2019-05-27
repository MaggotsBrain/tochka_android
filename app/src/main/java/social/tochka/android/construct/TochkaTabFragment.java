package social.tochka.android.construct;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;
import java.util.UUID;

import social.tochka.android.R;
import social.tochka.android.main.cards.TochkaCard;
import social.tochka.android.main.util.MarkerSaver;

/**
 * A simple {@link Fragment} subclass.
 */
public class TochkaTabFragment extends Fragment {

    private GoogleMap mMap;

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
        View view = inflater.inflate(R.layout.fragment_tochka_tab, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.construct_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapStyle(
                            MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));
                    mMap = googleMap;
                    mMap.setInfoWindowAdapter(new CustomCunstructInfoWindowAdapter(getContext()));
                    mMap.getUiSettings().setMapToolbarEnabled(false);

                    for (Map.Entry<UUID, TochkaCard> entry : MarkerSaver.tochkaMap.entrySet()) {
                        mMap.addMarker(new MarkerOptions()
                                .position(entry.getValue().getLatLng())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tochka))
                                .title(entry.getKey().toString()));

                    }
                }
            });
        }
        return view;
    }
}
