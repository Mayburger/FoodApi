/**
 * Copyright 2016 Erik Jhordan Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.nacoda.foodapi.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.nacoda.foodapi.Constants;
import com.nacoda.foodapi.FoodApplication;
import com.nacoda.foodapi.data.FoodService;
import com.nacoda.foodapi.model.food.FoodResponse;
import com.nacoda.foodapi.model.food.FoodRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class FoodViewModel extends Observable {

    public ObservableInt peopleProgress;
    public ObservableInt peopleRecycler;

    private ArrayList<FoodRecipe> foodList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public FoodViewModel(@NonNull Context context) {
        peopleRecycler = new ObservableInt(View.GONE);
        peopleProgress = new ObservableInt(View.VISIBLE);
        this.context = context;
        this.foodList = new ArrayList<>();
        fetchFoodList();
    }

    public void onRefreshClick(View view){
        initializeViews();
        fetchFoodList();
    }

    public void initializeViews() {
        peopleRecycler.set(View.GONE);
        peopleProgress.set(View.VISIBLE);
    }

    public void fetchFoodList() {
        peopleRecycler = new ObservableInt(View.GONE);
        peopleProgress = new ObservableInt(View.VISIBLE);
        final FoodApplication foodApplication = FoodApplication.create(context);
        FoodService foodService = foodApplication.getFoodService();
        compositeDisposable.add(
                foodService.getFoods(Constants.API_KEY, "1")
                        .subscribeOn(foodApplication.subscribeScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<FoodResponse>() {
                            @Override
                            public void accept(FoodResponse foodResponse) throws Exception {
                                changeFoodData(foodResponse.getRecipes());
                                peopleProgress.set(View.GONE);
                                peopleRecycler.set(View.VISIBLE);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                peopleProgress.set(View.GONE);
                                peopleRecycler.set(View.VISIBLE);
                            }
                        })
        );
    }

    private void changeFoodData(List<FoodRecipe> foodRecipes) {
        foodList.clear();
        foodList.addAll(foodRecipes);
        setChanged();
        notifyObservers();
    }

    public ArrayList<FoodRecipe> getFoodList() {
        return foodList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
