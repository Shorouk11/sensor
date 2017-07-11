package com.example.shoroukramzi.sensor;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import java.util.ArrayList;
import java.util.Random;

//import static android.R.attr.x;
//import static android.R.attr.y;

import android.app.Activity;

import static android.R.attr.max;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.view.View.Y;
//import com.example.R;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager senSensorManager; //object fi 5sa2s el sensor
    private Sensor senAccelerometer; //object fi el accelerometer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //3mlna access lel sensor resource w 7atena el service dy fi variable esmo senSensorManager
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //7atet el sensor el default fi el object bta3y
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL); //3malt regiter lel accelerometer bta3y fi el maneger w ch8lto b normal delay
    }

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private float x, y, z;
    private long curTime, diffTime;
    private float speed;
    private double threshold= -0.25;
    private double[] yPoints=new double[5];
    private static int i=0;
    private static int flag=0;
    private static int flag2=0;
    private static double ymin=-0.25;
    private  double ymax=0;
    private static int strokeCount=0;
    private static int b=0;


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mySensor = sensorEvent.sensor;

            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];

               //double totalAcceleration=Math.sqrt(x*x+y*y+z*z);
                //stroke for y
               /* if(y<=threshold)
                {
                    yPoints[i]=y;
                    i++;
                    if (i==4) {
                        int j = 0; int k=0;
                        ymin = yPoints[j];
                        for (j = 1; j <= 5; j++) {
                            if (ymin >= yPoints[j])
                                ymin = yPoints[j];
                            else
                                k++;

                        }

                        i=0;
                    }
                    if (ymin<y_m)
                        y_m=ymin; // y_m is the min value in the stroke




                }*/
                //start(catch state)
                if (y<ymin&&flag==0)
                {
                    ymin = y;

                }

                //drive state-->find max
                if(y>(-1*threshold)&&flag2==0) {/////////////
                   // yPoints[i]=ymin;////////////////////////////////////////////////
                    i++;
                    ymin = threshold;
                    if (y > ymax) {
                        ymax = y;
                        flag = 1;
                        b++;///////////////
                    }
                }
                //end (return to  catch state)
                if(y<=threshold&&flag==1&&b>1)////////////////
                {   // yPoints[i]=ymax;
                     ymax=0;
                    if (y < ymin)
                        ymin = y;
                    flag2=1;
                    b=0;
                }
                if(flag2==1&&y>0) {
                    strokeCount++;
                    flag2=0;/////////////////////////////
                    i=0;
                }




                /*{yPoints[i]=y;
                    i++;
                    if(i==4)
                    {   max = yPoints[0];
                        for(int k=0;k<=i;k++)
                            if (yPoints[k] =>max)
                            max = yPoints[k];
                        maximum=max;*/















                            curTime = System.currentTimeMillis();
                    }

                if ((curTime - lastUpdate) > 1000) {
                    diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 1000;

                   //if (speed > SHAKE_THRESHOLD) {
                        //getRandomNumber();


                        TextView text = (TextView)findViewById(R.id.number_1);
                        text.setText(String.valueOf(speed));
                        text = (TextView)findViewById(R.id.number_4);
                        text.setText(String.valueOf(x));
                        text = (TextView)findViewById(R.id.number_5);
                        text.setText(String.valueOf(y));
                        text = (TextView)findViewById(R.id.number_6);
                        text.setText(String.valueOf(z));
                        text = (TextView)findViewById(R.id.number_2);
                        text.setText("Hello");



                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
        }
        }


    //@Override
    /*private void getRandomNumber() {
        ArrayList numbersGenerated = new ArrayList();

        for (int i = 0; i < 6; i++) {
            Random randNumber = new Random();
            int iNumber = randNumber.nextInt(48) + 1;

            if(!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
*/
        /*TextView text = (TextView)findViewById(R.id.number_1);
        text.setText(""+numbersGenerated.get(0));

        text = (TextView)findViewById(R.id.number_2);
        text.setText(""+numbersGenerated.get(1));

        text = (TextView)findViewById(R.id.number_3);
        text.setText(""+numbersGenerated.get(2));

        text = (TextView)findViewById(R.id.number_4);
        text.setText(""+numbersGenerated.get(3));

        text = (TextView)findViewById(R.id.number_5);
        text.setText(""+numbersGenerated.get(4));

        text = (TextView)findViewById(R.id.number_6);
        text.setText(""+numbersGenerated.get(5));

        FrameLayout ball1 = (FrameLayout) findViewById(R.id.ball_1);
        ball1.setVisibility(View.INVISIBLE);

        FrameLayout ball2 = (FrameLayout) findViewById(R.id.ball_2);
        ball2.setVisibility(View.INVISIBLE);

        FrameLayout ball3 = (FrameLayout) findViewById(R.id.ball_3);
        ball3.setVisibility(View.INVISIBLE);

        FrameLayout ball4 = (FrameLayout) findViewById(R.id.ball_4);
        ball4.setVisibility(View.INVISIBLE);

        FrameLayout ball5 = (FrameLayout) findViewById(R.id.ball_5);
        ball5.setVisibility(View.INVISIBLE);

        FrameLayout ball6 = (FrameLayout) findViewById(R.id.ball_6);
        ball6.setVisibility(View.INVISIBLE);*/

    /* Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);
     ball6.setVisibility(View.VISIBLE);
     ball6.clearAnimation();
     ball6.startAnimation(a);

     ball5.setVisibility(View.VISIBLE);
     ball5.clearAnimation();
     ball5.startAnimation(a);

     ball4.setVisibility(View.VISIBLE);
     ball4.clearAnimation();
     ball4.startAnimation(a);

     ball3.setVisibility(View.VISIBLE);
     ball3.clearAnimation();
     ball3.startAnimation(a);

     ball2.setVisibility(View.VISIBLE);
     ball2.clearAnimation();
     ball2.startAnimation(a);

     ball1.setVisibility(View.VISIBLE);
     ball1.clearAnimation();
     ball1.startAnimation(a);*/
    // }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}