package com.example.chanrdrapratap.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Boolean counterIsActive=false;
    Button onClick;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        textView.setText("00:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        onClick.setText("Go");
        seekBar.setEnabled(true);
        counterIsActive=false;

    }

    public void updataTimer(int secondsLeft){
        int minutes=(int)secondsLeft/60;
        int seconds=secondsLeft-minutes*60;

        String secondString= Integer.toString(seconds);
        if(seconds<=9)
            secondString="0"+secondString;
        String minuteString= Integer.toString(minutes);
        if(minutes<=9)
            minuteString="0"+minuteString;

        textView.setText(minuteString+":"+secondString);
    }

    public void onClick(View view){

        if(counterIsActive==false) {

            counterIsActive = true;
            seekBar.setEnabled(false);
            onClick.setText("Stop");

            countDownTimer =new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {

                    updataTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhornn);
                    mplayer.start();


                }
            }.start();

        }else{
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        onClick=(Button)findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updataTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
