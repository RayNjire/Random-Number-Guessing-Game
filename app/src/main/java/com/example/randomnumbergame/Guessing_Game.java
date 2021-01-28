package com.example.randomnumbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class Guessing_Game extends AppCompatActivity implements View.OnClickListener
{
    int guessThisNumber, guessCounter = 5;
    String userName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guessing_game);
            
            //Background Music
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.background_elevator_music);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            
            TextView lblGreetings = (TextView) findViewById(R.id.LBLGreetings);
            Button btnBegin = (Button) findViewById(R.id.BTNBegin);
            Button btnExit = (Button) findViewById(R.id.BTNExit);
            TextView lblInstructions = (TextView) findViewById(R.id.LBLInstructions);
            TextView lblInstructions2 = (TextView) findViewById(R.id.LBLInstructions2);
            TextView txtEnterNumber = (TextView) findViewById(R.id.TXTEnterNumber);
            TextView lblGuessHistory = (TextView) findViewById(R.id.LBLGuessHistory);
            Button btnGuess = (Button) findViewById(R.id.BTNGuess);
            TextView lblGuessesLeft = (TextView) findViewById(R.id.LBLGuessesLeft);
            
            Intent intent = getIntent();
            userName = intent.getStringExtra(Intent.EXTRA_TEXT);
            lblGreetings.setText("Hello " + userName + "! Should we start the guessing game?");
            lblGreetings.setVisibility(View.VISIBLE);
            
            btnBegin.setText("Yes, Begin");
            btnExit.setText("No, Exit");
            lblInstructions.setVisibility(View.INVISIBLE);
            lblInstructions2.setVisibility(View.INVISIBLE);
            txtEnterNumber.setVisibility(View.INVISIBLE);
            lblGuessHistory.setVisibility(View.INVISIBLE);
            btnGuess.setVisibility(View.INVISIBLE);
            lblGuessesLeft.setVisibility(View.INVISIBLE);
            
            TextView lblGuessesLeft2 = (TextView) findViewById(R.id.LBLGuessesLeft);
            lblGuessesLeft2.setText("Guesses Left: " + guessCounter);
            
        }

        catch (Exception e)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("On Create Method Error\n" + e.getMessage())
                    .setIcon(android.R.drawable.ic_dialog_alert);
    
        }
    }
    
    //This is how you're supposed to implement onCLick Listeners!!
    @Override
    public void onClick(View objClicked)
    {
        try
        {
            //These Datatypes Changes Every Property of the Objects
            TextView lblGreetings = (TextView) findViewById(R.id.LBLGreetings);
            Button btnBegin = (Button) findViewById(R.id.BTNBegin);
            Button btnExit = (Button) findViewById(R.id.BTNExit);
            TextView lblInstructions = (TextView) findViewById(R.id.LBLInstructions);
            TextView lblInstructions2 = (TextView) findViewById(R.id.LBLInstructions2);
            TextView txtEnterNumber = (TextView) findViewById(R.id.TXTEnterNumber);
            TextView lblGuessHistory = (TextView) findViewById(R.id.LBLGuessHistory);
            TextView lblGuessesLeft = (TextView) findViewById(R.id.LBLGuessesLeft);
            Button btnGuess = (Button) findViewById(R.id.BTNGuess);
            
            switch (objClicked.getId())
            {
                case R.id.BTNBegin:
                    lblGreetings.setVisibility(View.INVISIBLE);
                    lblInstructions.setVisibility(View.VISIBLE);
                    lblInstructions2.setVisibility(View.VISIBLE);
                    txtEnterNumber.setVisibility(View.VISIBLE);
                    lblGuessHistory.setVisibility(View.VISIBLE);
                    btnGuess.setVisibility(View.VISIBLE);
                    lblGuessesLeft.setVisibility(View.VISIBLE);
                    
                    btnBegin.setText("Try Another");
                    btnExit.setText("Exit Game");
                    txtEnterNumber.setTextColor(Color.WHITE);
                    txtEnterNumber.setText("");
                    lblGuessHistory.setText("");
                    guessCounter = 5;
                    lblGuessesLeft.setText("Guesses Left: " + guessCounter);
                    
                    Random randomNumber = new Random();
                    guessThisNumber = randomNumber.nextInt(21 - 1) + 1; //Was only 0-19, so add 1
                    
                break;
                
                case R.id.BTNExit:
                    System.exit(0);
                    
                break;
                
                case R.id.TXTEnterNumber:
                    txtEnterNumber.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    
                break;
                
                case R.id.BTNGuess:
                    int usersGuess = Integer.valueOf(txtEnterNumber.getText().toString());
                    String alreadyGuessed = lblGuessHistory.getText().toString();
                    lblGuessHistory.setText(alreadyGuessed + usersGuess + "\n");
                    
                    if (usersGuess != guessThisNumber)
                    {
                        Vibrator vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                        vibrate.vibrate(80);
                        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.wrong_answer_buzzer);
                        mediaPlayer.start();
                        
                        txtEnterNumber.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                        guessCounter--;
                        lblGuessesLeft.setText("Guesses Left: " + guessCounter);
                        txtEnterNumber.setText("");
                        
                        if (usersGuess < guessThisNumber)
                        {
                            Toast.makeText(this, "Guess Higher!", Toast.LENGTH_LONG).show();
                            
                        }
                
                        else if (usersGuess > guessThisNumber)
                        {
                            Toast.makeText(this, "Guess Lower!", Toast.LENGTH_LONG).show();
                            
                        }
                    }
                    
                    else if (usersGuess == guessThisNumber)
                    {
                        txtEnterNumber.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                        txtEnterNumber.setTextColor(Color.GREEN);
                        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ding);
                        mediaPlayer.start();
                        
                        lblGreetings.setVisibility(View.VISIBLE);
                        lblGreetings.setText("Congratulations on winning this round " + userName + "! Would you like to play again?");
                        btnBegin.setText("Another Round!");
                        lblInstructions.setVisibility((View.INVISIBLE));
                        lblGuessesLeft.setText("CORRECT! The Number is " + guessThisNumber);
                        btnGuess.setVisibility(View.INVISIBLE);
                        guessCounter = 5;
                        
                    }
                    
                    if(guessCounter == 0)
                    {
                        Vibrator vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                        vibrate.vibrate(300);
                        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ko_punches);
                        mediaPlayer.start();
                        
                        lblGuessesLeft.setText("Sorry the number was " + guessThisNumber + " Better luck next time");
                        btnGuess.setVisibility(View.INVISIBLE);
                        lblInstructions.setVisibility(View.INVISIBLE);
                        
                    }
                break;
            
            }
        }

        catch (Exception e)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Click Error")
                    .setMessage(objClicked.getId() + " Click Error\n" + e.getMessage())
                    .setIcon(android.R.drawable.ic_dialog_alert);
    
        }
    }
}