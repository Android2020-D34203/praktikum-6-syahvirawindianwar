package id.ac.id.telkomuniversity.tass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondactivityActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        Bundle b = getIntent().getExtras();

        textView = findViewById(R.id.hasil);

        String message = getIntent().getStringExtra("Text");
        textView.setText(message);

    }
}