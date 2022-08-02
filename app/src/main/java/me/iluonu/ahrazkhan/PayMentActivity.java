package me.iluonu.ahrazkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PayMentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ment);

        EditText CardNo    = findViewById(R.id.cardno);
        EditText CardHName = findViewById(R.id.cardHname);
        EditText cvv       = findViewById(R.id.cvv);
        EditText expire    = findViewById(R.id.expire);
        Button button = findViewById(R.id.button2);


        String SCardNo   = CardNo   .getText().toString();
        String SCardHName= CardHName.getText().toString();
        String Scvv      = cvv      .getText().toString();
        String Sexpire   = expire   .getText().toString();
        button.setOnClickListener(v -> {
            if (SCardNo=="" || SCardHName=="" || Scvv=="" || Sexpire=="") {
            Toast.makeText(this, "Fill Your Card Details", Toast.LENGTH_SHORT).show();

        }
        else {
            startActivity(new Intent(this,PaymentSuccessful.class));

        }});




    }
}