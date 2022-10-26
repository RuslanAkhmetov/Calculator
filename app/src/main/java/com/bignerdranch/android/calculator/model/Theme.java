package com.bignerdranch.android.calculator.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.bignerdranch.android.calculator.R;

public enum Theme {
    ONE(R.style.Theme_Calculator, R.string.theme_1, "themeone"),
    TWO(R.style.Theme_CalculatorDark, R.string.theme_2, "themetwo"),
    THREE(R.style.Theme_CalculatorLight, R.string.theme_3, "themethree");

    @StyleRes
    private  int themeRes;
    @StringRes
    private int title;

    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getTitle() {
        return title;
    }

    public int getThemeRes() {
        return themeRes;
    }
}
