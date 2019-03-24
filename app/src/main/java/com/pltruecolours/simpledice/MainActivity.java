package com.pltruecolours.simpledice;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

private SensorManager mSensorManager;
private Sensor sensor;
public float [] mGravity;
public TextView txtVw;
public String daneAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVw=(TextView)findViewById(R.id.text1);


        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

        final float alpha = 0.8f;
        mGravity=event.values.clone();

        mGravity[0] = alpha * mGravity[0] + (1-alpha) *event.values[0];
        mGravity[1] = alpha * mGravity[1] + (1-alpha) *event.values[1];
        mGravity[2] = alpha * mGravity[2] + (1-alpha) *event.values[2];

        txtVw.setText(String.valueOf(mGravity[0]) +"\n"+String.valueOf(mGravity[1]) +"\n"+ String.valueOf(mGravity[2]));



    }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onPause(){
     super.onPause();
     mSensorManager.unregisterListener(this);
}
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

}
