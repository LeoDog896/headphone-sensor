package com.leodog896.headphonesensor;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Main extends AppCompatActivity {
    protected TextView textview;
    protected TextView trouble;
    protected AudioManager audioManager;
    protected int again = 0;
    protected String[] troubleshoots = new String[] {
            "Try restarting your phone.",
            "Use a dongle or an adapter.",
            "Use your warranty, if available.",
            "Search up fixes on your search engine.",
            "Use Google Support."
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textview = (TextView) findViewById(R.id.textView1);
        trouble =  (TextView) findViewById(R.id.textViewTrouble) ;
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(audioManager.isWiredHeadsetOn()) textview.setText("Headset is plugged.");
        else if (!audioManager.isWiredHeadsetOn()) textview.setText("Headset is unplugged.");
        else textview.setText("Headset has an unknown state, update it.");
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(
                v -> {
                    if (audioManager.isWiredHeadsetOn()) {
                        textview.setText("Headset is plugged."); again = 0; trouble.setText("");
                    } else if (!audioManager.isWiredHeadsetOn()) {
                        trouble.setText(getRandom(troubleshoots));
                        if (again >= 100) textview.setText("Try physically repairing your phone.");
                        else if (again >= 10) textview.setText("Headset is unplugged, try troubleshooting more.");
                        else if (again >= 1) textview.setText("Headset is unplugged, try troubleshooting again.");
                        else textview.setText("Headset is unplugged, try troubleshooting.");
                        again++;
                    } else {
                        textview.setText("Headset has an unknown state, update it.");
                        trouble.setText("");
                    }
                }
        );
    }
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
