package com.nacoda.foodapi.data;

import com.nacoda.foodapi.model.food.FoodResponse;
import com.nacoda.foodapi.model.recipe.RecipeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {

    @GET("search")
    Observable<FoodResponse> getFoodQuery(
            @Query("key") String api_key,
            @Query("q") String query,
            @Query("page") String page
    );

    @GET("search")
    Observable<FoodResponse> getFoods(
            @Query("key") String api_key,
            @Query("page") String page
    );

    @GET("get")
    Observable<RecipeResponse> getRecipe(
            @Query("key") String api_key,
            @Query("rId") String rId
    );

}
