package me.iluonu.ahrazkhan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class Adapter extends FragmentStateAdapter {
    public Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ShopFragment();
        }
        else if (position == 1) {
            return new BasketFragment();
        }
        else if (position == 2) {
            return new PaymentFragment();
        }


        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
