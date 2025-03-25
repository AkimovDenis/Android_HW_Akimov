package kz.itstep.androidlesson3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Pair implements Parcelable {
    private int first;
    private int second;

    protected Pair(Parcel in) {
        first = in.readInt();
        second = in.readInt();
    }

    public static final Creator<Pair> CREATOR = new Creator<Pair>() {
        @Override
        public Pair createFromParcel(Parcel in) {
            return new Pair(in);
        }

        @Override
        public Pair[] newArray(int size) {
            return new Pair[size];
        }
    };

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public void getSecond(int second){
        this.second = second;
    }

    public void fetFirst(int first){
        this.second = first;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(first);
        dest.writeInt(second);
    }
}
