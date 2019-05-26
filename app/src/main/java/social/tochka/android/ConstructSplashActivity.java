package social.tochka.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import social.tochka.android.login.ui.LoginActivity;

public class ConstructSplashActivity extends AppCompatActivity {

    private ImageView backgroundImage, eighthogaImage, hint1Image, fishEyeImage;
    private Button skipButton;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_construct);

        backgroundImage = findViewById(R.id.construct_background);
        eighthogaImage = findViewById(R.id.osminog_ebany);
        hint1Image = findViewById(R.id.hint1);
        fishEyeImage = findViewById(R.id.fish_eye);
        skipButton = findViewById(R.id.skip_button);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 0:
                        hint1Image.setImageResource(R.drawable.hint_2_image);
                        fishEyeImage.setImageResource(R.drawable.something_strange2);
                        clickCount++;
                        break;
                    case 1:
                        hint1Image.setImageResource(R.drawable.hint_3_image);
                        fishEyeImage.setImageResource(R.drawable.something_strange3);
                        clickCount++;
                        break;
                    case 2:
                        // start another activity
                        //Intent intent = new Intent(ConstructSplashActivity.this, ConstructActivity.class);
                        //startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake_osminog);
        eighthogaImage.startAnimation(animShake);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                eighthogaImage.setVisibility(View.GONE);
                backgroundImage.setImageResource(R.drawable.construct_background2);
                hint1Image.setVisibility(View.VISIBLE);
                fishEyeImage.setVisibility(View.VISIBLE);
                skipButton.setVisibility(View.VISIBLE);
            }
        }, 4500);
    }
}
