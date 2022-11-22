package com.bignerdranch.android.calculator.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.android.calculator.model.Calculator;
import com.bignerdranch.android.calculator.model.Operator;

public class CalculatorState implements Parcelable {
    public CalculatorState() {
        this.isDotPressed = false;
        this.exponent = -1;
    }

    double argOne;

    Double argTwo;

    Operator selectedOperator;

    boolean isDotPressed;

    int exponent ;

    protected CalculatorState(Parcel in) {
        argOne = in.readDouble();
        if (in.readByte() == 0) {
            argTwo = null;
        } else {
            argTwo = in.readDouble();
        }
        isDotPressed = in.readByte() != 0;
        exponent = in.readInt();
    }

    public static final Creator<CalculatorState> CREATOR = new Creator<CalculatorState>() {
        @Override
        public CalculatorState createFromParcel(Parcel in) {
            return new CalculatorState(in);
        }

        @Override
        public CalculatorState[] newArray(int size) {
            return new CalculatorState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(argOne);
        if (argTwo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(argTwo);
        }
        parcel.writeByte((byte) (isDotPressed ? 1 : 0));
        parcel.writeInt(exponent);
    }
}
