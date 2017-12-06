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

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nacoda.foodapi.R;
import com.nacoda.foodapi.databinding.FoodDetailBinding;
import com.nacoda.foodapi.model.food.FoodRecipe;
import com.nacoda.foodapi.viewmodel.FoodDetailViewModel;
import com.nacoda.foodapi.viewmodel.FoodViewModel;

import java.util.Observable;
import java.util.Observer;

public class FoodDetailActivity extends AppCompatActivity implements Observer {

    private FoodDetailBinding foodDetailBinding;
    private FoodDetailViewModel foodDetailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(foodDetailBinding.toolbar);
        getSupportActionBar().setTitle("");
        setupObserver(foodDetailViewModel);

    }

    public static Intent launchDetail(Context context, FoodRecipe foodRecipe) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra(context.getString(R.string.food_response_key), foodRecipe);
        return intent;
    }

    private void initDataBinding() {
        foodDetailBinding = DataBindingUtil.setContentView(this, R.layout.food_detail);

        FoodRecipe foodRecipe = (FoodRecipe)getIntent().getSerializableExtra(getString(R.string.food_response_key));

        foodDetailViewModel = new FoodDetailViewModel(this, foodRecipe);
        foodDetailBinding.setFoodDetailViewModel(foodDetailViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        foodDetailViewModel.reset();
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof FoodViewModel) {
//            FoodMainAdapter peopleAdapter = (FoodMainAdapter) foodDetailBinding.listFood.getAdapter();
//            FoodViewModel foodViewModel = (FoodViewModel) observable;
//            peopleAdapter.setFoodList(foodViewModel.getFoodList());
        }
    }
}
