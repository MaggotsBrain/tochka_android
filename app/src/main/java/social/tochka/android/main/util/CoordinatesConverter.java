package social.tochka.android.main.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CoordinatesConverter {

    public static Map<String, String> toMap(String latitude, String longitude) {

        latitude = latitude.replace(".", ",");
        longitude = longitude.replace(".", ",");

        Map<String, String> coordinates = new HashMap<>();

        String[] lats = latitude.split(":");
        if (lats[0].contains("-")) {
            coordinates.put("latitude_symbol", "S");
            coordinates.put("degree_latitude", lats[0].replace("-", ""));
            if (Objects.requireNonNull(coordinates.get("degree_latitude")).length() < 2)
                coordinates.put("degree_latitude", "0" + coordinates.get("degree_latitude"));
        } else {
            coordinates.put("latitude_symbol", "N");
            coordinates.put("degree_latitude", lats[0]);
            if (Objects.requireNonNull(coordinates.get("degree_latitude")).length() < 2)
                coordinates.put("degree_latitude", "0" + coordinates.get("degree_latitude"));
        }
        coordinates.put("minutes_latitude", lats[1]);
        if (Objects.requireNonNull(coordinates.get("minutes_latitude")).length() < 2)
            coordinates.put("minutes_latitude", "0" + coordinates.get("minutes_latitude"));
        coordinates.put("seconds_latitude", lats[2].split(",")[0]);
        if (Objects.requireNonNull(coordinates.get("seconds_latitude")).length() < 2)
            coordinates.put("seconds_latitude", "0" + coordinates.get("seconds_latitude"));

        String[] longs = longitude.split(":");

        if (longs[0].contains("-")) {
            coordinates.put("longitude_symbol", "W");
            coordinates.put("degree_longitude", longs[0].replace("-", ""));
            if (Objects.requireNonNull(coordinates.get("degree_longitude")).length() < 2)
                coordinates.put("degree_longitude", "0" + coordinates.get("degree_longitude"));
        } else {
            coordinates.put("longitude_symbol", "E");
            coordinates.put("degree_longitude", longs[0]);
            if (Objects.requireNonNull(coordinates.get("degree_longitude")).length() < 2)
                coordinates.put("degree_longitude", "0" + coordinates.get("degree_longitude"));
        }
        coordinates.put("minutes_longitude", longs[1]);
        if (Objects.requireNonNull(coordinates.get("minutes_longitude")).length() < 2)
            coordinates.put("minutes_longitude", "0" + coordinates.get("minutes_longitude"));
        coordinates.put("seconds_longitude", longs[2].split(",")[0]);
        if (Objects.requireNonNull(coordinates.get("seconds_longitude")).length() < 2)
            coordinates.put("seconds_longitude", "0" + coordinates.get("seconds_longitude"));

        return coordinates;
    }
}
