package com.example.randomnumbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    
    @Override
    public void onClick(View objClicked)
    {
        try
        {
            EditText editText = (EditText) findViewById(R.id.TXTUserName);
            TextView lblRandom = (TextView) findViewById(R.id.LBLRandom);
    
            switch (objClicked.getId())
            {
                case R.id.BTNSubmit:
                    String textToPass = editText.getText().toString();
    
                    //Open Next Page
                    Intent nextPage = new Intent(this, Guessing_Game.class);
    
                    //Pass UserName To The Next Page
                    nextPage.putExtra(Intent.EXTRA_TEXT, textToPass);
                    startActivity(nextPage);
                    
                break;
                
                case R.id.BTNRandomizer:
                    Random randomNumber = new Random();
                    int num = randomNumber.nextInt(21 - 1) + 1; //Was only 0-19, so add 1, now it's 1-20
                    lblRandom.setText("" + num);
    
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