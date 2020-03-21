package com.example.jungsoosmarket.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.jungsoosmarket.R;
import com.example.jungsoosmarket.databinding.HomeActivityBinding;
import com.example.jungsoosmarket.di.Injection;
import com.example.jungsoosmarket.ui.addtobasket.AddToBasketActivity;
import com.example.jungsoosmarket.ui.basket.BasketFragment;
import com.example.jungsoosmarket.ui.common.BaseActivity;

/**
 * The HomeScreen for the application.
 * The user will be navigated to this screen after the Splash.
 *
 * In this we add in the BasketFragment to the activity.
 */
public class HomeActivity extends BaseActivity {


    private HomeActivityBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.home_activity);
        initHomeViewModel();
        homeViewModel.loadDummyProducts();

        addBasketFragment(savedInstanceState);
        binding.floatingActionButton.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, AddToBasketActivity.class)));
    }

    /**
     * Method creates the instance of BasketFragment and performs
     * a FragmentTransaction to add it to the UI.
     * @param savedInstanceState
     */
    private void addBasketFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BasketFragment.newInstance())
                    .commitNow();
        }
    }

    /**
     * Initializes the HomeViewModel.
     */
    private void initHomeViewModel() {
        HomeViewModelProviderFactory factory = Injection.provideHomeViewModelProviderFactory(this);
        homeViewModel = new ViewModelProvider(this,factory).get(HomeViewModel.class);
    }
}
