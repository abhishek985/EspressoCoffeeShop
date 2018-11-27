package com.noralynn.coffeeShop.beveragelist;

import android.content.Context;
import android.support.annotation.NonNull;

import com.noralynn.coffeeShop.common.Beverage;

interface BeverageListView {

    void startCoffeeShopListActivity();

    void startBeverageActivity(@NonNull Beverage beverage);

    void displayBeverages(@NonNull BeverageListModel beverageListModel);

    @NonNull
    Context getContext();

}
