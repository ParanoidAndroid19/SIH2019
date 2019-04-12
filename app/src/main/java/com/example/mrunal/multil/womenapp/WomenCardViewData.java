package com.example.mrunal.multil.womenapp;

import android.content.Intent;

public class WomenCardViewData {

    public String mWeekNo;
    public int mImageId;
    public String mGotoVideo;

    public WomenCardViewData()
    {

    }
    public WomenCardViewData(String mWeekNo, Integer mImageId, String mGotoVideo) {
        this.mWeekNo = mWeekNo;
        this.mImageId = mImageId;
        this.mGotoVideo = mGotoVideo;
    }

    public String getmWeekNo() {
        return mWeekNo;
    }

    public void setmWeekNo(String mWeekNo) {
        this.mWeekNo = mWeekNo;
    }

    public Integer getmImageId() {
        return mImageId;
    }

    public void setmImageId(Integer mImageId) {
        this.mImageId = mImageId;
    }

    public String getmGotoVideo() {
        return mGotoVideo;
    }

    public void setmGotoVideo(String mGotoVideo) {
        this.mGotoVideo = mGotoVideo;
    }
}
