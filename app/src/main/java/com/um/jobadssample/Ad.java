package com.um.jobadssample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "ads")
public class Ad implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "_ID")
    private int mID;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "desc")
    private String mDesc;

    // "De-parcel object
    public Ad(Parcel in) {
        mTitle = in.readString();
        mDesc = in.readString();
        mID = in.readInt();
    }

    public Ad() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDesc);
        dest.writeInt(mID);
    }

    //creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Ad createFromParcel(Parcel in) {
            return new Ad(in);
        }

        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };

    public void setID(int id) {
        mID = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDesc (String desc) {
        mDesc = desc;
    }

    public int getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDesc() {
        return mDesc;
    }

    @Override
    public String toString() {
        return this.getID() + ", " + mTitle + ", " +  mDesc;
    }

}
