package ktu.edu.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;

public class Settings extends AppCompatActivity {

    public static final String MY_PREFERENCES = "GamePreferences";
    Button b1, b2;
    HistoryDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new HistoryDatabaseHandler(this);
        loadSettings();
        b1 = findViewById(R.id.save_button);
        b2 = findViewById(R.id.empty_db);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteEntries();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    void loadSettings()
    {
        SharedPreferences sharedPrefs = getSharedPreferences(MY_PREFERENCES, 0);
        boolean vibratorEnabled = sharedPrefs.getBoolean("vibratorEnabled", true);
        String theme = sharedPrefs.getString("theme", "light");

        CheckBox vibratorEnabledBox = findViewById(R.id.vibrate_box);


        vibratorEnabledBox.setChecked(vibratorEnabled);
    }
    void saveSettings()
    {
        SharedPreferences sharedPref = getSharedPreferences(MY_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        CheckBox vibratorEnabledBox = (CheckBox)findViewById(R.id.vibrate_box);

        editor.putBoolean("vibratorEnabled", vibratorEnabledBox.isChecked());
        editor.commit();
    }
}
