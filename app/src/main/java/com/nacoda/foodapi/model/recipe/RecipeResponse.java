
package com.nacoda.foodapi.model.recipe;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RecipeResponse {

    @SerializedName("recipe")
    private DetailRecipe mDetailRecipe;

    public DetailRecipe getRecipe() {
        return mDetailRecipe;
    }

    public void setRecipe(DetailRecipe detailRecipe) {
        mDetailRecipe = detailRecipe;
    }

}
