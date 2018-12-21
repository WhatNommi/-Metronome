package com.example.nommi.metronome;

import android.graphics.PointF;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    float x3 = 0;
    float y3 = 0;
    float Speed_Rete;
    float TempoValue;
    float Speed_Rete_long;
    int init_Value = 0;
    //tempo name
    private TextView textView;
    //tempo value
    private TextView textView1;
    //tempo speed
    private TextView textView2;
    //TempoValue
    private TextView textView3;
    //tempo speed long
    private TextView textView4;
    private Timer mTimer;

    PointF prePoint = new PointF(0, 0);
    PointF nowPoint = new PointF(0, 0);
    HashMap<Integer, String> myMap = new HashMap<Integer, String>();
    HashMap<Integer, Integer> myMap2 = new HashMap<Integer, Integer>();
    private MediaPlayer mp3;
    PlaybackParams pp;
    private Handler mThreadHandler;
    ToggleButton b1;
    //java.util.Timer timer = new java.util.Timer(true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HandlerThread mThread = new HandlerThread("name");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler = new Handler(mThread.getLooper());
        b1 = findViewById(R.id.btn);
        mp3 = MediaPlayer.create(this, R.raw.music);
      //  timer.schedule(timerTask,0,250);
        mTimer = new Timer();
        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    /*
                    textView1 = findViewById(R.id.SpeedValue);
                    String TempTotal = textView1.getText().toString();
                    int IntTempTotal = Integer.parseInt(TempTotal);
                    Speed_Rete_long = (float)60/IntTempTotal*1000 ;
                    long speed_long = (long) Speed_Rete_long ;
                    timer.schedule(timerTask,0,speed_long);
                    */
                    setTimerTask();
                 //   mThreadHandler.post(r1);
                } else {
                    mTimer.cancel();
                    textView3.setText(String.valueOf(1));
                    init_Value=0;
                  //  if (mThreadHandler != null) {
                  //      mThreadHandler.removeCallbacks(r1);
                  //  }
                }
            }
        });
        init();
    }

    private void init() {
        myMap.put(20, "Laraghissimo");
        myMap.put(40, "Largamente");
        myMap.put(60, "Adagio");
        myMap.put(66, "Adagio");
        myMap.put(76, "Adagio");
        myMap.put(80, "Adagietto");
        myMap.put(108, "Andante");
        myMap.put(120, "Moderato");
        myMap.put(168, "Allegro");
        myMap.put(200, "Presto");
        myMap.put(300, "Prestissimo");
        myMap2.put(0, 20);
        myMap2.put(1, 40);
        myMap2.put(2, 60);
        myMap2.put(3, 66);
        myMap2.put(4, 76);
        myMap2.put(5, 80);
        myMap2.put(6, 108);
        myMap2.put(7, 120);
        myMap2.put(8, 168);
        myMap2.put(9, 200);
        myMap2.put(10, 300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Get Background id number
        textView = findViewById(R.id.background);

        //Get Detection for Action
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Touch Screen
            x1 = event.getX();
            y1 = event.getY();
            //position show value
            //Toast.makeText(MainActivity.this, "x軸"+x1, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "y軸"+y1, Toast.LENGTH_SHORT).show();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // Touch Screen
            x3 = event.getX();
            y3 = event.getY();
            //position show value
            //Toast.makeText(MainActivity.this, "x軸"+x1, Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "y軸"+y1, Toast.LENGTH_SHORT).show();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //Leave Screen
            x2 = event.getX();
            y2 = event.getY();
            float x = Math.abs(x2 - x1);
            float y = Math.abs(y2 - y1);
            double z = Math.sqrt(x * x + y * y);
            // angle computer
            int angle = Math.round((float) (Math.asin(y / z) / Math.PI * 180));
            //Computer position and angle  take
            if (y2 < y1 && angle > 45) {
                textView1 = findViewById(R.id.SpeedValue);
                String UpString = textView1.getText().toString();
                int UpInt = Integer.parseInt(UpString);
                if (UpInt <= 250) {
                    UpInt = UpInt + 10;
                    textView1.setText(UpInt + "");
                    textView.setText(jujgeValue(UpInt));
                }
            } else if (y2 > y1 && angle > 45) {
                textView1 = findViewById(R.id.SpeedValue);
                String DownString = textView1.getText().toString();
                int DownInt = Integer.parseInt(DownString);
                //Value Check
                if (DownInt >= 70) {
                    DownInt = DownInt - 10;
                    textView1.setText(DownInt + "");
                    textView.setText(jujgeValue(DownInt));
                }
            } else if (x2 < x1 && angle <= 45) {
                textView1 = findViewById(R.id.SpeedValue);
                String LeftString = textView1.getText().toString();
                int LeftInt = Integer.parseInt(LeftString);
                if (LeftInt < 260) {
                    LeftInt++;
                    textView1.setText(LeftInt + "");
                    textView.setText(jujgeValue(LeftInt));
                }
            } else if (x2 > x1 && angle <= 45) {
                textView1 = findViewById(R.id.SpeedValue);
                String RightString = textView1.getText().toString();
                int RightInt = Integer.parseInt(RightString);
                if (RightInt > 60) {
                    RightInt--;
                    textView1.setText(RightInt + "");
                    textView.setText(jujgeValue(RightInt));
                }
            }
        }
        //get value of user setting
        textView1 = findViewById(R.id.SpeedValue);
        //String TempTotal_change = textView1.getText().toString();
        String TempTotal = textView1.getText().toString();
        //int IntTempTotal_change = Integer.parseInt(TempTotal_change);
        int IntTempTotal = Integer.parseInt(TempTotal);
        //Toast.makeText(MainActivity.this, "start"+IntTempTotal, Toast.LENGTH_SHORT).show();
        // action start
        //TempoValue/60s
        Speed_Rete = (float) 60/IntTempTotal ;
        Speed_Rete_long = (float)60/IntTempTotal*1000 ;
        long speed_long = (long) Speed_Rete_long ;
        //save float two point
        DecimalFormat df = new DecimalFormat("#.##");
        String Speed_Rete_Value = df.format(Speed_Rete);
        textView2 = findViewById(R.id.rate);
        textView2.setText(Speed_Rete_Value);
        textView4 = findViewById(R.id.rate_long);
        textView4.setText(String.valueOf(speed_long));
        //Toast.makeText(MainActivity.this, "start" + Speed_Rete_Value, Toast.LENGTH_SHORT).show();

        return super.onTouchEvent(event);
    }

    public String jujgeValue(int input) {
        String result = "";
        for (int i = 0; i < myMap.size(); i++) {
            if (input <= myMap2.get(i)) {
                result = myMap.get(myMap2.get(i));
                break;
            }
        }
        return result;
    }
    float rate = 0.10f;
    private Runnable r1 = new Runnable() {
        public void run() {
        //    while (b1.isChecked()) {
                // TODO Auto-generated method stub
                //.............................
            //    pp.setSpeed(rate);
            //    mp3.setPlaybackParams(pp);

            long time=0;

            //    while (mp3.isPlaying()) {
                    //donothing
            //    }
                //做了很多事
        //    }
        }
    };



    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    //mp3.start();
                    textView3 = findViewById(R.id.TempoValue);
                    String TempoValue = textView3.getText().toString();
                    int TempoValue_t = Integer.parseInt(TempoValue);
                    if (init_Value==0)
                    {init_Value++;}
                    else if (init_Value!=0)
                    {TempoValue_t = TempoValue_t + 1;}
                    //textView3.setText(TempoValue_t + "");
                    //Toast.makeText(MainActivity.this, "滑" + TempoValue_t, Toast.LENGTH_SHORT).show();
                    if (TempoValue_t%4==1) {textView3.setText(String.valueOf(1));
                       }
                    else if(TempoValue_t%4==2) {textView3.setText(String.valueOf(2));
                        }
                    else if(TempoValue_t%4==3) {textView3.setText(String.valueOf(3));
                        }
                    else if(TempoValue_t%4==0) {textView3.setText(String.valueOf(4));
                        }

                }
            });
        }
    };

    public void setTimerTask() {


                textView1 = findViewById(R.id.SpeedValue);
                String TempTotal = textView1.getText().toString();
                int IntTempTotal = Integer.parseInt(TempTotal);
                Speed_Rete_long = (float)60/IntTempTotal*1000 ;
                long speed_long = (long) Speed_Rete_long ;

                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                    //mp3.start();
                        runOnUiThread(setImageRunnable);
                    }
                }, 0, speed_long/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);


    }

     Runnable setImageRunnable = new Runnable() {
        public void run() {
            textView3 = findViewById(R.id.TempoValue);
            String TempoValue = textView3.getText().toString();
            int TempoValue_t = Integer.parseInt(TempoValue);
            if (init_Value==0)
            {init_Value++;}
            else if (init_Value!=0)
            {TempoValue_t = TempoValue_t + 1;}
            //textView3.setText(TempoValue_t + "");
            //Toast.makeText(MainActivity.this, "滑" + TempoValue_t, Toast.LENGTH_SHORT).show();
            if (TempoValue_t%4==1) {textView3.setText(String.valueOf(1));
            }
            else if(TempoValue_t%4==2) {textView3.setText(String.valueOf(2));
            }
            else if(TempoValue_t%4==3) {textView3.setText(String.valueOf(3));
            }
            else if(TempoValue_t%4==0) {textView3.setText(String.valueOf(4));
            }
        }
    };
}

