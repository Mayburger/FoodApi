package com.nacoda.foodapi.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nacoda.foodapi.model.food.FoodRecipe;
import com.nacoda.foodapi.view.FoodDetailActivity;

/**
 * Created by Mayburger on 12/4/17.
 */

public class ItemFoodViewModel extends BaseObservable {

    private FoodRecipe foodRecipe;
    private Context context;

    public ItemFoodViewModel(FoodRecipe foodRecipe, Context context) {
        this.foodRecipe = foodRecipe;
        this.context = context;
    }

    public String getPublisher() {
        return foodRecipe.getPublisher();
    }

    public String getTitle() {
        return foodRecipe.getTitle();
    }

    public String getSourceUrl() {
        return foodRecipe.getSourceUrl();
    }

    public String getRecipeId() {
        return foodRecipe.getRecipeId();
    }

    public String getImageUrl() {
        return foodRecipe.getImageUrl();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(final ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    public void setFoodRecipe(FoodRecipe foodRecipe) {
        this.foodRecipe = foodRecipe;
        notifyChange();
    }

    public void onItemClick(View view) {
        context.startActivity(FoodDetailActivity.launchDetail(view.getContext(), foodRecipe));
    }

}
