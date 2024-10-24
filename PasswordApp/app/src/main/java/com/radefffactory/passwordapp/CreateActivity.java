package com.radefffactory.passwordapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    EditText et_password1, et_password2;
    Button b_create, b_delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        et_password1 = findViewById(R.id.et_password1);
        et_password2 = findViewById(R.id.et_password2);

        b_create = findViewById(R.id.b_create);
        b_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = et_password1.getText().toString().trim();
                String pass2 = et_password2.getText().toString().trim();

                if (pass1.length() > 2 && pass1.equals(pass2)) {
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("password", pass1);
                    editor.apply();

                    Toast.makeText(CreateActivity.this, "Password saved!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreateActivity.this, "Password issue!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_delete = findViewById(R.id.b_delete);
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("password", "");
                editor.apply();

                Toast.makeText(CreateActivity.this, "Password deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
