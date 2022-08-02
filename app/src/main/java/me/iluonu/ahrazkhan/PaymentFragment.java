package me.iluonu.ahrazkhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class PaymentFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        CardView Card1 = v.findViewById(R.id.Card1);
        CardView Card2 = v.findViewById(R.id.Card2);
        CardView Card3 = v.findViewById(R.id.Card3);
        CardView Card4 = v.findViewById(R.id.Card4);
        CardView Card5 = v.findViewById(R.id.Card5);
        CardView Card6 = v.findViewById(R.id.Card6);
        CardView Card7 = v.findViewById(R.id.Card7);
        CardView Card8 = v.findViewById(R.id.Card8);



        Card1.setOnClickListener(V->{
            Intent intent = new Intent(getContext(),PayMentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getContext().startActivity(intent);

        });








        Card2.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});
        Card3.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});
        Card4.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});
        Card5.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});
        Card6.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});
        Card7.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});
        Card8.setOnClickListener(V->{getContext().startActivity(new Intent(getContext(),PayMentActivity.class));});











        return v;
    }
}