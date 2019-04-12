package com.example.mrunal.multil.womenapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class ViewVideo extends AppCompatActivity {

    private VideoView videoView,videoView1,videoView2,videoView3,videoView4;
    private int position = 0;
    private int position1 = 0;
    private int position2 =0;
    private int position3 =0;
    private int position4 =0;
    ImageButton mBackButton;

    private MediaController mediaController,mediaController1,mediaController2,mediaController3,mediaController4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        mBackButton = findViewById(R.id.back_button);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewVideo.this,MainActivity.class));
                finish();
            }
        });
        videoView = (VideoView) findViewById(R.id.view1);
        videoView1 = (VideoView) findViewById(R.id.view2);
        videoView2 = (VideoView) findViewById(R.id.view3);
        videoView3 = (VideoView) findViewById(R.id.view4);
        videoView4 = (VideoView) findViewById(R.id.view5);

        // Set the media controller buttons
        if (mediaController == null) {
            mediaController = new MediaController(ViewVideo.this);

            // Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(videoView);
            // mediaController.setAnchorView(videoView1);
            // mediaController.setAnchorView(videoView2);

            // Set MediaController for VideoView
            videoView.setMediaController(mediaController);
            //videoView1.setMediaController(mediaController);
            //videoView2.setMediaController(mediaController);
        }if (mediaController1 == null) {
            mediaController1 = new MediaController(ViewVideo.this);

            // Set the videoView that acts as the anchor for the MediaController.
            //mediaController1.setAnchorView(videoView);
            mediaController1.setAnchorView(videoView1);
            //mediaController.setAnchorView(videoView2);

            // Set MediaController for VideoView
            //videoView.setMediaController(mediaController);
            videoView1.setMediaController(mediaController1);
            // videoView2.setMediaController(mediaController);
        }
        if (mediaController2 == null) {
            mediaController2 = new MediaController(ViewVideo.this);

            // Set the videoView that acts as the anchor for the MediaController.
            // mediaController.setAnchorView(videoView);
            //mediaController.setAnchorView(videoView1);
            mediaController2.setAnchorView(videoView2);

            // Set MediaController for VideoView
            // videoView.setMediaController(mediaController);
            //videoView1.setMediaController(mediaController);
            videoView2.setMediaController(mediaController2);
        }
        if (mediaController3 == null) {
            mediaController3 = new MediaController(ViewVideo.this);

            // Set the videoView that acts as the anchor for the MediaController.
            // mediaController.setAnchorView(videoView);
            //mediaController.setAnchorView(videoView1);
            mediaController3.setAnchorView(videoView3);

            // Set MediaController for VideoView
            // videoView.setMediaController(mediaController);
            //videoView1.setMediaController(mediaController);
            videoView3.setMediaController(mediaController3);
        }
        if (mediaController4 == null) {
            mediaController4 = new MediaController(ViewVideo.this);

            // Set the videoView that acts as the anchor for the MediaController.
            // mediaController.setAnchorView(videoView);
            //mediaController.setAnchorView(videoView1);
            mediaController4.setAnchorView(videoView4);

            // Set MediaController for VideoView
            // videoView.setMediaController(mediaController);
            //videoView1.setMediaController(mediaController);
            videoView4.setMediaController(mediaController4);
        }


        try {
            // ID of video file.
            // int id = this.getRawResIdByName("myvideo");
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.babycare1));
            videoView1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.babycare2));
            videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pregnancy1));
            videoView3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pregnancy2));
            videoView4.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pregnancy1));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView1.requestFocus();
        videoView2.requestFocus();
        videoView3.requestFocus();
        videoView4.requestFocus();


        // When the video file ready for playback.
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });
        videoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                videoView1.seekTo(position1);
                if (position1 == 0) {
                    videoView1.pause();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController1.setAnchorView(videoView1);
                    }
                });
            }
        });
        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                videoView2.seekTo(position);
                if (position2 == 0) {
                    videoView2.pause();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController2.setAnchorView(videoView2);
                    }
                });
            }
        });
        videoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                videoView3.seekTo(position);
                if (position3 == 0) {
                    videoView3.pause();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController3.setAnchorView(videoView3);
                    }
                });
            }
        });
        videoView4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {


                videoView4.seekTo(position);
                if (position4 == 0) {
                    videoView4.pause();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mediaController4.setAnchorView(videoView4);
                    }
                });
            }
        });

    }

    // Find ID corresponding to the name of the resource (in the directory raw).
    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }


    // When you change direction of phone, this method will be called.
    // It store the state of video (Current position)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
        savedInstanceState.putInt("CurrentPosition", videoView1.getCurrentPosition());
        savedInstanceState.putInt("CurrentPosition", videoView2.getCurrentPosition());
        savedInstanceState.putInt("CurrentPosition", videoView3.getCurrentPosition());
        savedInstanceState.putInt("CurrentPosition", videoView4.getCurrentPosition());
        videoView.pause();
        videoView1.pause();
        videoView2.pause();
        videoView3.pause();
        videoView4.pause();
    }


    // After rotating the phone. This method is called.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        position1 = savedInstanceState.getInt("CurrentPosition");
        position2= savedInstanceState.getInt("CurrentPosition");
        position3= savedInstanceState.getInt("CurrentPosition");
        position4= savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
        videoView1.seekTo(position1);
        videoView2.seekTo(position2);
        videoView3.seekTo(position3);
        videoView4.seekTo(position4);
    }

}
