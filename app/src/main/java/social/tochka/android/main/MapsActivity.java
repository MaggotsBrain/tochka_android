package social.tochka.android.main;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import social.tochka.android.construct.ConstructFragment;
import social.tochka.android.construct.ConstructSplashActivity;
import social.tochka.android.R;
import social.tochka.android.main.buttons.GodButtonText;
import social.tochka.android.main.cards.RVAdapter;
import social.tochka.android.main.cards.TochkaCard;
import social.tochka.android.main.util.CoordinatesConverter;
import social.tochka.android.main.util.CustomInfoWindowAdapter;
import social.tochka.android.main.util.MarkerSaver;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private boolean godButtonClick;
    private boolean profileVisible;
    private boolean godTextWasVisible;
    private GodButtonText godButtonText;
    private EditText storyText;

    private List<TochkaCard> cards;
    private RecyclerView rv;

    public static int xGod;
    public static int yGod;

    public static String latitude = "-16:42:45,02561";
    public static String longitude = "49:13:53,22818";


    // klimenco
    private ImageView mConstructImageView;

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


        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        addContentView(godButtonText, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        final EditText passwordEditText = findViewById(R.id.search);
        storyText = findViewById(R.id.add_story);
        storyText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeData();
        initializeAdapter();

        mConstructImageView = findViewById(R.id.construct);
        mConstructImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, ConstructSplashActivity.class);
                startActivity(intent);
            }
        });
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
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                xGod = (int) findViewById(R.id.kostil).getX();
                yGod = (int) findViewById(R.id.kostil).getY();
                LatLng target = mMap.getCameraPosition().target;
                latitude = Location.convert(target.latitude, Location.FORMAT_SECONDS);
                longitude = Location.convert(target.longitude, Location.FORMAT_SECONDS);
                godButtonText.invalidate();

            }
        });
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);

    }

    public void onClickGodButton(View view) {
        if (godButtonClick) {
            findViewById(R.id.tochka_image).setVisibility(View.INVISIBLE);
            findViewById(R.id.add_window).setVisibility(View.VISIBLE);
            godButtonText.setVisibility(View.INVISIBLE);
            godButtonClick = false;
        } else {
            findViewById(R.id.tochka_image).setVisibility(View.VISIBLE);
            godButtonText.setVisibility(View.VISIBLE);
            godButtonClick = true;
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

    public void onClickFirstTochka(View view) {
        findViewById(R.id.tochka_image).setVisibility(View.VISIBLE);
        godButtonText.setVisibility(View.VISIBLE);
        godButtonClick = true;
        findViewById(R.id.profile_layout).setVisibility(View.INVISIBLE);
        findViewById(R.id.profile_layout).setClickable(false);
        findViewById(R.id.god_button).setClickable(true);
        profileVisible = false;
        if (godTextWasVisible) {
            godButtonText.setVisibility(View.VISIBLE);
            godTextWasVisible = false;
        }
    }

    public void onClickAddCancel(View view) {
        storyText.getText().clear();
        findViewById(R.id.tochka_image).setVisibility(View.INVISIBLE);
        findViewById(R.id.add_window).setVisibility(View.INVISIBLE);
        godButtonText.setVisibility(View.INVISIBLE);
        godButtonClick = false;
        view.clearFocus();
    }

    public void onClickAddLock(View view) {
    }

    public void onClickAddApprove(View view) {
        LatLng target = mMap.getCameraPosition().target;
        latitude = Location.convert(target.latitude, Location.FORMAT_SECONDS);
        longitude = Location.convert(target.longitude, Location.FORMAT_SECONDS);
        Map<String, String> coordinates = CoordinatesConverter.toMap(latitude, longitude);

        TochkaCard tochkaCard = TochkaCard.builder()
                .username("sweet_child")
                .latitudeDegree(coordinates.get("degree_latitude"))
                .latitudeMinutes(coordinates.get("minutes_latitude"))
                .latitudeSeconds(coordinates.get("seconds_latitude"))
                .latitudeSymbol(coordinates.get("latitude_symbol"))
                .longitudeDegree(coordinates.get("degree_longitude"))
                .longitudeMinutes(coordinates.get("minutes_longitude"))
                .longitudeSeconds(coordinates.get("seconds_longitude"))
                .longitudeSymbol(coordinates.get("longitude_symbol"))
                .text(storyText.getText().toString())
                .build();
        cards.add(tochkaCard);
        UUID uuid = UUID.randomUUID();
        MarkerSaver.tochkaMap.put(uuid, tochkaCard);

        TextView latitude_degree_digit = findViewById(R.id.add_latitude_degree_digit);
        latitude_degree_digit.setText(coordinates.get("degree_latitude"));
        TextView latitude_minute_digit = findViewById(R.id.add_latitude_minute_digit);
        latitude_minute_digit.setText(coordinates.get("minutes_latitude"));
        TextView latitude_seconds_digit = findViewById(R.id.add_latitude_seconds_digit);
        latitude_seconds_digit.setText(coordinates.get("seconds_latitude"));
        TextView latitude_symbol = findViewById(R.id.add_latitude_symbol);
        latitude_symbol.setText(coordinates.get("latitude_symbol"));

        TextView longitude_degree_digit = findViewById(R.id.add_longitude_degree_digit);
        longitude_degree_digit.setText(coordinates.get("degree_longitude"));
        TextView longitude_minute_digit = findViewById(R.id.add_longitude_minute_digit);
        longitude_minute_digit.setText(coordinates.get("minutes_longitude"));
        TextView longitude_seconds_digit = findViewById(R.id.add_longitude_seconds_digit);
        longitude_seconds_digit.setText(coordinates.get("seconds_longitude"));
        TextView longitude_symbol = findViewById(R.id.add_longitude_symbol);
        longitude_symbol.setText(coordinates.get("longitude_symbol"));

        mMap.addMarker(new MarkerOptions()
                .position(target)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tochka))
                .title(uuid.toString()));

        storyText.getText().clear();
        findViewById(R.id.add_window).setVisibility(View.INVISIBLE);

        Objects.requireNonNull(rv.getAdapter()).notifyDataSetChanged();

        godButtonClick = false;

        findViewById(R.id.first_tochka_layout).setVisibility(View.GONE);

        view.clearFocus();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initializeData() {
        cards = new ArrayList<>();
    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);
    }

}
