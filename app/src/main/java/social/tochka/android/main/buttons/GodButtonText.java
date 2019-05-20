package social.tochka.android.main.buttons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.View;

import social.tochka.android.R;
import social.tochka.android.main.MapsActivity;

public class GodButtonText extends View {

    Paint p;
    Path path;

    Typeface typeface;

    String degree_latitude;
    String minutes_latitude;
    String seconds_latitude;

    String degree_longitude;
    String minutes_longitude;
    String seconds_longitude;

    String vertical_line = "|";
    String degree_symbol = "°";
    String minutes_symbol = "′";
    String second_symbol = "″";
    String longitude_symbol = "E";
    String latitude_symbol = "N";

    public GodButtonText(Context context, Typeface typeface) {
        super(context);
        this.typeface = typeface;
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(2);
        p.setTextSize(70);
        path = new Path();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {

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

        path.reset();
        path.addCircle(539, 1492, 115, Path.Direction.CW);

        int voff = -20;

        p.setTypeface(Typeface.DEFAULT_BOLD);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(vertical_line, path, 265, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(vertical_line, path, 625, voff, p);

        p.setTextSize(60);
        p.setTypeface(typeface);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(degree_longitude, path, 655, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(degree_symbol, path, 0, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(minutes_longitude, path, 30, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(minutes_symbol, path, 95, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(seconds_longitude, path, 120, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(second_symbol, path, 190, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(longitude_symbol, path, 220, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(degree_latitude, path, 285, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(degree_symbol, path, 352, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(minutes_latitude, path, 379, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(minutes_symbol, path, 451, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(seconds_latitude, path, 476, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(second_symbol, path, 546, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(latitude_symbol, path, 579, voff, p);
    }
}
