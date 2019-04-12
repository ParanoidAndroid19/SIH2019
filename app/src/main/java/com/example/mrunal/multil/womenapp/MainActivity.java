package com.example.mrunal.multil.womenapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wenchao.cardstack.CardStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextToSpeech textToSpeech;
    //FirebaseDatabase firebaseDatabase;
    //DatabaseReference databaseReference;


    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int REQ_CODE_SPEECH_INPUT_TWO = 200;
    private final int REQ_CODE_SPEECH_INPUT_THREE = 300;
    private final int REQ_CODE_SPEECH_INPUT_FOUR = 400;
    private final int REQ_CODE_SPEECH_INPUT_FIVE = 500;
    private final int REQ_CODE_SPEECH_INPUT_MAP = 600;

    Locale myLocale;
    String currentLanguage = Locale.getDefault().getDisplayLanguage(), currentLang;

    FloatingActionButton floatingActionButton;


    //CardStack mCardStack;
    //CardStackAdapter mCardAdapter;
    Button mSos, mEseva;

    Button mWeek1Timeline, mWeek2Timeline, mWeek3Timeline, mWeek4Timeline;
    // ArrayList<WomenCardViewData> womenCardViewDataList;

    EditText txtSpeechInput, txtSpeechInput2, txtSpeechInput3, txtSpeechInput4, txtSpeechInput5, txtSpeechMap;
    ImageView mImg1, mImg2, mImg3, mImg4;
    Button b1, b2, b3, b4;
    RadioButton radio1, radio2, radio3;
    FloatingActionButton mMapFloat;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase mFireData;
    DatabaseReference mDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmfunc();

//        mDataRef = FirebaseDatabase.getInstance().getReference().child("Notifications");
//        mDataRef.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot fire : dataSnapshot.getChildren())
//                {
//                    Product temp= fire.getValue(Product.class);
//                    mDataSet.add(temp);
//                    //Toast.makeText(MyProduct.this,mDataSet.size()+ "", Toast.LENGTH_SHORT).show();
//                }
//                mAdapter.refresh(mDataSet);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        currentLanguage = getIntent().getStringExtra(currentLang);


        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences sharedPreferences = getSharedPreferences("My_pref",Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString("User_id",user.getUid());

        edit.commit();

        //firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = firebaseDatabase.getReference("Suggestions");

        //setLocale(getResources().getConfiguration().getLocales().get(0).toString());

        setLocale(Locale.getDefault().getDisplayLanguage());

        floatingActionButton = findViewById(R.id.floating_button);

        //mCardStack = findViewById(R.id.container);
        mSos = findViewById(R.id.sos);
        mEseva = findViewById(R.id.eseva);
        mMapFloat = findViewById(R.id.mic_button);


        mMapFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInputMap();
            }
        });
        //womenCardViewDataList = new ArrayList<WomenCardViewData>();
        // mCardAdapter = new CardStackAdapter(this,womenCardViewDataList);

//
//        Calendar cal = Calendar.getInstance();
//
//        cal.setTimeInMillis(System.currentTimeMillis());
//        cal.clear();
//        cal.set(2019,2,28,15,56);
//
//        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//        // cal.add(Calendar.SECOND, 5);
//        alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


        mImg1 = findViewById(R.id.img1);
        mImg2 = findViewById(R.id.img2);
        mImg3 = findViewById(R.id.img3);
        mImg4 = findViewById(R.id.img4);


        mImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FruitAndVegetables.class));
            }
        });

        mImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DietCard.class));
            }
        });

        mImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HealthCard.class));
            }
        });

        mImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Hygiene.class));
            }
        });


        txtSpeechInput = findViewById(R.id.txtSpeechInput);
        txtSpeechInput2 = findViewById(R.id.txtSpeechInput2);
        txtSpeechInput3 = findViewById(R.id.txtSpeechInput3);
        txtSpeechInput4 = findViewById(R.id.txtSpeechInput4);
        txtSpeechInput5 = findViewById(R.id.txtSpeechInput5);
        txtSpeechMap = findViewById(R.id.txtSpeechInputMap);
        b1 = findViewById(R.id.playfunc);
        b2 = findViewById(R.id.playfunc1);
        b3 = findViewById(R.id.playfunc2);
        b4 = findViewById(R.id.playfunc3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.pregnancy1);
                //mp.start();
                playingaudio("week1.mp3");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playingaudio("week2.mp3");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                playingaudio("week3.mp3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                playingaudio("week4.mp3");
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewVideo.class);
                startActivity(i);
            }
        });

        txtSpeechInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String abc = txtSpeechInput.getText().toString();
                if (abc.equals("ha") || abc.equals("haan") || abc.equals("har")) {
                    //do your work here
//                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(i);

                    playingaudio("Answer_1.mp3");


                }
                if (abc.equals("nahi")) {
                    playingaudio("Question_2.mp3");
                    promptSpeechInputSecond();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtSpeechInput2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String abc = txtSpeechInput.getText().toString();
                if (abc.equals("ha") || abc.equals("haan") || abc.equals("har")) {
                    //do your work here
//                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(i);

                    playingaudio("Answer_2.mp3");


                }
                if (abc.equals("nahi")) {
                    playingaudio("Question_3.mp3");
                    promptSpeechInputThird();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtSpeechInput3.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String abc = txtSpeechInput.getText().toString();
                if (abc.equals("ha") || abc.equals("haan") || abc.equals("har")) {
                    //do your work here
//                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(i);

                    playingaudio("Answer_3.mp3");


                }
                if (abc.equals("nahi")) {
                    playingaudio("Question_4.mp3");
                    promptSpeechInputFourth();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtSpeechInput4.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String abc = txtSpeechInput.getText().toString();
                if (abc.equals("ha") || abc.equals("haan") || abc.equals("har")) {
                    //do your work here
//                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(i);

                    playingaudio("Answer_4.mp3");


                }
                if (abc.equals("nahi")) {
                    playingaudio("Question_5.mp3");
                    promptSpeechInputFifth();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtSpeechInput5.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String abc = txtSpeechInput.getText().toString();
                if (abc.equals("ha") || abc.equals("haan") || abc.equals("har")) {
                    //do your work here
//                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(i);

                    playingaudio("Answer_5.mp3");


                }
                if (abc.equals("nahi")) {
                    playingaudio("Question_5.mp3");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtSpeechMap.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String abc = txtSpeechMap.getText().toString();
                if (abc.equals("Map") || abc.equals("map") || abc.equals("Maps") || abc.equals("maps")) {
                    //do your work here
                    Intent i = new Intent(MainActivity.this,RiddhiMap.class);
                    startActivity(i);



                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(new Intent(MainActivity.this, SOSActivity.class));
            }
        });

        mEseva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ESeva.class));
            }
        });

        //mCardStack.setContentResource(R.layout.card_layout);
        //mCardStack.setStackMargin(20);


//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week1),R.drawable.image1,getResources().getString(R.string.videostring)));
//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week2),R.drawable.image2,getResources().getString(R.string.videostring)));
//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week3),R.drawable.image3,getResources().getString(R.string.videostring)));
//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week4),R.drawable.image4,getResources().getString(R.string.videostring)));
//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week5),R.drawable.image5,getResources().getString(R.string.videostring)));
//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week6),R.drawable.image6,getResources().getString(R.string.videostring)));
//        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week7),R.drawable.image7,getResources().getString(R.string.videostring)));


        //mCardStack.setAdapter(new CardStackAdapter(this,womenCardViewDataList));

        //mCardStack.setListener(this);

    }


//    @Override
//    public boolean swipeEnd(int i, float v) {
//        return (v>300)?true:false;
//    }
//
//    @Override
//    public boolean swipeStart(int i, float v) {
//        return false;
//    }
//
//    @Override
//    public boolean swipeContinue(int i, float v, float v1) {
//        return false;
//    }
//
//    @Override
//    public void discarded(int i, int i1) {
//
//    }
//
//    @Override
//    public void topCardTapped() {
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reset) {
            // mCardStack.reset(true);
            return true;
        }
        if (id == R.id.voice) {
//            Toast.makeText(MainActivity.this,"Voice",Toast.LENGTH_LONG).show();
            playingaudio("Question_1.mp3");

            promptSpeechInput();

            return true;
        }
        // if (id == R.id.suggestion) {
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for(DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {
//                        String val = mDataSnapshot.child("hey").getValue().toString();
//                        Log.i("abc",val);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
        //}

        if (id == R.id.rate) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.rate_dialog, null);

            radio1 = dialogView.findViewById(R.id.happy);
            radio2 = dialogView.findViewById(R.id.neutral);
            radio3 = dialogView.findViewById(R.id.sad);

            dialogBuilder.setView(dialogView);


            final MediaPlayer mAsk;
            mAsk = MediaPlayer.create(this, R.raw.feedback);
            mAsk.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mAsk.setLooping(true);
            mAsk.start();


            final MediaPlayer mThx;
            mThx = MediaPlayer.create(this, R.raw.thanx);
            mThx.setAudioStreamType(AudioManager.STREAM_MUSIC);


            radio1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Happy", Toast.LENGTH_LONG).show();
                    mAsk.stop();
                    mThx.start();


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 10 seconds
                            System.exit(0);

                        }
                    }, 9000);

                }
            });

            radio2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Neutral", Toast.LENGTH_LONG).show();
                    mAsk.stop();
                    mThx.start();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 10 seconds
                            System.exit(0);
                        }
                    }, 9000);

                }
            });

            radio3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Sad", Toast.LENGTH_LONG).show();
                    mAsk.stop();
                    mThx.start();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 10 seconds
                            System.exit(0);
                        }
                    }, 9000);

                }
            });


            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        }
//        if(id == R.id.hospital)
//        {
//            //startActivity(new Intent(this,MapsActivity.class));
//
//        }
        return super.onOptionsItemSelected(item);
    }

//    public void showContent() {
//
//
//    }

    public void playingaudio(String audioname) {
        AssetFileDescriptor afd = null;
        try {
            afd = getAssets().openFd(audioname);
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechInputSecond() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT_TWO);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechInputThird() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT_THREE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechInputFourth() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT_FOUR);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechInputFifth() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT_FIVE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechInputMap() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT_MAP);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechInput.setText(result.get(0));

                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT_TWO: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechInput2.setText(result.get(0));

                }
                break;

            }

            case REQ_CODE_SPEECH_INPUT_THREE: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechInput3.setText(result.get(0));

                }
                break;

            }

            case REQ_CODE_SPEECH_INPUT_FOUR: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechInput4.setText(result.get(0));

                }
                break;


            }

            case REQ_CODE_SPEECH_INPUT_MAP: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechMap.setText(result.get(0));

                }
                break;
            }
        }
    }

//            case REQ_CODE_SPEECH_INPUT_FIVE: {
//                if (resultCode == RESULT_OK && null != data) {
//
//                    ArrayList<String> result = data
//                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//
//                    txtSpeechInput5.setText(result.get(0));
//
//                }
//                break;
//
//            }






        public void setLocale (String localeName){
            if (localeName.equals(currentLanguage)) {
                myLocale = new Locale(localeName);
                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.locale = myLocale;
                res.updateConfiguration(conf, dm);
                Intent refresh = new Intent(this, MainActivity.class);
                refresh.putExtra(currentLang, localeName);
                startActivity(refresh);
            }
        }


        public void alarmfunc () {


//        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        Calendar firingCal = Calendar.getInstance();
//        Calendar currentCal = Calendar.getInstance();
//
//        firingCal.set(Calendar.HOUR, 16); // At the hour you wanna fire
//        firingCal.set(Calendar.MINUTE, 15); // Particular minute
//        firingCal.set(Calendar.SECOND, 0); // particular second
//
//        long intendedTime = firingCal.getTimeInMillis();
//        long currentTime = currentCal.getTimeInMillis();
//
//        if (intendedTime >= currentTime) {
//
//            alarmManager.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
//        } else {
//            firingCal.add(Calendar.DAY_OF_MONTH, 1);
//            intendedTime = firingCal.getTimeInMillis();
//            alarmManager.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
//        }

//        Calendar cal = Calendar.getInstance();

//        int cal_year = cal.get(Calendar.YEAR);
//        int cal_month = cal.get(Calendar.MONTH);
//        int cal_day_of_month = cal.get(Calendar.DAY_OF_MONTH);
//        int cal_hour_of_day = cal.get(Calendar.HOUR_OF_DAY);
//        int cal_min = cal.get(Calendar.MINUTE);


            //Toast.makeText(this, " " +cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " +cal.get(Calendar.DAY_OF_MONTH) + " " +cal.get(Calendar.HOUR_OF_DAY)+" "+cal.get(Calendar.MINUTE),Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 23433, intent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//
//        Calendar cal_alarm = Calendar.getInstance();
//
//        cal_alarm.set(Calendar.YEAR, 2019);
//        cal_alarm.set(Calendar.MONTH, 3);
//        cal_alarm.set(Calendar.DAY_OF_MONTH, 2);
//        cal_alarm.set(Calendar.HOUR_OF_DAY, 8);
//        cal_alarm.set(Calendar.MINUTE, 0);
//        cal_alarm.set(Calendar.SECOND, 0);

//        Intent intent = new Intent(this, AlarmReceiver.class);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, 0);
//        Calendar calSet = Calendar.getInstance();
//
////        int cal_year = calSet.get(Calendar.YEAR);
////        int cal_month = calSet.get(Calendar.MONTH);
////        int cal_day_of_month = calSet.get(Calendar.DAY_OF_MONTH);
//        int cal_hour_of_day = calSet.get(Calendar.HOUR_OF_DAY);
//        int cal_min = calSet.get(Calendar.MINUTE);
//
//        calSet.set(Calendar.HOUR_OF_DAY,cal_hour_of_day);
//        calSet.set(Calendar.MINUTE, cal_min);
//        calSet.set(Calendar.SECOND, 0);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                calSet.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
//
////        Intent myIntent = new Intent(MainActivity.this , AlarmReceiver.class);
////        calendar.set(Calendar.HOUR_OF_DAY, 8);
////        calendar.set(Calendar.MINUTE, 00);
////        calendar.set(Calendar.SECOND, 00);
////        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);

            Calendar calender = Calendar.getInstance();

            calender.set(Calendar.DAY_OF_WEEK, 7);  //here pass week number
            calender.set(Calendar.HOUR_OF_DAY, 16);  //pass hour which you have select
            calender.set(Calendar.MINUTE, 24);  //pass min which you have select
            calender.set(Calendar.SECOND, 0);
            calender.set(Calendar.MILLISECOND, 0);

            Calendar now = Calendar.getInstance();
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

            if (calender.before(now)) {    //this condition is used for future reminder that means your reminder not fire for past time
                calender.add(Calendar.DATE, 7);
            }

            final int _id = (int) System.currentTimeMillis();  //this id is used to set multiple alarms

            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), _id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), 7 * 24 * 60 * 60 * 1000, pendingIntent);

        }
    }


