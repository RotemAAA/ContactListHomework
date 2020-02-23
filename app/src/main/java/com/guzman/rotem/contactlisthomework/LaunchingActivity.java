package com.guzman.rotem.contactlisthomework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class LaunchingActivity extends AppCompatActivity {

    Runnable runnable2;
    int counter = 0;
    int count = 0;
    View view1, view2, view3, view4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        view1 = findViewById(R.id.view_1);
        view2 = findViewById(R.id.view_2);
        view3 = findViewById(R.id.view_3);
        view4 = findViewById(R.id.view_4);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @SuppressLint("WrongViewCast")
            @Override
            public void run() {
                count++;
                if (count <= 4) {
//                    View view = findViewById(R.id.view_1);
////                    ((TextView) findViewById(R.id.view_1)).setText("" + System.currentTimeMillis());
//                view.setBackgroundColor(43234);
                    updateViewColors();
                    handler.postDelayed(this, 1000);
                } else {
                    if (chackLoggedIn()){
                        sendToContact();
                    }else {
                        sendToSignUp();
                    }
                }
            }
        };

        handler.postDelayed(runnable, 1000);


//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {

//                }
//            };



//        handler.postDelayed(runnable, 4000);
       // handler.removeCallbacks(runnable);

    }

    private void updateViewColors(){
        view1.setBackgroundColor(getResources().getColor(getRandomColor()));
        view2.setBackgroundColor(getResources().getColor(getRandomColor()));
        view3.setBackgroundColor(getResources().getColor(getRandomColor()));
        view4.setBackgroundColor(getResources().getColor(getRandomColor()));
    }
    private int getRandomColor(){
        ArrayList<Integer> color = new ArrayList<>();
        color.add(R.color.colorAccent);
        color.add(R.color.colorGreen);
        color.add(R.color.colorPrimary);
        color.add(R.color.colorPurple);
        color.add(R.color.colorPrimaryDark);
        color.add(R.color.colorRed);
        color.add(R.color.colorWhite);
        color.add(R.color.colorYellow);
        //add colors
        Random random = new Random();
        int pointer = random.nextInt(color.size());

        return color.get(pointer);
    }

    private boolean chackLoggedIn(){

        //check shared preferences if log in information
        SharedPreferences preferences = getSharedPreferences(Constants.LOGIN_PREF_NAME, MODE_PRIVATE);
        String name = preferences.getString(Constants.LOGIN_NAME_KEY, Constants.NOT_FOUND);
        String password = preferences.getString(Constants.LOGIN_PASSWORD_KEY,Constants.NOT_FOUND);

       return (!(name.equals(Constants.NOT_FOUND) || password.equals(Constants.NOT_FOUND)));
    }

//    private void send(){
//        Intent intent;
//        if (chackLoggedIn()) intent = new Intent(LaunchingActivity.this, null);
//        else intent = new Intent(LaunchingActivity.this, null);
//        startActivity(intent); //sends user to set activity in intent
//        finish(); //deletes activity from stack
//    }
//
    private void sendToSignUp(){

        Intent intent = new Intent(LaunchingActivity.this, SignUpActivity.class);
        startActivity(intent); //sends user to set activity in intent
        finish(); //deletes activity from stack
    }

    private void sendToContact(){

        Intent intent = new Intent(LaunchingActivity.this, ContactsActivity.class);
        startActivity(intent);
        finish();
    }
}
