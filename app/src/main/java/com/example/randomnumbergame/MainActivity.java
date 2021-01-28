package com.example.randomnumbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickBTNSubmit(View view)
    {
        EditText editText = (EditText) findViewById(R.id.TXTUserName);
        String textToPass = editText.getText().toString();

        //Open Next Page
        Intent nextPage = new Intent(this, Guessing_Game.class);

        //Pass UserName To The Next Page
        nextPage.putExtra(Intent.EXTRA_TEXT, textToPass);
        startActivity(nextPage);

    }

    public void onClickBTNRandomizer(View randomizer)
    {
        TextView lblRandom = (TextView) findViewById(R.id.LBLRandom);
        Random randomNumber = new Random();
        int num = randomNumber.nextInt(21 - 1) + 1; //Was only 0-19, so add 1, now it's 1-20
        lblRandom.setText("" + num);

    }
}