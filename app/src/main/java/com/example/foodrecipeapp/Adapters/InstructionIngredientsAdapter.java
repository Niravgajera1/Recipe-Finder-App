package com.example.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Models.Ingredient;
import com.example.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsView> {
    Context context;
    List<Ingredient> list;

    public InstructionIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsView(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsView holder, int position) {
          holder.textview_instruction_item_name.setText(list.get(position).name);
          holder.textview_instruction_item_name.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageview_instruction_item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionIngredientsView extends RecyclerView.ViewHolder{
   TextView textview_instruction_item_name;
   ImageView imageview_instruction_item;
    public InstructionIngredientsView(@NonNull View itemView) {
        super(itemView);

        imageview_instruction_item = itemView.findViewById(R.id.imageview_instruction_item);
        textview_instruction_item_name = itemView.findViewById(R.id.textview_instruction_item_name);
    }
}