package social.tochka.android.main;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import social.tochka.android.R;
import social.tochka.android.main.buttons.GodButtonText;
import social.tochka.android.main.cards.RVAdapter;
import social.tochka.android.main.cards.TochkaCard;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private boolean godButtonClick;
    private boolean profileVisible;
    private boolean godTextWasVisible;
    private GodButtonText godButtonText;
    public static String latitude;
    public static String longitude;

    private List<TochkaCard> cards;
    private RecyclerView rv;

    private String degree_latitude;
    private String minutes_latitude;
    private String seconds_latitude;
    private String degree_longitude;
    private String minutes_longitude;
    private String seconds_longitude;
    private String longitude_symbol = "E";
    private String latitude_symbol = "N";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface tf = ResourcesCompat.getFont(getApplicationContext(), R.font.overpass_mono_bold);
        godButtonClick = false;
        godTextWasVisible = false;
        godButtonText = new GodButtonText(this, tf);
        godButtonText.setVisibility(View.INVISIBLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        addContentView(godButtonText, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        final EditText passwordEditText = findViewById(R.id.search);
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeData();
        initializeAdapter();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_style));
        mMap = googleMap;

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLng target = mMap.getCameraPosition().target;
                latitude = Location.convert(target.latitude, Location.FORMAT_SECONDS);
                longitude = Location.convert(target.longitude, Location.FORMAT_SECONDS);
                godButtonText.invalidate();

            }
        });

        // Add a marker in Sydney and move the camera
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        mMap.setMyLocationEnabled(true);
    }

    public void onClickGodButton(View view) {

        if (godButtonClick) {
            findViewById(R.id.tochka_image).setVisibility(View.INVISIBLE);

            LatLng target = mMap.getCameraPosition().target;
            latitude = Location.convert(target.latitude, Location.FORMAT_SECONDS);
            longitude = Location.convert(target.longitude, Location.FORMAT_SECONDS);

            String[] lats = MapsActivity.latitude.split(":");

            if (lats[0].contains("-")) {
                latitude_symbol = "S";
                degree_latitude = lats[0].replace("-", "");
                if (degree_latitude.length() < 2)
                    degree_latitude = "0" + degree_latitude;
            } else {
                latitude_symbol = "N";
                degree_latitude = lats[0];
                if (degree_latitude.length() < 2)
                    degree_latitude = "0" + degree_latitude;
            }
            minutes_latitude = lats[1];
            if (minutes_latitude.length() < 2)
                minutes_latitude = "0" + minutes_latitude;
            seconds_latitude = lats[2].split(",")[0];
            if (seconds_latitude.length() < 2)
                seconds_latitude = "0" + seconds_latitude;

            String[] longs = MapsActivity.longitude.split(":");
            if (longs[0].contains("-")) {
                longitude_symbol = "W";
                degree_longitude = longs[0].replace("-", "");
                if (degree_longitude.length() < 2)
                    degree_longitude = "0" + degree_longitude;
            } else {
                longitude_symbol = "E";
                degree_longitude = longs[0];
                if (degree_longitude.length() < 2)
                    degree_longitude = "0" + degree_longitude;
            }
            minutes_longitude = longs[1];
            if (minutes_longitude.length() < 2)
                minutes_longitude = "0" + minutes_longitude;
            seconds_longitude = longs[2].split(",")[0];
            if (seconds_longitude.length() < 2)
                seconds_longitude = "0" + seconds_longitude;

            cards.add(TochkaCard.builder()
                    .latitudeDegree(degree_latitude)
                    .latitudeMinutes(minutes_latitude)
                    .latitudeSeconds(seconds_latitude)
                    .latitudeSymbol(latitude_symbol)
                    .longitudeDegree(degree_longitude)
                    .longitudeMinutes(minutes_longitude)
                    .longitudeSeconds(seconds_longitude)
                    .longitudeSymbol(longitude_symbol)
                    .text("Щас бы...в кроватку..")
                    .build());

            mMap.addMarker(new MarkerOptions()
                    .position(target)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.tochka))
                    .title("Щас бы...в кроватку.."));

            rv.getAdapter().notifyDataSetChanged();
            
            godButtonClick = false;
            godButtonText.setVisibility(View.INVISIBLE);

        } else {
            godButtonClick = true;
            findViewById(R.id.tochka_image).setVisibility(View.VISIBLE);
            godButtonText.setVisibility(View.VISIBLE);
        }
    }

    public void onClickProfileButton(View view) {

        if (profileVisible) {
            findViewById(R.id.profile_layout).setVisibility(View.INVISIBLE);
            findViewById(R.id.profile_layout).setClickable(false);
            findViewById(R.id.god_button).setClickable(true);
            profileVisible = false;
            if (godTextWasVisible) {
                godButtonText.setVisibility(View.VISIBLE);
                godTextWasVisible = false;
            }
        } else {
            profileVisible = true;
            findViewById(R.id.profile_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.profile_layout).setClickable(true);
            findViewById(R.id.god_button).setClickable(false);
            if (godButtonText.getVisibility() == View.VISIBLE) {
                godButtonText.setVisibility(View.INVISIBLE);
                godTextWasVisible = true;
            }
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initializeData() {

        cards = new ArrayList<>();
        cards.add(TochkaCard.builder()
                .latitudeDegree("55")
                .latitudeMinutes("23")
                .latitudeSeconds("25")
                .latitudeSymbol("N")
                .longitudeDegree("25")
                .longitudeMinutes("47")
                .longitudeSeconds("38")
                .longitudeSymbol("E")
                .text("Ахуительная история о том, как две самовлюбленных личности поняли, что как-то необходимо доказать свою илитарность. Для этого им приходитя каждый вечер покупать поднимающие дух напитки и творить!")
                .build());
    }


    private void initializeAdapter() {

        RVAdapter adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);

    }
}
