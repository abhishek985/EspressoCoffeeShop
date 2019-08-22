package com.noralynn.coffeeShop.beveragelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import com.noralynn.coffeeShop.R;
import com.noralynn.coffeeShop.beveragedetail.BeverageDetailActivity;
import com.noralynn.coffeeShop.coffeeshoplist.CoffeeShopListActivity;
import com.noralynn.coffeeShop.coffeeshoplist.CoffeeShopListViewPresenter;
import com.noralynn.coffeeShop.coffeeshoplist.idlingresource.CoffeeShopsIdlingResource;
import com.noralynn.coffeeShop.common.Beverage;

import static com.noralynn.coffeeShop.R.id.map_fab;

public class BeverageListActivity extends AppCompatActivity implements BeverageListView {

    @NonNull
    public static final String BEVERAGE_LIST_MODEL_BUNDLE_KEY = "BEVERAGE_LIST_MODEL_BUNDLE_KEY";

    @NonNull
    @SuppressWarnings("NullableProblems")
    private BeverageListViewPresenter presenter;

    @Nullable
    private RecyclerView beverageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        presenter = new BeverageListViewPresenter(this);
        presenter.onCreate(savedInstanceState);
    }


    public void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        beverageRecyclerView = (RecyclerView) findViewById(R.id.beverages_recycler);

        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(map_fab);
        floatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickFloatingActionButton();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(BEVERAGE_LIST_MODEL_BUNDLE_KEY, presenter.getBeverageListModel());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void startBeverageActivity(@NonNull Beverage beverage) {
        Intent beverageActivityIntent = new Intent(BeverageListActivity.this, BeverageDetailActivity.class);
        beverageActivityIntent.putExtra(BeverageDetailActivity.BEVERAGE_MODEL_BUNDLE_KEY, beverage);
        startActivity(beverageActivityIntent);
    }

    @Override
    public void startCoffeeShopListActivity() {
        Intent coffeeShopListActivityIntent = new Intent(this, CoffeeShopListActivity.class);
        startActivity(coffeeShopListActivityIntent);
    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void displayBeverages(@NonNull BeverageListModel beverageListModel) {
        if (null != beverageRecyclerView) {
            BeverageAdapter adapter = new BeverageAdapter(presenter, beverageListModel.getBeverages());
            beverageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            beverageRecyclerView.setAdapter(adapter);
        }
    }
}
