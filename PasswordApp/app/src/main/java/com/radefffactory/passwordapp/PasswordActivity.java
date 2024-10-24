package com.radefffactory.passwordapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {

    EditText et_password;
    Button b_continue, b_create;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        et_password = findViewById(R.id.et_password);

        b_continue = findViewById(R.id.b_continue);
        b_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                String password = preferences.getString("password", "");
                boolean passwordExists = !password.isEmpty();

                if (passwordExists) {
                    String pass = et_password.getText().toString().trim();
                    if (password.equals(pass)) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        et_password.setText("");
                    } else {
                        Toast.makeText(PasswordActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    et_password.setText("");
                }
            }
        });

        b_create = findViewById(R.id.b_create);
        b_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                String password = preferences.getString("password", "");
                boolean passwordExists = !password.isEmpty();

                if (passwordExists) {
                    String pass = et_password.getText().toString().trim();
                    if (password.equals(pass)) {
                        startActivity(new Intent(getApplicationContext(), CreateActivity.class));
                        et_password.setText("");
                    } else {
                        Toast.makeText(PasswordActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), CreateActivity.class));
                    et_password.setText("");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        String password = preferences.getString("password", "");
        boolean passwordExists = !password.isEmpty();
        if (passwordExists) {
            et_password.setVisibility(View.VISIBLE);
            b_create.setText("Edit password");
        } else {
            et_password.setVisibility(View.GONE);
            b_create.setText("Create password");
        }
    }
}
