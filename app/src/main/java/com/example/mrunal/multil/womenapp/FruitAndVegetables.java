package com.example.mrunal.multil.womenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;

public class FruitAndVegetables extends AppCompatActivity implements CardStack.CardEventListener {

    CardStack mCardStack;
    CardStackAdapter mCardAdapter;
    ArrayList<WomenCardViewData> womenCardViewDataList;
    ImageButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_and_vegetables);

        mCardStack = findViewById(R.id.containerFruitsAndVegetable);
        womenCardViewDataList = new ArrayList<>();
        mCardAdapter = new CardStackAdapter(this,womenCardViewDataList);

        mBackButton = findViewById(R.id.back_button);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FruitAndVegetables.this,MainActivity.class));
                finish();
            }
        });

        mCardStack.setContentResource(R.layout.card_layout);
        mCardStack.setStackMargin(20);

        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week1),R.drawable.fruits1,getResources().getString(R.string.videostring)));
        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week2),R.drawable.fruits2,getResources().getString(R.string.videostring)));
        womenCardViewDataList.add(new WomenCardViewData(getResources().getString(R.string.week3),R.drawable.fruits3,getResources().getString(R.string.videostring)));

        mCardStack.setAdapter(new CardStackAdapter(this,womenCardViewDataList));

        mCardStack.setListener(this);

    }


    @Override
    public boolean swipeEnd(int i, float v) {
        return (v>300)?true:false;
    }

    @Override
    public boolean swipeStart(int i, float v) {
        return false;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return false;
    }

    @Override
    public void discarded(int i, int i1) {

    }

    @Override
    public void topCardTapped() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reset) {
            mCardStack.reset(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
