package com.example.nommi.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float x1 = 0; float x2 = 0; float y1 = 0; float y2 = 0;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Background id number
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.background);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
          //Get Detection for Action
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                //Computer and take
                if(y1 - y2 > 50) {
                    Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                    textView.setText("UP");
                } else if(y2 - y1 > 50) {
                    Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                    textView.setText("Down");
                } else if(x1 - x2 > 50) {
                    Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                    textView.setText("Left");
                } else if(x2 - x1 > 50) {
                    Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                    textView.setText("Right");
                }
                break;
            case MotionEvent.ACTION_HOVER_ENTER:
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                break;
        }


          return super.onTouchEvent(event);
    }

}