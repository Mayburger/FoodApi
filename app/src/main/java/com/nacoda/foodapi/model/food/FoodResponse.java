
package com.nacoda.foodapi.model.food;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class FoodResponse {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("recipes")
    private List<FoodRecipe> mFoodRecipes;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public List<FoodRecipe> getRecipes() {
        return mFoodRecipes;
    }

    public void setRecipes(List<FoodRecipe> foodRecipes) {
        mFoodRecipes = foodRecipes;
    }

}
