package com.bignerdranch.android.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.calculator.R;
import com.bignerdranch.android.calculator.model.Theme;
import com.bignerdranch.android.calculator.model.ThemeRepository;
import com.bignerdranch.android.calculator.model.ThemeRepositoryImpl;

import java.util.List;

public class SelectThemeActivity extends AppCompatActivity {

    public static final String EXTRA_THEME = "EXTRA_THEME";

    private final String TAG = "Select Theme TAG";

    ThemeRepository themeRepository;

    private Theme selectedTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        findViewById(R.id.how_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru"));

                //открываем активити с проверкой наличие возможности выполнить действие
                startActivity(Intent.createChooser(browserIntent, null));
            }
        });

        ThemeRepository themeRepository = ThemeRepositoryImpl.getInstance(this);

        List<Theme> themes = themeRepository.getAll();

        LinearLayout container = findViewById(R.id.container);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_THEME)){
            selectedTheme = (Theme) intent.getSerializableExtra(EXTRA_THEME);
        }

        for (Theme theme: themes){
            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);

            TextView textView = itemView.findViewById(R.id.title);

            textView.setText(theme.getTitle());


            CardView cardView = itemView.findViewById(R.id.theme_card);


            cardView.setOnClickListener(view -> {

                Intent data = new Intent();

                data.putExtra(EXTRA_THEME, theme);

                setResult(Activity.RESULT_OK, data);

                finish();
            });

            ImageView checked = itemView.findViewById(R.id.checked);

            if(theme.equals(selectedTheme)){
                checked.setVisibility(View.VISIBLE);
            } else {
                checked.setVisibility(View.GONE);
            }

            container.addView(itemView);
        }

    }
}