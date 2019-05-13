package com.tochka.testmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class GodButtonText extends View {

    Paint p;
    Path path;

    String degree_n;
    String minutes_n;
    String seconds_n;

    String degree_e;
    String minutes_e;
    String seconds_e;

    String vertical_line = "|";
    String degree_symbol = "°";
    String minutes_symbol = "′";
    String second_symbol = "″";
    String longtitude_symbol = "E";
    String latitude_symbol = "N";

    public GodButtonText(Context context) {
        super(context);
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
            degree_n = lats[0].replace("-", "");
            if (degree_n.length() < 2)
                degree_n = "0" + degree_n;
        } else {
            latitude_symbol = "N";
            degree_n = lats[0];
            if (degree_n.length() < 2)
                degree_n = "0" + degree_n;
        }
        minutes_n = lats[1];
        if (minutes_n.length() < 2)
            minutes_n = "0" + minutes_n;
        seconds_n = lats[2].split(",")[0];
        if (seconds_n.length() < 2)
            seconds_n = "0" + seconds_n;

        String[] longs = MapsActivity.longtitude.split(":");
        if (longs[0].contains("-")) {
            longtitude_symbol = "W";
            degree_e = longs[0].replace("-", "");
            if (degree_e.length() < 2)
                degree_e = "0" + degree_e;
        } else {
            longtitude_symbol = "E";
            degree_e = longs[0];
            if (degree_e.length() < 2)
                degree_e = "0" + degree_e;
        }
        minutes_e = longs[1];
        if (minutes_e.length() < 2)
            minutes_e = "0" + minutes_e;
        seconds_e = longs[2].split(",")[0];
        if (seconds_e.length() < 2)
            seconds_e = "0" + seconds_e;

        path.reset();
        path.addCircle(539, 1492, 115, Path.Direction.CW);

        int voff = -20;

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(degree_e, path, 620, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(degree_symbol, path, 699, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(minutes_e, path, 5, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(minutes_symbol, path, 83, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(seconds_e, path, 95, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(second_symbol, path, 170, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(longtitude_symbol, path, 193, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(vertical_line, path, 240, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(degree_n, path, 265, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(degree_symbol, path, 342, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(minutes_n, path, 361, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(minutes_symbol, path, 438, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(seconds_n, path, 447, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(second_symbol, path, 522, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(latitude_symbol, path, 542, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(vertical_line, path, 600, voff, p);

    }
}
