package com.example.omarg.bbhatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //DBManager db;
    EditText username, password, cPassword;
    TextView login;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        cPassword = findViewById(R.id.editText3);
        login = findViewById(R.id.textView1);
        button = findViewById(R.id.button1);
        //db = new DBManager(this);
    }

    public void btnRegister(View view) {
        /*String user = username.getText().toString(), pass = password.getText().toString(), cPass = cPassword.getText().toString();
        if(user.matches("") || pass.matches("") || cPass.matches(""))
            Toast.makeText(getApplicationContext(),"Fields are empty! Please fill fields and try again!",Toast.LENGTH_LONG).show();
        else {
            if (pass.matches(cPass)) {
                Boolean checkUser = db.checkUser(user);
                if (checkUser) {
                    Boolean insert = db.insert(user, pass);
                    if (insert) {
                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();*/
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    /*}
                } else
                    Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(), "Passwords don't match! Try Again!", Toast.LENGTH_LONG).show();
        }*/
    }

    public void clkText(View view) {
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
    }
}
