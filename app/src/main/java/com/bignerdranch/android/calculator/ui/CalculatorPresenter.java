package com.bignerdranch.android.calculator.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.android.calculator.model.Calculator;
import com.bignerdranch.android.calculator.model.Operator;
import  java.lang.Math;

import java.text.DecimalFormat;

public class CalculatorPresenter  {

    private CalculatorView view;

    private DecimalFormat formatter = new DecimalFormat("#.#####");

    private Calculator calculator;

    CalculatorState calculatorState;

 /*   private double argOne;

    private Double argTwo;

    private Operator selectedOperator;

    private boolean isDotPressed = false;

    private int exponent = -1;*/


    public CalculatorPresenter(CalculatorView view, Calculator calculator, CalculatorState calculatorState) {
        this.view = view;
        this.calculator = calculator;
        this.calculatorState = calculatorState;
    }

   /* protected CalculatorPresenter(Parcel in) {
        argOne = in.readDouble();
        if (in.readByte() == 0) {
            argTwo = null;
        } else {
            argTwo = in.readDouble();
        }
        isDotPressed = in.readByte() != 0;
        exponent = in.readInt();
    }*/

    /*public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };*/

    public void onDigitPressed(int digit){
        if (calculatorState.isDotPressed){
            if (calculatorState.argTwo == null) {
                calculatorState.argOne += digit * Math.pow(10, calculatorState.exponent);
                showFormatted(calculatorState.argOne);
            } else {
                calculatorState.argTwo += digit * Math.pow(10, calculatorState.exponent);
                showFormatted(calculatorState.argTwo);
            }
            calculatorState.exponent --;
        } else {
            if (calculatorState.argTwo == null) {
                calculatorState.argOne = calculatorState.argOne * 10 + digit;
                showFormatted(calculatorState.argOne);
            } else {
                calculatorState.argTwo = calculatorState.argTwo * 10 + digit;
                showFormatted(calculatorState.argTwo);
            }
        }
    }

    public void onOperandListener(Operator operator) {
        if (calculatorState.selectedOperator!= null){
            calculatorState.argOne = calculator.perform(calculatorState.argOne, calculatorState.argTwo, calculatorState.selectedOperator);
            showFormatted(calculatorState.argOne);
        }
        calculatorState.selectedOperator = operator;
        calculatorState.argTwo = 0.0;
        calculatorState.isDotPressed = false;
        calculatorState.exponent = -1;

    }

    public void onEqualListener() {
        if (calculatorState.selectedOperator!= null && calculatorState.argTwo != null){
            calculatorState.argOne = calculator.perform(calculatorState.argOne, calculatorState.argTwo, calculatorState.selectedOperator);
            showFormatted(calculatorState.argOne);
            calculatorState.argTwo = null;
            calculatorState.isDotPressed = false;
            calculatorState.exponent = -1;
            calculatorState.argOne = 0.0;
            calculatorState.selectedOperator = null;
        }
    }

    public void onDotListener() {
        calculatorState.isDotPressed = true;
    }

    private  void showFormatted(double value){
       view.showResult(formatter.format(value).toString());
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(calculatorState.argOne);
        if (calculatorState.argTwo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(argTwo);
        }
        parcel.writeByte((byte) (isDotPressed ? 1 : 0));
        parcel.writeInt(exponent);
    }*/
}

