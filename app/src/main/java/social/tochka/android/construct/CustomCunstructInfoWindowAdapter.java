package social.tochka.android.construct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.UUID;

import social.tochka.android.R;
import social.tochka.android.main.cards.TochkaCard;
import social.tochka.android.main.util.MarkerSaver;

public class CustomCunstructInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    public CustomCunstructInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.map_marker_construct_info_window, null);
    }

    private void rendowWindowText(Marker marker, View view) {

        String title = marker.getTitle();
        TochkaCard tochkaCard = MarkerSaver.tochkaMap.get(UUID.fromString(title));

        TextView latitude_degree_digit = mWindow.findViewById(R.id.info_latitude_degree_digit);
        latitude_degree_digit.setText(tochkaCard.getLatitudeDegree());
        TextView latitude_minute_digit = mWindow.findViewById(R.id.info_latitude_minute_digit);
        latitude_minute_digit.setText(tochkaCard.getLatitudeMinutes());
        TextView latitude_seconds_digit = mWindow.findViewById(R.id.info_latitude_seconds_digit);
        latitude_seconds_digit.setText(tochkaCard.getLatitudeSeconds());
        TextView latitude_symbol = mWindow.findViewById(R.id.info_latitude_symbol);
        latitude_symbol.setText(tochkaCard.getLatitudeSymbol());

        TextView longitude_degree_digit = mWindow.findViewById(R.id.info_longitude_degree_digit);
        longitude_degree_digit.setText(tochkaCard.getLongitudeDegree());
        TextView longitude_minute_digit = mWindow.findViewById(R.id.info_longitude_minute_digit);
        longitude_minute_digit.setText(tochkaCard.getLongitudeMinutes());
        TextView longitude_seconds_digit = mWindow.findViewById(R.id.info_longitude_seconds_digit);
        longitude_seconds_digit.setText(tochkaCard.getLongitudeSeconds());
        TextView longitude_symbol = mWindow.findViewById(R.id.info_longitude_symbol);
        longitude_symbol.setText(tochkaCard.getLongitudeSymbol());

        TextView username = mWindow.findViewById(R.id.info_username);
        username.setText(tochkaCard.getUsername());

        TextView story = mWindow.findViewById(R.id.info_story);
        story.setText(tochkaCard.getText());

    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}