package com.example.nommi.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float x1 = 0; float x2 = 0; float y1 = 0; float y2 = 0;
    //int tempo
    private TextView textView;
    private TextView textView1;
    int number =60;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
                @Override
                public boolean onTouchEvent(MotionEvent event) {
                    //Get Background id number
                    textView = findViewById(R.id.background);
                    textView1 = findViewById(R.id.SpeedValue);

                    //textView1.setText(number);
                    //Get Detection for Action
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                                // Touch Screen
                                x1 = event.getX();
                                y1 = event.getY();
                                //position show value
                                //Toast.makeText(MainActivity.this, "x軸"+x1, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(MainActivity.this, "y軸"+y1, Toast.LENGTH_SHORT).show();
                    }
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                                //Leave Screen
                                x2 = event.getX();
                                y2 = event.getY();
                                float x=Math.abs(x2-x1);
                                float y=Math.abs(y2-y1);
                                double z=Math.sqrt(x*x+y*y);
                                //角度complete
                                int angle=Math.round((float)(Math.asin(y/z)/Math.PI*180));

                                //Computer position and angle  take
                                  if(y2 < y1  && angle>45) {
                                    Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                                    textView.setText("UP");
                                  } else if(y2 > y1 && angle>45 ) {
                                    Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                                    textView.setText("Down");
                                  } else if(x2 < x1 && angle <= 45) {
                                    Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                                    textView.setText("Left");
                                  } else if(x2 > x1 && angle <= 45) {
                                    Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                                    textView.setText("Right");
                                }
                    }
                    return super.onTouchEvent(event);
                }

    }