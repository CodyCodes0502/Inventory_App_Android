package com.zybooks.cs_360_project.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zybooks.cs_360_project.R;
import com.zybooks.cs_360_project.model.Login;

public class LoginActivity extends AppCompatActivity {

    private Login login;
    private EditText userNameEditText, passwordEditText;
    private Button loginButton, newUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login db = new Login(this);

        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        newUserButton = findViewById(R.id.newUserButton);

        newUserButton.setOnClickListener(v -> {
            // Handle new user button click

            String username = userNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }
            else if (db.checkUsername(username)) {
            Toast.makeText(LoginActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
            } else {
                boolean updated = db.addUser(username, password);
                if (updated) {
                    Toast.makeText(LoginActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to add user", Toast.LENGTH_SHORT).show();
                }
        }
        });

        loginButton.setOnClickListener(v -> {
            // Handle login button click
            String username = userNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            boolean isValid = db.checkUser(username, password);

            if (!isValid) {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(LoginActivity.this, Inventory_Activity.class));
            }
        });
    }
}