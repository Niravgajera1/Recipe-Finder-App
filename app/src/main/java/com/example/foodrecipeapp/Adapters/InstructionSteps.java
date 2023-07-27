package com.example.foodrecipeapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Models.Step;
import com.example.foodrecipeapp.R;

import java.util.List;

public class InstructionSteps extends RecyclerView.Adapter<InstructionStepViewholder>{

    Context context;
    List<Step> list;

    public InstructionSteps(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewholder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewholder holder, int position) {
              holder.textview_instruction_number.setText(String.valueOf(list.get(position).number));
              holder.textview_instruction_step_name.setText(list.get(position).step);

              holder.recycler_instruction_ingredients.setHasFixedSize(true);
              holder.recycler_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
              InstructionIngredientsAdapter instructionIngredientsAdapter = new InstructionIngredientsAdapter(context,list.get(position).ingredients);
              holder.recycler_instruction_ingredients.setAdapter(instructionIngredientsAdapter);

              holder.recycler_instruction_equipments.setHasFixedSize(true);
              holder.recycler_instruction_equipments.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
              InstructionEquipmentsAdapter instructionEquipmentsAdapter = new InstructionEquipmentsAdapter(context,list.get(position).equipment);
              holder.recycler_instruction_equipments.setAdapter(instructionEquipmentsAdapter);
    }

    @Override
    public int getItemCount() {
        return
                list.size();
    }
}

class InstructionStepViewholder extends RecyclerView.ViewHolder{
    TextView textview_instruction_number,textview_instruction_step_name;
    RecyclerView recycler_instruction_equipments,recycler_instruction_ingredients;

    public InstructionStepViewholder(@NonNull View itemView) {
        super(itemView);

        textview_instruction_number = itemView.findViewById(R.id.textview_instruction_number);
        textview_instruction_step_name = itemView.findViewById(R.id.textview_instruction_step_name);
        recycler_instruction_equipments = itemView.findViewById(R.id.recycler_instruction_equipments);
        recycler_instruction_ingredients = itemView.findViewById(R.id.recycler_instruction_ingredients);
    }
}
