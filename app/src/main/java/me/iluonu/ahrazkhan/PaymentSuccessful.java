package me.iluonu.ahrazkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.UUID;

public class PaymentSuccessful extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        TextView tv = findViewById(R.id.textView8);

        tv.setText("Order No: "+UUID.randomUUID().toString());


    }
}