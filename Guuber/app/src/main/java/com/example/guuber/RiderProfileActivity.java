package com.example.guuber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RiderProfileActivity extends AppCompatActivity {

    Rider myself;
    String username;
    String email;
    String phoneNumber;
    TextView emailField;
    TextView usernameField;
    TextView phoneNumberField;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_profile_disp);

        /**display the back button**/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myself = new Rider("+263784446345", "musariri@ualberta.ca", "Tinashe",
                "Musariri");

        phoneNumberField = findViewById(R.id.phoneTextRdIn);
        usernameField = findViewById(R.id.usernameTextRdIn);
        emailField = findViewById(R.id.emailTextRdIn);

        phoneNumber = myself.getPhoneNumber();
        username = myself.getFirstName();
        email = myself.getEmail();

        phoneNumberField.setText(phoneNumber);
        usernameField.setText(username);
        emailField.setText(email);

        deleteButton = findViewById(R.id.deleteAccButtonRdIn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("first statement.");
            }
        });
    }
    /**implement logic here for what you want to
     * happen upon back button press**/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}