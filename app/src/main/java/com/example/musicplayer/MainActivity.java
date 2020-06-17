package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private float volumeValue = (float) 0.500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.nhac);
        Button btnPlay = (Button) this.findViewById(R.id.playBtn);
        Button btnPause = (Button) this.findViewById(R.id.pauseBtn);
        Button btnVolUp = (Button) this.findViewById(R.id.volUpBtn);
        Button btnVolDown = (Button) this.findViewById(R.id.volDownBtn);
        final TextView textviewVolume = (TextView) this.findViewById(R.id.volumeTextview);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_SHORT).show();
                        releaseMediaPlayer();

                    }
                });

            }

            public void releaseMediaPlayer() {
                // If the media player is not null, then it may be currently playing a sound.
                if (mediaPlayer != null) {
                    // Regardless of the current state of the media player, release its resources
                    // because we no longer need it.
                    mediaPlayer.release();

                    // Set the media player back to null. For our code, we've decided that
                    // setting the media player to null is an easy way to tell that the media player
                    // is not configured to play an audio file at the moment.
                    mediaPlayer = null;
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        btnVolUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeValue += 0.01;
                if (volumeValue > 1) {
                    volumeValue = 1;
                }
                mediaPlayer.setVolume(volumeValue, volumeValue);
                textviewVolume.setText("Volume " + volumeValue * 100 + " %");
            }
        });

        btnVolDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeValue -= 0.01;
                if (volumeValue < 0) {
                    volumeValue = 0;
                }
                mediaPlayer.setVolume(volumeValue, volumeValue);
                textviewVolume.setText("Volume " + volumeValue * 100 + " %");
            }
        });




    }

}