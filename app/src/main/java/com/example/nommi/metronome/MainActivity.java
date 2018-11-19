package com.example.nommi.metronome;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    float x1 = 0; float x2 = 0; float y1 = 0; float y2 = 0; float x3 = 0; float y3 = 0;
    float Speed_Rete;
    //tempo name
    private TextView textView;
    //tempo value
    private TextView textView1;
    //tempo speed
    private TextView textView2;

    PointF prePoint=new PointF(0,0);
    PointF nowPoint=new PointF(0,0);

    @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                //Start to work  for Speed choice
                ToggleButton b1 = (ToggleButton) findViewById(R.id.btn);
        }
                @Override
                public boolean onTouchEvent(MotionEvent event) {
                    //Get Background id number
                    textView = findViewById(R.id.background);

                    //Get Detection for Action
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        // Touch Screen
                        x1 = event.getX();
                        y1 = event.getY();
                        //position show value
                        //Toast.makeText(MainActivity.this, "x軸"+x1, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this, "y軸"+y1, Toast.LENGTH_SHORT).show();
                    }
                    if(event.getAction() == MotionEvent.ACTION_MOVE) {
                        // Touch Screen
                        x3 = event.getX();
                        y3 = event.getY();
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
                                // angle computer
                                int angle=Math.round((float)(Math.asin(y/z)/Math.PI*180));
                                  //Computer position and angle  take
                                  if(y2 < y1  && angle>45) {
                                      textView1 = findViewById(R.id.SpeedValue);
                                      String UpString = textView1.getText().toString();
                                      int UpInt = Integer.parseInt(UpString);
                                          if(UpInt <= 250 ) {
                                              UpInt=UpInt+10;
                                              textView1.setText(UpInt + "");
                                              Toast.makeText(MainActivity.this, "向上滑" + UpInt, Toast.LENGTH_SHORT).show();

                                              if (UpInt <= 20) {
                                                  textView.setText("Laraghissimo");
                                              } else if (UpInt <= 40) {
                                                  textView.setText("Largamente");
                                              } else if (UpInt <= 60) {
                                                  textView.setText("Adagio");
                                              } else if (UpInt <= 66) {
                                                  textView.setText("Adagio");
                                              } else if (UpInt <= 76) {
                                                  textView.setText("Adagio");
                                              } else if (UpInt <= 80) {
                                                  textView.setText("Adagietto");
                                              } else if (UpInt <= 108) {
                                                  textView.setText("Andante");
                                              } else if (UpInt <= 120) {
                                                  textView.setText("Moderato");
                                              } else if (UpInt <= 168) {
                                                  textView.setText("Allegro");
                                              } else if (UpInt <= 200) {
                                                  textView.setText("Presto");
                                              } else if (UpInt <= 300) {
                                                  textView.setText("Prestissimo");
                                              }
                                          }
                                                  ToggleButton b1 = (ToggleButton) findViewById(R.id.btn);
                                                  b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                                     public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked ) {
                                                          if (isChecked) {
                                                             //get value of user setting
                                                              textView1 = findViewById(R.id.SpeedValue);
                                                              //String TempTotal_change = textView1.getText().toString();
                                                              String TempTotal = textView1.getText().toString();
                                                              //int IntTempTotal_change = Integer.parseInt(TempTotal_change);
                                                              int IntTempTotal = Integer.parseInt(TempTotal);
                                                              //Toast.makeText(MainActivity.this, "start"+IntTempTotal, Toast.LENGTH_SHORT).show();
                                                              // action start
                                                              //TempoValue/60s
                                                              Speed_Rete = (float) IntTempTotal/60;
                                                              //save float two point
                                                              DecimalFormat df=new DecimalFormat("#.##");
                                                              String Speed_Rete_Value=df.format(Speed_Rete);
                                                              textView2 = findViewById(R.id.rate);
                                                              textView2.setText(Speed_Rete_Value);
                                                              Toast.makeText(MainActivity.this, "start"+Speed_Rete_Value, Toast.LENGTH_SHORT).show();
                                                              //if (Speed_rete){
                                                              //
                                                              //   textView1 = findViewById(R.id.TempoValue);
                                                              //}
                                                              }
                                                              else {
                                                                  // action stop
                                                              }
                                                          }
                                                     });
                                  } else if(y2 > y1 && angle>45 ) {
                                      textView1 = findViewById(R.id.SpeedValue);
                                      String DownString = textView1.getText().toString();
                                      int DownInt = Integer.parseInt(DownString);
                                           //Value Check
                                          if (DownInt >= 70) {
                                              DownInt=DownInt-10;
                                              textView1.setText(DownInt + "");
                                              Toast.makeText(MainActivity.this, "向下滑" + DownInt, Toast.LENGTH_SHORT).show();
                                                  if (DownInt <= 20) {
                                                      textView.setText("Laraghissimo");
                                                  } else if (DownInt <= 40) {
                                                      textView.setText("Largamente");
                                                  } else if (DownInt <= 60) {
                                                      textView.setText("Adagio");
                                                  } else if (DownInt <= 66) {
                                                      textView.setText("Adagio");
                                                  } else if (DownInt <= 76) {
                                                      textView.setText("Adagio");
                                                  } else if (DownInt <= 80) {
                                                      textView.setText("Adagietto");
                                                  } else if (DownInt <= 108) {
                                                      textView.setText("Andante");
                                                  } else if (DownInt <= 120) {
                                                      textView.setText("Moderato");
                                                  } else if (DownInt <= 168) {
                                                      textView.setText("Allegro");
                                                  } else if (DownInt <= 200) {
                                                      textView.setText("Presto");
                                                  } else if (DownInt <= 300) {
                                                      textView.setText("Prestissimo");
                                                  }
                                          }
                                  } else if(x2 < x1 && angle <= 45) {
                                      textView1 = findViewById(R.id.SpeedValue);
                                      String LeftString = textView1.getText().toString();
                                      int LeftInt = Integer.parseInt(LeftString);
                                              if (LeftInt<260) {
                                                  LeftInt++;
                                                  textView1.setText(LeftInt + "");
                                                  Toast.makeText(MainActivity.this, "向左滑" + LeftInt, Toast.LENGTH_SHORT).show();
                                                      if (LeftInt <= 20) {
                                                          textView.setText("Laraghissimo");
                                                      } else if (LeftInt <= 40) {
                                                          textView.setText("Largamente");
                                                      } else if (LeftInt <= 60) {
                                                          textView.setText("Adagio");
                                                      } else if (LeftInt <= 66) {
                                                          textView.setText("Adagio");
                                                      } else if (LeftInt <= 76) {
                                                          textView.setText("Adagio");
                                                      } else if (LeftInt <= 80) {
                                                          textView.setText("Adagietto");
                                                      } else if (LeftInt <= 108) {
                                                          textView.setText("Andante");
                                                      } else if (LeftInt <= 120) {
                                                          textView.setText("Moderato");
                                                      } else if (LeftInt <= 168) {
                                                          textView.setText("Allegro");
                                                      } else if (LeftInt <= 200) {
                                                          textView.setText("Presto");
                                                      } else if (LeftInt <= 300) {
                                                          textView.setText("Prestissimo");
                                                      }
                                              }
                                  } else if(x2 > x1 && angle <= 45) {
                                      textView1 = findViewById(R.id.SpeedValue);
                                      String RightString = textView1.getText().toString();
                                      int RightInt = Integer.parseInt(RightString);
                                              if (RightInt>60) {
                                                  RightInt--;
                                                  textView1.setText(RightInt + "");
                                                  Toast.makeText(MainActivity.this, "向右滑" + RightInt, Toast.LENGTH_SHORT).show();
                                                  if (RightInt <= 20) {
                                                      textView.setText("Laraghissimo");
                                                  } else if (RightInt <= 40) {
                                                      textView.setText("Largamente");
                                                  } else if (RightInt <= 60) {
                                                      textView.setText("Adagio");
                                                  } else if (RightInt <= 66) {
                                                      textView.setText("Adagio");
                                                  } else if (RightInt <= 76) {
                                                      textView.setText("Adagio");
                                                  } else if (RightInt <= 80) {
                                                      textView.setText("Adagietto");
                                                  } else if (RightInt <= 108) {
                                                      textView.setText("Andante");
                                                  } else if (RightInt <= 120) {
                                                      textView.setText("Moderato");
                                                  } else if (RightInt <= 168) {
                                                      textView.setText("Allegro");
                                                  } else if (RightInt <= 200) {
                                                      textView.setText("Presto");
                                                  } else if (RightInt <= 300) {
                                                      textView.setText("Prestissimo");
                                                  }
                                              }
                                }
                    }
                    return super.onTouchEvent(event);
                }
    }