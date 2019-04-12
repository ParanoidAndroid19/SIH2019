package com.example.mrunal.multil.womenapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;

public class ESeva extends AppCompatActivity implements TextToSpeech.OnInitListener {

    FloatingActionButton mFabVoice;
    TextToSpeech textToSpeech;
    TextView mTextView;
    VideoView viewashavideo;
    MediaController mediaController;
    int position = 0;
    ImageButton mBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eseva);

        mTextView = findViewById(R.id.pmny);
        //mFabVoice = findViewById(R.id.fab_voice);
        textToSpeech = new TextToSpeech(this,this);
        viewashavideo = findViewById(R.id.viewasha);

        mBackButton = findViewById(R.id.back_button);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ESeva.this,MainActivity.class));
                finish();
            }
        });

        if (mediaController == null) {
            mediaController = new MediaController(ESeva.this);
            mediaController.setVisibility(View.GONE);

            // Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(viewashavideo);
            // mediaController.setAnchorView(videoView1);
            // mediaController.setAnchorView(videoView2);

            // Set MediaController for VideoView
            viewashavideo.setMediaController(mediaController);
            //videoView1.setMediaController(mediaController);
            //videoView2.setMediaController(mediaController);
        }

        try {
            // ID of video file.
            // int id = this.getRawResIdByName("myvideo");
            viewashavideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.zzz));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        viewashavideo.requestFocus();

        // When the video file ready for playback.
        viewashavideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                viewashavideo.seekTo(position);
                if (position == 0) {
                    viewashavideo.start();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(viewashavideo);
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        viewashavideo.start();

                    }
                });
            }
        });
        Typeface fontHindi = Typeface.createFromAsset(getAssets(),
                "fonts/DroidHindi.ttf");

        mTextView.setTypeface(fontHindi);

//        mFabVoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                speakOut();
//            }
//        });


    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                result = textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
            }

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
                //mFabVoice.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {

        String text = mTextView.getText().toString();

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
