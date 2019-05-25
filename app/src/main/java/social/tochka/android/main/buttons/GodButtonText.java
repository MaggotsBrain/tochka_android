package social.tochka.android.main.buttons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import social.tochka.android.R;
import social.tochka.android.main.MapsActivity;
import social.tochka.android.main.util.CoordinatesConverter;

@SuppressLint("ViewConstructor")
public class GodButtonText extends View {

    Paint p;
    Path path;
    Typeface typeface;

    Map<String, String> coordinates;
    Map<String, String> symbols;

    {
        symbols = new HashMap<>();
        symbols.put("vertical_line", "|");
        symbols.put("degree_symbol", "°");
        symbols.put("minutes_symbol", "′");
        symbols.put("second_symbol", "″");

    }

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

        coordinates = CoordinatesConverter.toMap(MapsActivity.latitude, MapsActivity.longitude);

        path.reset();
        path.addCircle(MapsActivity.xGod, MapsActivity.yGod, 115, Path.Direction.CW);
        int voff = -20;


        p.setTypeface(Typeface.DEFAULT_BOLD);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("vertical_line")), path, 265, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("vertical_line")), path, 625, voff, p);

        p.setTextSize(60);
        p.setTypeface(typeface);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("degree_longitude")), path, 655, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("degree_symbol")), path, 0, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("minutes_longitude")), path, 30, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("minutes_symbol")), path, 95, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("seconds_longitude")), path, 120, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("second_symbol")), path, 190, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("longitude_symbol")), path, 220, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("degree_latitude")), path, 285, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("degree_symbol")), path, 352, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("minutes_latitude")), path, 379, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("minutes_symbol")), path, 451, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("seconds_latitude")), path, 476, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_symbol));
        canvas.drawTextOnPath(Objects.requireNonNull(symbols.get("second_symbol")), path, 546, voff, p);

        p.setColor(getResources().getColor(R.color.god_button_digit));
        canvas.drawTextOnPath(Objects.requireNonNull(coordinates.get("latitude_symbol")), path, 579, voff, p);
    }
}
