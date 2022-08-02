package me.iluonu.ahrazkhan;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ShopFragment extends Fragment {

    ArrayList<String> ProductName = new ArrayList<>();
    ArrayList<String> ProductPrice = new ArrayList<>();
    ArrayList<String> ProductImg = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_shop, container, false);


        RecyclerView recyclerView = v.findViewById(R.id.recy);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.scrollToPosition(GetData("position"));

        try {
            JSONObject object = new JSONObject(GetData());

            JSONArray array = object.getJSONArray("ProductDetails");
            for (int i = 0; i < array.length(); i++) {
                JSONObject LoginDatas = array.getJSONObject(i);
                ProductName.add(LoginDatas.getString("ProductName"));
                ProductPrice.add(LoginDatas.getString("ProductPrice"));
                ProductImg.add(LoginDatas.getString("ProductImg"));


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        ShopAdapter adapter = new ShopAdapter(getContext(), ProductName, ProductPrice, ProductImg);
        recyclerView.setAdapter(adapter);


        return v;


    }

    public static String AssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    String GetData() {
        String json = null;
        try {
            InputStream in = getActivity().getAssets().open("data.json");
            int size = in.available();
            byte[] bytes = new byte[size];
            in.read(bytes);
            in.close();
            json = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    int GetData(String id) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(id, 0);

        return sharedPreferences.getInt(id, 0);


    }
}