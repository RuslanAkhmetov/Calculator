package com.bignerdranch.android.calculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.calculator.R;
import com.bignerdranch.android.calculator.model.CalculatorImpl;
import com.bignerdranch.android.calculator.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    private final String TAG = "Calculator ActivityTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.result);

        presenter = new CalculatorPresenter(this, new CalculatorImpl(), new CalculatorState());
        if (savedInstanceState != null) {
            showResult(savedInstanceState.getString("result_key"));
            presenter.calculatorState = savedInstanceState.getParcelable("calculatorState");

            Log.d(TAG, savedInstanceState.getString("result_key"));
        }

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        Map<Integer, Operator> operands = new HashMap<>();
        operands.put(R.id.key_plus, Operator.ADD);
        operands.put(R.id.key_minus, Operator.SUB);
        operands.put(R.id.key_multiple, Operator.MULT);
        operands.put(R.id.key_divide, Operator.DIV);


        View.OnClickListener digitClickListener = view -> presenter.onDigitPressed(digits.get(view.getId()));

        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        View.OnClickListener operandClickListener = view -> presenter.onOperandListener(operands.get(view.getId()));

        findViewById(R.id.key_plus).setOnClickListener(operandClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operandClickListener);
        findViewById(R.id.key_multiple).setOnClickListener(operandClickListener);
        findViewById(R.id.key_divide).setOnClickListener(operandClickListener);

        findViewById(R.id.key_equal).setOnClickListener(view -> presenter.onEqualListener());

        findViewById(R.id.key_dot).setOnClickListener( view ->  presenter.onDotListener());

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("result_key", resultTxt.getText().toString());
        outState.putParcelable("calculatorState", presenter.calculatorState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}