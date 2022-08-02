package me.iluonu.ahrazkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        EditText UserName = findViewById(R.id.UserName);
        EditText Password = findViewById(R.id.Password);
        TextView iHave = findViewById(R.id.iHave);
        iHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button Login = findViewById(R.id.button);
        Login.setOnClickListener(v -> {
            if (UserName.getText().toString().equals("") || Password.getText().toString().equals("")) {

                Toast.makeText(this, "Fill Your Details", Toast.LENGTH_SHORT).show();

            }
            else {
                LoginDBManager loginDbManager = new LoginDBManager(this); Toast.makeText(this, loginDbManager.AddRecord(UserName.getText().toString(), Password.getText().toString()), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterPage.this, MainActivity.class));
                finish();
            }

        });


    }
}