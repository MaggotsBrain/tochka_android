package social.tochka.android.construct;


import android.content.Context;
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

    private anotherOnListInteractionListener mListener;


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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof anotherOnListInteractionListener) {
            mListener = (anotherOnListInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface anotherOnListInteractionListener {
        // TODO: Update argument type and name
        void anotherOnListFragmentInteraction(String item);
    }

}
