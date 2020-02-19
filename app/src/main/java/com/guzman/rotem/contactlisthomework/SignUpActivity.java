package com.guzman.rotem.contactlisthomework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signupBtn = findViewById(R.id.button_signup);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save information
                boolean successful = saveUserInfo();
                //if successful -> move
                if (successful){
                    moveToContacts();
                }else {//if not -> show toast
                    Toast.makeText(SignUpActivity.this, "something went wrong...", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private boolean saveUserInfo(){
        EditText nameET = findViewById(R.id.editTextName);
        EditText password = findViewById(R.id.editTextPassword);


        //save information
        SharedPreferences preferences = getSharedPreferences(Constants.LOGIN_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.LOGIN_NAME_KEY, nameET.getText().toString());
        editor.putString(Constants.LOGIN_PASSWORD_KEY, password.getText().toString());
        return editor.commit();

    }

    private void moveToContacts(){
        Intent intent = new Intent(SignUpActivity.this, ContactsActivity.class);
        startActivity(intent);
        finish();
    }
}
