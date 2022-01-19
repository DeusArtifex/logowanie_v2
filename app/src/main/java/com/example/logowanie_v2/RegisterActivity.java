package com.example.logowanie_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button accept;
    private EditText formEmail, formPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        formEmail = findViewById(R.id.formMail);
        formPass = findViewById(R.id.formPass);

        //Nasłuchuj przycisków
        accept = findViewById(R.id.accept);
        accept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mail = formEmail.getText().toString();
        String pass = formPass.getText().toString();
        if(checkMail(mail) && checkPass(pass)){
            Toast.makeText(this, "Zarejestrowano", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Błąd", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkMail(String mail){
        Pattern pattern = Pattern.compile("^(.*)@([a-z]{3,})\\.([a-z]{2,}$)");
        Matcher matcher = pattern.matcher(mail);
        if(matcher.find()){
            return true;
        }else {
            formEmail.setError("Niepoprawny email");
            Toast.makeText(this, "Błąd", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkPass(String pass){
        String strPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,36}$";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(pass);
        if(matcher.find()){
            return true;
        } else{
            formPass.setError("Wprowadź poprawne hasło");
            Toast.makeText(this, "Błąd", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}