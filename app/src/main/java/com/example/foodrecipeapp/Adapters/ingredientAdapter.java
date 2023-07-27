package com.example.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Models.ExtendedIngredient;
import com.example.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ingredientAdapter extends RecyclerView.Adapter<IngredientViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public ingredientAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        ExtendedIngredient ingredient = list.get(position);
        System.out.println(holder.textview_ingredient_name + "-------------------------------------------------------------------");
        if (holder.textview_ingredient_name != null) {
            holder.textview_ingredient_name.setText(ingredient.name);
            holder.textview_ingredient_name.setSelected(true);
        }
        if (holder.textview_ingredient_quntity != null) {
            holder.textview_ingredient_quntity.setText(ingredient.original);
            holder.textview_ingredient_quntity.setSelected(true);
        }
        if (holder.imageview_ingredient_iage != null) {
            Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageview_ingredient_iage);
        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}

 class IngredientViewHolder extends RecyclerView.ViewHolder{
      TextView textview_ingredient_quntity,textview_ingredient_name;
      ImageView imageview_ingredient_iage;
    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        textview_ingredient_quntity = itemView.findViewById(R.id.textview_ingredient_quntity);
        textview_ingredient_name = itemView.findViewById(R.id.textview_ingredient_name);
        imageview_ingredient_iage = itemView.findViewById(R.id.imageview_ingredient_iage);
    }
}
