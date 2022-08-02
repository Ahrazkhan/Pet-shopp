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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    Context context;
    ArrayList<String> ProductName ;
    ArrayList<String> ProductPrice ;
    ArrayList<String> ProductImg ;

    public ShopAdapter(Context context, ArrayList<String> productName, ArrayList<String> productPrice, ArrayList<String> productImg) {
        this.context = context;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImg = productImg;
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ite_shop, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder holder, int position) {

        holder.itemName.setText(ProductName.get(position));
        holder.itemPrice.setText("€ "+ProductPrice.get(position));
        byte[] decodedString = Base64.decode(ProductImg.get(position), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.img.setImageBitmap(decodedByte);
        holder.Add.setOnClickListener(v -> {
            BDBManger bdbManger = new BDBManger(context);
            bdbManger.AddRecord(ProductName.get(position),ProductPrice.get(position),ProductImg.get(position));

            Intent intent = new Intent(context,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(intent);
            SaveData("position",position/2);
            Toast.makeText(context, "Item Added to Cart successfully." , Toast.LENGTH_SHORT).show();
        });



    }

    @Override
    public int getItemCount() {
        return ProductName.size();
   }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,Add;
        TextView itemName, itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            Add = itemView.findViewById(R.id.AddBasket);


        }
    }
    void SaveData(String id,int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(id,value);
        editor.apply();

    }

}
