package me.iluonu.ahrazkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        EditText UserName = findViewById(R.id.UserName);
        EditText Password = findViewById(R.id.Password);
        TextView Create = findViewById(R.id.Create);
        LoginDBManager loginDbManager = new LoginDBManager(this);

        SharedPreferences s = getSharedPreferences("login",0);

        if (s.getInt("login",0) == 1){
            startActivity(new Intent(LoginPage.this,MainActivity.class));
        }


        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, RegisterPage.class));
            }
        });
        Button Login = findViewById(R.id.button);
        Login.setOnClickListener(v -> {

            if (UserName.getText().toString().toLowerCase(Locale.ROOT).equals("") & Password.getText().toString().toLowerCase(Locale.ROOT).equals("")) {
                Toast.makeText(this, "fill All Details", Toast.LENGTH_SHORT).show();
            } else {
                if (loginDbManager.CheckUsernameAndPassword(UserName.getText().toString(), Password.getText().toString()).equals("LogIn Successful")) {
                    Toast.makeText(this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = s.edit();
                    editor.putInt("login",1);
                    editor.apply();
                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                    finish();

                } else {
                    Toast.makeText(this, "Check Your Password", Toast.LENGTH_SHORT).show();
                }
            }

        });

        findViewById(R.id.c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,aboutUs.class));
            }
        });

    }
    // String GetData(){
    //     String json = null;
    //     try {
    //         InputStream in = this.getAssets().open("data.json");
    //         int size = in.available();
    //         byte[] bytes = new byte[size];
    //         in.read(bytes);
    //         in.close();
    //         json = new String(bytes, StandardCharsets.UTF_8);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return json;
    // }
}