package com.example.testorangapp.post.create;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testorangapp.R;
import com.example.testorangapp.databinding.ActivityPostCreateBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PostCreateActivity extends AppCompatActivity {
    private ActivityPostCreateBinding binding;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostCreateBinding.inflate(getLayoutInflater());
        viewPager = binding.postCreateViewPager2;
        tabLayout = binding.postCreateTabLayout;
       FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if (position == 0) {
                    return new PostCreateFirstPageFragment();
                } else {
                    Log.d("POSistioner",position+"");
                    return new PostCreateSecondPageFragment();
                }
            }
            @Override
            public int getItemCount() {
                return 2;
            }
        };


        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout,  viewPager,
                (tab, position) -> tab.setText(position == 0 ? "1" : "2")
        ).attach();

        setContentView(binding.getRoot());
    }
    public ViewPager2 getViewPager() {
        return viewPager;
    }
    private void TabLayout(){
        tabLayout.setSelectedTabIndicator(R.drawable.custom_tab_indicator);

// Apply padding to the tab views
        int tabPaddingStart = getResources().getDimensionPixelSize(R.dimen.tab_padding_start);
        int tabPaddingEnd = getResources().getDimensionPixelSize(R.dimen.tab_padding_end);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            tabView.setPadding(tabPaddingStart, 0, tabPaddingEnd, 0);
        }
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
//        return navController.navigateUp() || super.onSupportNavigateUp();
//    }

}