package ktu.edu.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v){
        if (v.getId() == R.id.playGame){
            Intent intent = new Intent(this, PlayGame.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.settings){
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.howToPlay){
            Intent intent = new Intent(this, HowToPlay.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.credits){
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.history){
            Intent intent = new Intent(this, History.class);
            startActivity(intent);
        }
    }
}
