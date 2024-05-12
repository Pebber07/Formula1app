package com.example.formula1app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView emailText;
    private Button deleteProfile;
    private EditText newPasswordText;
    private Button updatePasswordButton;

    private void updatePassword(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Jelszó frissítve.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Sikertelen frissítés: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        deleteProfile = findViewById(R.id.deleteProfile);
        newPasswordText = findViewById(R.id.newPassword);
        updatePasswordButton = findViewById(R.id.updatePasswordButton);

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            emailText.setText(user.getEmail());
        }

        deleteProfile.setOnClickListener(v -> {
            if (user != null) {
                user.delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Profil törölve", Toast.LENGTH_SHORT).show();
                                Intent loginIntent = new Intent(ProfileActivity.this, Login.class);
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(loginIntent);
                                finish();
                            }
                        });
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        updatePasswordButton.setOnClickListener(v -> {
            String newPassword = newPasswordText.getText().toString().trim();
            if (TextUtils.isEmpty(newPassword)) {
                Toast.makeText(ProfileActivity.this, "Kérjük, adjon meg egy új jelszót.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newPassword.length() < 6) {
                Toast.makeText(ProfileActivity.this, "A jelszónak legalább 6 karakter hosszúnak kell lennie.", Toast.LENGTH_SHORT).show();
                return;
            }

            updatePassword(newPassword);
        });

    }
}

