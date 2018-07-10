package com.example.nommi.metronome;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    float x1 = 0; float x2 = 0; float y1 = 0; float y2 = 0;

    PointF prePoint=new PointF(0,0);
    PointF nowPoint=new PointF(0,0);
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
                event.getActionMasked();
                prePoint.x=event.getX();
                prePoint.y=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                nowPoint.x = event.getX();
                nowPoint.y = event.getY();
                Log.d("ACTION_MOVE",String.format("X=%f Y=%f",event.getX(),event.getY()));
                //計算寫於此

                //將座標記錄於此
                prePoint.x=nowPoint.x;
                prePoint.y=nowPoint.y;
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                //Computer and take
                break;
        }


          return super.onTouchEvent(event);
    }

}