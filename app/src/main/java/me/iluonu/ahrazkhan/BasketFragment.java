package me.iluonu.ahrazkhan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    BasketItemAdapter basketItemAdapter;
    BDBManger bdbManger;
    RecyclerView recyclerView;
    ArrayList<BasketModel> model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_basket, container, false);


        TextView Price = v.findViewById(R.id.PriceCounter);
        TextView itemCount = v.findViewById(R.id.ItemCounter);
        itemCount.setOnClickListener(v1 -> {
            Intent intent = new Intent(getContext(),MainActivity.class);
            intent.putExtra("Focus",2);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getContext().startActivity(intent);
        });
       // Price.setText(GetData());
        bdbManger = new BDBManger(getContext());
        recyclerView = v.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        Cursor cursor = new BDBManger(getContext()).readalldata();
        model = new ArrayList<>();

        while (cursor.moveToNext()) {
            BasketModel obj = new BasketModel(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
            model.add(obj);
        }

        basketItemAdapter = new BasketItemAdapter(model, getContext(), Price,itemCount);
        recyclerView.setAdapter(basketItemAdapter);

        return v;
    }

    String GetData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Price", 0);

        return String.valueOf(sharedPreferences.getInt("Price", 0));


    }
}