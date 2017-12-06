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
import android.content.Intent;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.nacoda.foodapi.Constants;
import com.nacoda.foodapi.FoodApplication;
import com.nacoda.foodapi.data.FoodService;
import com.nacoda.foodapi.model.food.FoodResponse;
import com.nacoda.foodapi.model.food.FoodRecipe;
import com.nacoda.foodapi.model.recipe.RecipeResponse;
import com.nacoda.foodapi.view.FoodDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class FoodDetailViewModel extends Observable {

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private FoodRecipe foodRecipe;


    public FoodDetailViewModel(@NonNull Context context, FoodRecipe foodRecipe) {
        this.context = context;
        this.foodRecipe = foodRecipe;
        fetchFoodList(foodRecipe.getRecipeId());
    }

    private void fetchFoodList(String rId) {
        final FoodApplication foodApplication = FoodApplication.create(context);
        FoodService foodService = foodApplication.getFoodService();

        compositeDisposable.add(
                foodService.getRecipe(Constants.API_KEY, rId)
                        .subscribeOn(foodApplication.subscribeScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RecipeResponse>() {
                                       @Override
                                       public void accept(RecipeResponse recipeResponse) throws Exception {

                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {

                                       }
                                   }
                        )

        );
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
