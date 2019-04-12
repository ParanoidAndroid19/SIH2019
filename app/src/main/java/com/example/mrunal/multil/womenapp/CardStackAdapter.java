package com.example.mrunal.multil.womenapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends ArrayAdapter<WomenCardViewData> {

    Context mContext;
     List<WomenCardViewData> mWomenList = new ArrayList<>();

    public CardStackAdapter(@NonNull Context context, ArrayList<WomenCardViewData> womenCardViewData) {
        super(context, 0, womenCardViewData);
        context = mContext;
        mWomenList = womenCardViewData;
    }

    @Override
    public View getView(int position,@Nullable View convertView,ViewGroup parent) {

        WomenCardViewData womenCardViewData = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout,parent,false);
        }
        TextView mTxt = convertView.findViewById(R.id.week_text);
        ImageView imageView = convertView.findViewById(R.id.img_swipe);
//        Button mBtn = convertView.findViewById(R.id.go_to_video);

        mTxt.setText(womenCardViewData.getmWeekNo());
        imageView.setImageResource(womenCardViewData.getmImageId());
//        mBtn.setText(womenCardViewData.getmGotoVideo());

        return convertView;
    }
}
