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
import com.example.foodrecipeapp.Models.Recipe;
import com.example.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

  public class RandomRecipesAdapter extends RecyclerView.Adapter<RandomRecipesViewHolder>{

    Context context;
    List<Recipe> list;
    RecipeClickedListener listener;


    public RandomRecipesAdapter(Context context, List<Recipe> list, RecipeClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipesViewHolder holder, int position) {
         holder.textview_title.setText(list.get(position).title);
         holder.textview_title.setSelected(true);
         holder.textview_serving.setText(list.get(position).servings+"Serving");
         holder.textview_likes.setText(list.get(position).aggregateLikes+"Likes");
         holder.textview_time.setText(list.get(position).readyInMinutes+"Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageview_food);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
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
class RandomRecipesViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    TextView textview_title,textview_serving,textview_likes,textview_time;
    ImageView imageview_food;

    public RandomRecipesViewHolder(@NonNull View itemView) {
        super(itemView);

        random_list_container= itemView.findViewById(R.id.random_list_container);
        textview_title= itemView.findViewById(R.id.textview_title);
        textview_serving= itemView.findViewById(R.id.textview_serving);
        textview_likes= itemView.findViewById(R.id.textview_likes);
        textview_time= itemView.findViewById(R.id.textview_time);
        imageview_food= itemView.findViewById(R.id.imageview_food);

    }
}