package com.example.attendence_tracker.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class PeriodInstance implements Parcelable {
    private String date; // yyyy-MM-dd
    private String startTime;
    private String endTime;

    public PeriodInstance(String date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected PeriodInstance(Parcel in) {
        date = in.readString();
        startTime = in.readString();
        endTime = in.readString();
    }

    public static final Creator<PeriodInstance> CREATOR = new Creator<PeriodInstance>() {
        @Override
        public PeriodInstance createFromParcel(Parcel in) {
            return new PeriodInstance(in);
        }

        @Override
        public PeriodInstance[] newArray(int size) {
            return new PeriodInstance[size];
        }
    };

    public String getDate() { return date; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(startTime);
        dest.writeString(endTime);
    }
} 