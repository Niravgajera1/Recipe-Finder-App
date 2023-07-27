package com.example.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Listener.RecipeClickedListener;
import com.example.foodrecipeapp.Models.SimilarRecipeResponse;
import com.example.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeviewholder>{

    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickedListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeviewholder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeviewholder holder, int position) {

        holder.similar_recipe_name.setText(list.get(position).title);
        holder.similar_recipe_name.setSelected(true);
        holder.similar_recipe_serving.setText(list.get(position).servings+"Peeson");
        holder.similar_recipe_serving.setSelected(true);
        Picasso.get().load(" https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.similar_recipe_image);

        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SimilarRecipeviewholder extends RecyclerView.ViewHolder{

    CardView similar_recipe_holder;
    TextView similar_recipe_name,similar_recipe_serving;
    ImageView similar_recipe_image;

    public SimilarRecipeviewholder(@NonNull View itemView) {
        super(itemView);

        similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
        similar_recipe_name = itemView.findViewById(R.id.similar_recipe_name);
        similar_recipe_serving = itemView.findViewById(R.id.similar_recipe_serving);
        similar_recipe_image = itemView.findViewById(R.id.similar_recipe_image);
    }
}
