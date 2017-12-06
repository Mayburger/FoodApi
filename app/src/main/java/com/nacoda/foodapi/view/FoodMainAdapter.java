package com.nacoda.foodapi.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appolica.flubber.Flubber;
import com.nacoda.foodapi.R;
import com.nacoda.foodapi.databinding.FoodRowBinding;
import com.nacoda.foodapi.model.food.FoodRecipe;
import com.nacoda.foodapi.viewmodel.ItemFoodViewModel;

import java.util.Collections;
import java.util.List;

public class FoodMainAdapter extends RecyclerView.Adapter<FoodMainAdapter.ViewHolder> {

    private List<FoodRecipe> foodList;

    public FoodMainAdapter() {
        this.foodList = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FoodRowBinding foodRowBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.food_row,
                        parent, false);
        return new ViewHolder(foodRowBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindPeople(foodList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setFoodList(List<FoodRecipe> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FoodRowBinding foodRowBinding;

        public ViewHolder(FoodRowBinding foodRowBinding) {
            super(foodRowBinding.foodRowLayout);
            this.foodRowBinding = foodRowBinding;
        }

        void bindPeople(FoodRecipe food, int position) {
            if (foodRowBinding.getFoodRowViewModel() == null) {
                foodRowBinding.setFoodRowViewModel(
                        new ItemFoodViewModel(food, itemView.getContext()));
                Flubber.with()
                        .animation(Flubber.AnimationPreset.SLIDE_DOWN)
                        .duration(Integer.parseInt(String.valueOf((position + 4) + "00")))
                        .createFor(foodRowBinding.foodRowLayout)
                        .start();
            } else {
                foodRowBinding.getFoodRowViewModel().setFoodRecipe(food);
            }
        }
    }
}
