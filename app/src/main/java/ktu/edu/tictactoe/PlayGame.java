package ktu.edu.tictactoe;

import android.content.DialogInterface;
import android.os.VibrationEffect;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PlayGame extends AppCompatActivity {

    private BoardViewClass boardView;
    private GameEngineClass gameEngine;
    HistoryDatabaseHandler db;
    public static final String MY_PREFERENCES = "GamePreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        boardView = findViewById((R.id.board));
        gameEngine = new GameEngineClass();
        boardView.setGameEngine(gameEngine);
        boardView.setMainActivity(this);
        newGame();
        db = new HistoryDatabaseHandler(this);
    }

    public void gameEnded(char c) {
        SharedPreferences sp = getSharedPreferences(MY_PREFERENCES, 0);
        boolean vibrator = sp.getBoolean("vibratorEnabled", true);
        if (vibrator == true) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(VibrationEffect.createOneShot(400, 2));
        }
        String msg = (c == 'T') ? "Game Tied" : "Game Over " + c + " win";

        String Date = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss", Locale.getDefault()).format(new Date());

        if (c == 'X') {
            Log.d("GAME", "Player won");
            db.addEntry(new HistoryEntry(0, "You won", Date));
        } else if (c == 'O'){
            Log.d("Game", "Computer won");
            db.addEntry(new HistoryEntry(0, "You lost", Date));
        } else {
            db.addEntry(new HistoryEntry(0, "Game tied", Date));
            Log.d("Game", "Tied");
        }

        new AlertDialog.Builder(this).setTitle(("Tic Tac Toe")).
                setCancelable(false)
                .setPositiveButton("New game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PlayGame.this.finish();
                    }
                })
                .setMessage(msg)
                .show();
    }

    private void newGame() {
        gameEngine.newGame();
        boardView.invalidate();
    }
}
