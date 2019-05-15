package social.tochka.android.main;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

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

import social.tochka.android.R;
import social.tochka.android.main.buttons.GodButtonText;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private boolean godButtonClick;
    private boolean profileVisible;
    private GodButtonText godButtonText;
    public static String latitude = "-16:42:45,02561";
    public static String longitude = "49:13:53,22818";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface tf = ResourcesCompat.getFont(getApplicationContext(), R.font.overpass_mono_bold);
        godButtonClick = false;
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
//            findViewById(R.id.god_button).setBackground(getResources().getDrawable(R.drawable.god_button));

//            Animation anim = new RotateAnimation(0.0f, 180.0f,
//                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//                    0.5f);
//            anim.setRepeatCount(0);
//            anim.setDuration(1000);
//            findViewById(R.id.god_button).startAnimation(anim);
//            TransitionDrawable transition = (TransitionDrawable) findViewById(R.id.god_button).getBackground();
//            transition.reverseTransition(300);

            LatLng target = mMap.getCameraPosition().target;
            mMap.addMarker(new MarkerOptions()
                    .position(mMap.getCameraPosition().target)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.tochka))
                    .title("Latitude: " + Location.convert(target.latitude, Location.FORMAT_SECONDS)
                            + "\n" + "Longitude: " + Location.convert(target.longitude, Location.FORMAT_SECONDS)));
            godButtonClick = false;
            godButtonText.setVisibility(View.INVISIBLE);

        } else {
            godButtonClick = true;
//            Animation anim = AnimationUtils.loadAnimation(this, R.anim.godbutton_anim);
//            Animation anim = new RotateAnimation(0.0f, 180.0f,
//                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//                    0.5f);
//            anim.setRepeatCount(0);
//            anim.setDuration(1000);
//            findViewById(R.id.god_button).startAnimation(anim);
//            TransitionDrawable transition = (TransitionDrawable) findViewById(R.id.god_button).getBackground();
//            transition.startTransition(300);
            findViewById(R.id.tochka_image).setVisibility(View.VISIBLE);
//            findViewById(R.id.god_button).setBackground(getResources().getDrawable(R.drawable.god_button_inverse));
            godButtonText.setVisibility(View.VISIBLE);
        }
    }

    public void onClickProfileButton(View view) {

        if (profileVisible) {
            findViewById(R.id.profile_layout).setVisibility(View.INVISIBLE);
            findViewById(R.id.profile_layout).setClickable(false);
            findViewById(R.id.god_button).setClickable(true);
            profileVisible = false;
        } else {
            profileVisible = true;
            findViewById(R.id.profile_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.profile_layout).setClickable(true);
            findViewById(R.id.god_button).setClickable(true);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

//    @Override
//    public void onCameraMove() {
//
//    }
}
