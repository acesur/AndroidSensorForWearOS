package com.android_app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import createChannel.CreateChannel;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;

    private SensorManager sensorManager;
    private TextView tvSensors;

    private Button btnDisplayNotification;
    private Button btnDisplayNotification2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        btnDisplayNotification = findViewById(R.id.btnDisplayNotification);
        btnDisplayNotification2 = findViewById(R.id.btnDisplayNotification2);

        btnDisplayNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayNotification();
            }
        });

        btnDisplayNotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayNotification2();
            }
        });

        setTitle("Sensor List");
        tvSensors = findViewById(R.id.tvSensors);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensorList.size(); i++){
            String sensors = "";
            sensors += sensorList.get(i).getName() + "\n";
            tvSensors.append(sensors);
        }
    }
    private void DisplayNotification(){
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1).setSmallIcon(R.drawable.ic_mms_black_24dp).setContentTitle("FirstMessage").setContentText("First Message Body").setCategory(NotificationCompat.CATEGORY_MESSAGE).build();

        notificationManagerCompat.notify(1, notification);
    }
    private void DisplayNotification2(){
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_2).setSmallIcon(R.drawable.ic_voice_chat_black_24dp).setContentTitle("Second Message").setContentText("Second Message Body").setCategory(NotificationCompat.CATEGORY_MESSAGE).build();

        notificationManagerCompat.notify(2,notification);
    }
}
