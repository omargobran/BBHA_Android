package com.example.omarg.bbhatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //DBManager db;
    SessionManager session;
    EditText username, password;
    TextView register;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        register = findViewById(R.id.textView1);
        button = findViewById(R.id.button1);
        //db = new DBManager(this);
        session = new SessionManager(this);
        session.setLoggedIn(false);
        if (session.loggedIn()) {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void btnLogin(View view) {
        /*String user = username.getText().toString(), pass = password.getText().toString();
        if(user.matches("") || pass.matches(""))
            Toast.makeText(getApplicationContext(),"Fields are empty! Please fill fields and try again!",Toast.LENGTH_LONG).show();
        else {
            boolean checkPass = db.checkPass(user, pass);
            if (checkPass) {
                session.setLoggedIn(true);
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            /*} else
                Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG).show();
        }*/
    }

    public void clkText(View view) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerIntent);
    }
}
