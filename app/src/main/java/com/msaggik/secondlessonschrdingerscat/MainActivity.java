package com.msaggik.secondlessonschrdingerscat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView coordinatesOut;
    private String sDown;
    private String sMove;
    private String sUp;
    private ImageView imageView;
    float xCat;
    float yCat;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatesOut = findViewById(R.id.coordinatesOut);
        coordinatesOut.setOnTouchListener(listener);
        imageView = findViewById(R.id.cat);
        imageView.setImageResource(R.drawable.cat);
        imageView.setVisibility(View.INVISIBLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        xCat = (float) (Math.random() * (displayMetrics.widthPixels - 121));
        yCat = (float) (Math.random() * (displayMetrics.heightPixels - 128));
        imageView.setX(xCat);
        imageView.setY(yCat);
    }


    private final View.OnTouchListener listener = new View.OnTouchListener() {
        @SuppressLint({"SetTextI18n", "RtlHardcoded", "ClickableViewAccessibility"})
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            float deltaCat = 80;
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sDown = "Click: X = " + x + ", y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Move: X = " + x + ", y = " + y;
                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat) && y > (yCat - deltaCat))
                        imageView.setVisibility(View.VISIBLE);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Up: X = " + x + ", y = " + y;
                    break;
            }
            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);
            return true;
        }
    };
}