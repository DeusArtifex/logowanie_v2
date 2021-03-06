package com.example.logowanie_v2;

import static android.util.Patterns.EMAIL_ADDRESS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login, register, loginT, registerT;
    private EditText email, haslo;
    private static int LAUNCH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);

        //Wewnątrz
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        //Na toolbarze
        loginT = findViewById(R.id.loginTbr);
        registerT = findViewById(R.id.registerTbr);
        loginT.setOnClickListener(this);
        registerT.setOnClickListener(this);

    }

    public void onClick(View v) {
        int resID = v.getId();
        if(resID == R.id.loginBtn || resID == R.id.loginTbr){
            String mail = email.getText().toString();
            String pass = haslo.getText().toString();

            if(checkPass(pass) && checkMail(mail)){
                Toast.makeText(this, "Zalogowano "+mail, Toast.LENGTH_SHORT).show();
            }

        } else {
            Intent intent = new Intent(this, RegisterActivity.class);
            Toast.makeText(this, "register", Toast.LENGTH_SHORT).show();
            startActivityForResult(intent, LAUNCH_ACTIVITY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                email.setText(data.getStringExtra("mail"));
            }
        }
    }

    public boolean checkMail(String mail){
        Pattern pattern = EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(mail);
        if(matcher.find()){
            return true;
        }else {
            email.setError("Niepoprawny email");
            Toast.makeText(this, "Błąd", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkPass(String pass){
        if(pass.length() != 0){
            return true;
        } else{
            haslo.setError("Wprowadź hasło");
            Toast.makeText(this, "Błąd", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}