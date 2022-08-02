package me.iluonu.ahrazkhan;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BasketItemAdapter extends RecyclerView.Adapter<BasketItemAdapter.BasketItemViewHolder> {


    ArrayList<BasketModel> models;
    Context context;
    TextView PriceCounter;
    TextView ItemCounter;


    public BasketItemAdapter(ArrayList<BasketModel> models, Context context, TextView priceCounter, TextView itemCounter) {
        this.models = models;
        this.context = context;
        PriceCounter = priceCounter;
        ItemCounter = itemCounter;
    }

    @NonNull
    @Override
    public BasketItemAdapter.BasketItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item, parent, false);


        return new BasketItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketItemAdapter.BasketItemViewHolder holder, int position) {


        holder.ProductPrice.setText(String.format(" € %s", models.get(position).getPrice()));
        holder.ProductName.setText(String.format("%s", models.get(position).getProductName()));
        byte[] decodedString = Base64.decode(models.get(position).getImg(), Base64.DEFAULT);
       Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
       holder.ProductImg.setImageBitmap(decodedByte);
        holder.Delete.setOnClickListener(v -> {
            BDBManger bdbManger = new BDBManger(context);
           bdbManger.Card_Delete(models.get(position).getId());
          Intent intent = new Intent(context,MainActivity.class);
          intent.putExtra("Focus",1);
          intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
          context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        ItemCounter.setText(String.format("Procees to Buy  (%d)", models.size()));
        int aa = 0;
        setPriceCalculate(0);


       while (aa != models.size()) {
           setPriceCalculate(getPriceCalculate() + Integer.parseInt(models.get(aa).getPrice()));

           aa++;

       }
        PriceCounter.setText(" €"+ getPriceCalculate());


        return models.size();
    }

    public class BasketItemViewHolder extends RecyclerView.ViewHolder {
        ImageView ProductImg,Delete;
        TextView ProductName, ProductPrice;

        public BasketItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ProductImg = itemView.findViewById(R.id.productImg);
            ProductName = itemView.findViewById(R.id.Pname);
            ProductPrice = itemView.findViewById(R.id.Pprice);
            Delete = itemView.findViewById(R.id.Delete);


        }
    }
    void SaveData(int Price){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Price",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Price",Price);
        editor.apply();

    }




    int PriceCalculate;

    public int getPriceCalculate() {
        return PriceCalculate;
    }

    public void setPriceCalculate(int priceCalculate) {
        PriceCalculate = priceCalculate;
    }
}


