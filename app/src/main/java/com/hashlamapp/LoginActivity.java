package com.hashlamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameLoginEditText;
    private EditText passwordLoginEditText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize() {
        usernameLoginEditText = (EditText)findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText = (EditText)findViewById(R.id.passwordLoginEditText);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTemp = usernameLoginEditText.getText().toString();
                String passwordTemp = passwordLoginEditText.getText().toString();
                if(usernameTemp.isEmpty())
                    Toast.makeText(getApplicationContext(), getString(R.string.emptyError), Toast.LENGTH_SHORT).show();
                else if(passwordTemp.isEmpty())
                    Toast.makeText(getApplicationContext(), getString(R.string.emptyError), Toast.LENGTH_SHORT).show();
                else {

                }
            }
        });
    }
}