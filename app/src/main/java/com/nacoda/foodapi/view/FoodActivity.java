/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nacoda.foodapi.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nacoda.foodapi.R;
import com.nacoda.foodapi.databinding.FoodActivityBinding;
import com.nacoda.foodapi.model.food.FoodRecipe;
import com.nacoda.foodapi.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class FoodActivity extends AppCompatActivity implements Observer {

    private FoodActivityBinding foodActivityBinding;
    private FoodViewModel foodViewModel;
    private ArrayList<FoodRecipe> foodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(foodActivityBinding.toolbar);
        getSupportActionBar().setTitle("");
        setupListPeopleView(foodActivityBinding.listFood);
        setupObserver(foodViewModel);

    }

    private void initDataBinding() {
        foodActivityBinding = DataBindingUtil.setContentView(this, R.layout.food_activity);
        foodViewModel = new FoodViewModel(this);
        foodActivityBinding.setFoodViewModel(foodViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        FoodMainAdapter adapter = new FoodMainAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        foodViewModel.reset();
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof FoodViewModel) {
            FoodMainAdapter peopleAdapter = (FoodMainAdapter) foodActivityBinding.listFood.getAdapter();
            FoodViewModel foodViewModel = (FoodViewModel) observable;
            peopleAdapter.setFoodList(foodViewModel.getFoodList());
        }
    }
}
