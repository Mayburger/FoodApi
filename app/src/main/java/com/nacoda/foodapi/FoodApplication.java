package com.nacoda.foodapi;

import android.app.Application;
import android.content.Context;

import com.nacoda.foodapi.data.FoodFactory;
import com.nacoda.foodapi.data.FoodService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class FoodApplication extends Application {

    private FoodService foodService;
    private Scheduler scheduler;

    private static FoodApplication get(Context context) {
        return (FoodApplication) context.getApplicationContext();
    }

    public static FoodApplication create(Context context) {
        return FoodApplication.get(context);
    }

    public FoodService getFoodService() {
        if (foodService == null) {
            foodService = FoodFactory.create();
        }

        return foodService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
