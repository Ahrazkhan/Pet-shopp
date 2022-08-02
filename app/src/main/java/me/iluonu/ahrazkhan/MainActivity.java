package me.iluonu.ahrazkhan;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    AlertDialog.Builder builder;


    float finalTotalCoins = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Adapter adapter = new Adapter(this);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tab = findViewById(R.id.tab);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("Focus",0));
        viewPager.setOffscreenPageLimit(3);


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new TabLayoutMediator(tab, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0) tab.setText("Home") ;
                if (position==1) tab.setText("Cart") ;
                if (position==2) tab.setText("Payment");
                if (position==0) tab.setIcon(R.drawable.shopping_bag);
                if (position==1) tab.setIcon(R.drawable.shopping_cart);
                if (position==2) tab.setIcon(R.drawable.purse);

            }
        }).attach();


        findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences s = getSharedPreferences("login",0);
                SharedPreferences.Editor editor = s.edit();
                editor.putInt("login",0);
                editor.apply();

                startActivity(new Intent(MainActivity.this, LoginPage.class));
                finish();
            }
        });





    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Are you sure to Exit");
        builder1.setTitle("Exit");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       finishAffinity();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

}
}